package pl.ozodbek.mathapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pl.ozodbek.mathapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var musicPlayer: MusicManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Display the FirstFragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, FragmentController())
            .commit()

        musicPlayer = MusicManager(this, R.raw.bg_music)
        musicPlayer.start()


    }

    override fun onPause() {
        super.onPause()
        if (musicPlayer.isPlaying()) {
            musicPlayer.pause()
            musicPlayer.saveState(getPreferences(Context.MODE_PRIVATE))
        }
    }

    override fun onResume() {
        super.onResume()
        musicPlayer.restoreState(getPreferences(Context.MODE_PRIVATE))
        if (!musicPlayer.isPlaying() && musicPlayer.isPrepared()) {
            musicPlayer.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        musicPlayer.release()
    }
}