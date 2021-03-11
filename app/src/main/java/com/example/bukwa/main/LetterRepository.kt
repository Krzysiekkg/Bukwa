package com.example.bukwa.main

import androidx.lifecycle.MutableLiveData
import com.example.bukwa.data.room.LettersDao
import com.example.bukwa.util.MediaPlayerUtil
import com.example.bukwa.util.RussianAlphabetForUrl.Companion.BASE_URL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject


class LetterRepository @Inject constructor(
    val mediaPlayerUtil: MediaPlayerUtil,
    val lettersDao: LettersDao
) {

    private var letterIndex: Int
    private lateinit var lettersForUrl: List<String>
    var currentLetter = MutableLiveData<String>()


    init {

        letterIndex = 0
        CoroutineScope(IO).launch {
            lettersForUrl = lettersDao.getLettersForUrl()
            currentLetter.postValue(lettersForUrl.get(letterIndex))

        }
    }

    fun nextLetter() {

        when (letterIndex.equals(lettersForUrl.lastIndex)) {
            true -> letterIndex = 0
            false -> letterIndex++
        }
        currentLetter.postValue(lettersForUrl.get(letterIndex))

    }

    fun previousLetter() {

        when (letterIndex.equals(0)) {
            true -> letterIndex = lettersForUrl.lastIndex
            false -> letterIndex--
        }
        currentLetter.postValue(lettersForUrl.get(letterIndex))

    }

    fun playLetterSampleSoundExample() {
        val url = "${BASE_URL}/${lettersForUrl.get(letterIndex)}-samples.mp3"
        mediaPlayerUtil.playSound(url)


    }

    fun playLetterSingleSoundExample() {

        val url = "${BASE_URL}/${lettersForUrl.get(letterIndex)}.mp3"

        mediaPlayerUtil.playSound(url)
    }

}