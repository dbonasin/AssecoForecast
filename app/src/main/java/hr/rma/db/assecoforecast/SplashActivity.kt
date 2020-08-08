package hr.rma.db.assecoforecast

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity


class SplashActivity : AppCompatActivity() {
    private val TIME_OUT:Long=10
    private val FULL_BAR = 100
    private var i = 0
    private var progressBar:ProgressBar? = null
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        progressBar = findViewById(R.id.welcome_progress_bar)

        object : CountDownTimer(1000, 1) {
            override fun onTick(millisUntilFinished: Long) {
                val i = (1000 - millisUntilFinished)/10
                progressBar!!.progress = i.toInt()
                Log.d("Splash", "loading: " + i + "%")
            }

            override fun onFinish() {
                startActivity(Intent(intent))
                finish();
            }
        }.start()


    }
}