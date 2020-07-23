package hr.rma.db.assecoforecast

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import hr.rma.db.assecoforecast.R

class SplashActivity : AppCompatActivity() {
    private val TIME_OUT:Long=10
    private val FULL_BAR = 100
    private var i = 0
    private var progressBar:ProgressBar? = null
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        progressBar = findViewById(R.id.welcome_progress_bar)
        i = progressBar!!.progress

        Thread (Runnable {
            while(i < FULL_BAR){
                i += 1
                handler.post(Runnable {
                    progressBar!!.progress = i
                })
                try {
                    Thread.sleep(TIME_OUT)
                } catch (e: InterruptedException){
                    e.printStackTrace()
                }
            }
            startActivity(Intent(this, MainActivity::class.java))

            finish()
        }).start()

    }
}