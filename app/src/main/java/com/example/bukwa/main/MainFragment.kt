package com.example.bukwa.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Dao
import com.example.bukwa.data.room.Letters
import com.example.bukwa.data.room.LettersDao
import com.example.bukwa.databinding.FragmentMainBinding
import com.example.bukwa.di.CustomApp
import com.example.bukwa.main.buttonStatus.Status
import com.example.bukwa.util.RussianAlphabetForUrl.Companion.BASE_URL
import com.example.bukwa.util.setImageFromUrl
import com.example.bukwa.viewmodelutils.ViewModelFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine


class MainFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var letterDao: LettersDao

    lateinit var mainViewModel: MainViewModel
    lateinit var imageOfButton: ImageButton


    //viewbinding stuff
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

        letterDao.getAllLetters()

       /* GlobalScope.launch {
            letterDao.insert(Letters(polish = "a", russian = "a"))

        }*/
        binding.imageGifView.setOnClickListener {
            setImageFromUrl(
                binding.imageGifView,
                "$BASE_URL/${mainViewModel.letter}-on.gif"
            )
        }

        binding.spellSampleImageView.setOnClickListener {
            imageOfButton = binding.spellSampleImageView
            mainViewModel.playLetterSampleSoundExample()
        }

        binding.spellLetterImageView.setOnClickListener {
            imageOfButton = binding.spellLetterImageView
            mainViewModel.playLetterSingleSoundExample()
        }

        binding.nextButton.setOnClickListener {
            mainViewModel.nextLetter()
        }

        binding.previousButton.setOnClickListener {
            mainViewModel.previousLetter()
        }

        //kotlin top level function, i don't like it becouse of readability
        imageOfButton = binding.spellSampleImageView

        mainViewModel.letter.observe(viewLifecycleOwner, Observer<String> { currentLetter ->

            setImageFromUrl(
                binding.imageGifView,
                "$BASE_URL/$currentLetter-off.gif"
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


}