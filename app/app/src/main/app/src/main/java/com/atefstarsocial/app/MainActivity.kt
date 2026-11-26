package com.atefstarsocial.app

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this)
        root.orientation = LinearLayout.VERTICAL
        root.gravity = Gravity.CENTER
        root.setPadding(24, 30, 24, 24)
        root.setBackgroundColor(Color.rgb(10, 10, 10))

        val title = TextView(this)
        title.text = "✦ ATEF STAR SOCIAL ✦"
        title.textSize = 26f
        title.gravity = Gravity.CENTER
        title.setTextColor(Color.rgb(212, 175, 55))
        root.addView(title)

        val subtitle = TextView(this)
        subtitle.text = "إدارة ونشر السوشيال ميديا"
        subtitle.textSize = 18f
        subtitle.gravity = Gravity.CENTER
        subtitle.setTextColor(Color.WHITE)
        root.addView(subtitle)

        val button = Button(this)
        button.text = "أهلاً بيك في تطبيقك"
        root.addView(button)

        button.setOnClickListener {
            Toast.makeText(
                this,
                "Atef Star Social يعمل بنجاح",
                Toast.LENGTH_LONG
            ).show()
        }

        setContentView(root)
    }
}
