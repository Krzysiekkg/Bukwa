package com.example.bukwa.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bukwa.data.room.Letters
import com.example.bukwa.util.RussianAlphabetForUrl
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(val letterRepository: LetterRepository) : ViewModel() {

    val buttonSingleStatus = letterRepository.mediaPlayerUtil.buttonSingleStatus
    val letter = letterRepository.currentLetter


    init {
        //for database inspector
        viewModelScope.launch {
            letterRepository.lettersDao.getAllLettersOnClick()
        }
    }

    fun playLetterSampleSoundExample() {
        letterRepository.playLetterSampleSoundExample()
    }

    fun nextLetter() {
        letterRepository.nextLetter()
    }

    fun previousLetter() {
        letterRepository.previousLetter()
    }

    fun playLetterSingleSoundExample() {
        letterRepository.playLetterSingleSoundExample()
    }

}