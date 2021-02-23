package com.example.bukwa.util

import javax.inject.Inject

//temporary class
class RussianAlphabetForUrl @Inject constructor() {


    companion object {
        val listOfLetters = listOf(
            "a", "be", "we", "ge", "de", "je", "jo", "sche", "se", "i", "i-kratkaje",
            "ka", "el", "em", "en", "o", "pe", "er", "es", "te", "u",
            "ef", "cha", "tse", "tsche", "scha", "schtscha", "twordyj-znak",
            "y", "machkij-znak", "e", "ju", "ja",
        )
        const val BASE_URL = "https://www.russlandjournal.de/en/wp-content/uploads/sites/2/2014/09"
    }
}