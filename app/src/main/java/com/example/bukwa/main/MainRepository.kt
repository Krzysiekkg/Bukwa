package com.example.bukwa.main

import androidx.lifecycle.MutableLiveData
import com.example.bukwa.util.MediaPlayerUtil
import com.example.bukwa.util.RussianAlphabetForUrl
import javax.inject.Inject


class MainRepository @Inject constructor(
    val mediaPlayerUtil: MediaPlayerUtil

) {
    var letter = MutableLiveData<Int>().apply {
        value = 0
    }


    fun playLetterSampleSoundExample() {

        val url =
            "${RussianAlphabetForUrl.BASE_URL}/${RussianAlphabetForUrl.listOfLetters.get(letter.value!!.toInt())}-samples.mp3" // your URL here

        mediaPlayerUtil.playSound(url)
    }

    fun nextLetter() {
        letter.value.let {
            if (it == (RussianAlphabetForUrl.listOfLetters.lastIndex)) {
                letter.value = 0
                return
            }
            letter.value = it?.plus(1)
        }
    }

    fun previousLetter() {
        letter.value.let { letterList ->
            if (letterList!!.equals(0)) {
                letter.value = RussianAlphabetForUrl.listOfLetters.lastIndex
                return
            }
            letter.value = letterList!!.minus(1)
        }
    }

    fun playLetterSingleSoundExample() {

        val url =
            "${RussianAlphabetForUrl.BASE_URL}/${RussianAlphabetForUrl.listOfLetters.get(letter.value!!.toInt())}.mp3" // your URL here

        mediaPlayerUtil.playSound(url)
    }

}