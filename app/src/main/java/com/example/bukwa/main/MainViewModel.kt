package com.example.bukwa.main

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(val letterRepository: LetterRepository) : ViewModel() {

    val buttonSingleStatus = letterRepository.mediaPlayerUtil.buttonSingleStatus
    val letter = letterRepository.currentLetter


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