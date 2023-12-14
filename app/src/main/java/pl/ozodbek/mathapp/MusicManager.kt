package pl.ozodbek.mathapp

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer

class MusicManager(private val context: Context, private val musicResId: Int) {

    private var mediaPlayer: MediaPlayer? = null
    private var isPrepared = false
    private var isPaused = false

    fun start() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, musicResId)
            mediaPlayer?.isLooping = true
            mediaPlayer?.setOnPreparedListener {
                isPrepared = true
                mediaPlayer?.start()
            }
        } else {
            if (isPaused) {
                mediaPlayer?.start()
                isPaused = false
            }
        }
    }

    fun pause() {
        mediaPlayer?.pause()
        isPaused = true
    }

    fun stop() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        isPrepared = false
        isPaused = false
    }

    fun saveState(sharedPref: SharedPreferences) {
        with(sharedPref.edit()) {
            putBoolean("isPaused", isPaused)
            putInt("position", mediaPlayer?.currentPosition ?: 0)
            commit()
        }
    }

    fun restoreState(sharedPref: SharedPreferences) {
        val isPaused = sharedPref.getBoolean("isPaused", false)
        val position = sharedPref.getInt("position", 0)
        if (isPaused) {
            mediaPlayer?.seekTo(position)
            this.isPaused = true
        }
    }

    fun release() {
        mediaPlayer?.release()
        mediaPlayer = null
    }

    fun isPlaying(): Boolean {
        return mediaPlayer?.isPlaying ?: false
    }

    fun isPrepared(): Boolean {
        return isPrepared
    }
}