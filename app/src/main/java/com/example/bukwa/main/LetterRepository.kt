package com.example.bukwa.main

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.bukwa.util.MediaPlayerUtil
import com.example.bukwa.util.RussianAlphabetForUrl
import javax.inject.Inject


class LetterRepository @Inject constructor(
    val mediaPlayerUtil: MediaPlayerUtil
) {

    private var letterIndex = MutableLiveData<Int>()
    var currentLetter = MediatorLiveData<String>()

    init {
        letterIndex.value = 0
        currentLetter.apply {
            letterIndex.value?.let {
                addSource(letterIndex) {
                    value = RussianAlphabetForUrl.listOfLetters.get(it)
                }
            }
        }
    }


    fun playLetterSampleSoundExample() {

        val url = "${RussianAlphabetForUrl.BASE_URL}/${currentLetter}-samples.mp3"
        mediaPlayerUtil.playSound(url)
    }

    fun nextLetter() {
        letterIndex.value.let {
            if (it == (RussianAlphabetForUrl.listOfLetters.lastIndex)) {
                letterIndex.value = 0
                return
            }
            letterIndex.value = it?.plus(1)
        }
    }

    fun previousLetter() {
        letterIndex.value.let { letterList ->
            if (letterList!!.equals(0)) {
                letterIndex.value = RussianAlphabetForUrl.listOfLetters.lastIndex
                return
            }
            letterIndex.value = letterList!!.minus(1)
        }
    }

    fun playLetterSingleSoundExample() {

        val url = "${RussianAlphabetForUrl.BASE_URL}/${currentLetter.value}.mp3"

        mediaPlayerUtil.playSound(url)
    }

}