package fr.neon.e_kfc_v2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val textView = findViewById<TextView>(R.id.textView)

        textView.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}