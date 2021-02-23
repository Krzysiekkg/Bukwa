package com.example.bukwa.util

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.lifecycle.MutableLiveData
import com.example.bukwa.main.buttonStatus.Status
import javax.inject.Inject


class MediaPlayerUtil @Inject constructor() {

    var buttonSingleStatus = MutableLiveData<Status>().apply {
        value = Status.STOP
    }

    var mediaPlayer: MediaPlayer? = MediaPlayer()

    init {
        mediaPlayer?.setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build()
        )
    }

    fun playSound(url: String) {

        mediaPlayer?.let {
            it.setOnCompletionListener {
                buttonSingleStatus.postValue(Status.PLAY)
            }

            if (it.isPlaying) {
                it.pause()
                buttonSingleStatus.postValue(Status.PLAY)
            } else {
                buttonSingleStatus.postValue(Status.PAUSE)
                prepare(url)
                it.start()
            }
        }
    }

    fun prepare(url: String) {
        mediaPlayer?.let {
            it.reset();
            it.setDataSource(url)
            it.prepare()// might take long! (for buffering, etc)
        }
    }
}