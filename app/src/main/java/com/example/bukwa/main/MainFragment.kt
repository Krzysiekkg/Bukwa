package com.example.bukwa.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.bukwa.databinding.FragmentMainBinding
import com.example.bukwa.di.CustomApp
import com.example.bukwa.main.buttonStatus.Status
import com.example.bukwa.util.RussianAlphabetForUrl
import com.example.bukwa.util.RussianAlphabetForUrl.Companion.BASE_URL
import com.example.bukwa.util.setImageFromUrl
import com.example.bukwa.viewmodelutils.ViewModelFactory
import javax.inject.Inject


class MainFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var mainViewModel: MainViewModel

    lateinit var imageOfButton: ImageButton

    var bukwa: String = "a"


    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fragmentComponent = ((activity?.application) as CustomApp)
            .appComponent
            .fragmentComponent()
            .fragment(this)
            .build()

        fragmentComponent.inject(this)

        mainViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.imageGifView.setOnClickListener(OnImageClick())
        binding.spellSampleImageView.setOnClickListener(SpellSampleClick())
        binding.spellLetterImageView.setOnClickListener(SpellLetterClick())
        binding.nextButton.setOnClickListener(OnNextButtonClick())
        binding.previousButton.setOnClickListener(OnPreviousButtonClick())
        //kotlin top level function, i don't like it becouse of readability
        imageOfButton = binding.spellSampleImageView

        mainViewModel.letter.observe(viewLifecycleOwner, Observer<Int> { index ->
            bukwa = RussianAlphabetForUrl.listOfLetters.get(index)
            setImageFromUrl(
                binding.imageGifView,
                "$BASE_URL/$bukwa-off.gif"

            )
        })

        mainViewModel.buttonSingleStatus.observe(viewLifecycleOwner, { status ->

            when (status) {
                Status.PLAY -> {
                    binding.spellLetterImageView.setImageResource(android.R.drawable.ic_media_play)
                    binding.spellSampleImageView.setImageResource(android.R.drawable.ic_media_play)
                }
                Status.PAUSE -> imageOfButton.setImageResource(android.R.drawable.ic_media_pause)
            }
        })

        return binding.root
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    inner class OnImageClick : View.OnClickListener {
        override fun onClick(v: View?) {
            setImageFromUrl(
                binding.imageGifView,
                "$BASE_URL/$bukwa-on.gif"
            )
        }
    }

    inner class SpellSampleClick : View.OnClickListener {
        override fun onClick(v: View?) {
            imageOfButton = binding.spellSampleImageView
            mainViewModel.playLetterSampleSoundExample()
        }
    }

    inner class SpellLetterClick : View.OnClickListener {
        override fun onClick(v: View?) {
            imageOfButton = binding.spellLetterImageView
            mainViewModel.playLetterSingleSoundExample()
        }
    }

    inner class OnNextButtonClick : View.OnClickListener {
        override fun onClick(v: View?) {
            mainViewModel.nextLetter()
        }
    }

    inner class OnPreviousButtonClick : View.OnClickListener {
        override fun onClick(v: View?) {
            mainViewModel.previousLetter()
        }
    }
}