package com.example.bukwa.main

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(val mainRepository: MainRepository) : ViewModel() {

    val buttonSingleStatus = mainRepository.mediaPlayerUtil.buttonSingleStatus
    val letter = mainRepository.letter


    fun playLetterSampleSoundExample() {
        mainRepository.playLetterSampleSoundExample()
    }

    fun nextLetter() {
        mainRepository.nextLetter()
    }

    fun previousLetter() {
        mainRepository.previousLetter()
    }

    fun playLetterSingleSoundExample() {
        mainRepository.playLetterSingleSoundExample()
    }

}