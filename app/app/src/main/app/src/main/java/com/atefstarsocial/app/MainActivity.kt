package com.atefstarsocial.app

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import android.graphics.Color

class MainActivity : Activity() {

    private var mediaUri: Uri? = null
    private lateinit var caption: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this)
        root.orientation = LinearLayout.VERTICAL
        root.setPadding(24, 30, 24, 24)
        root.setBackgroundColor(Color.rgb(10,10,10))

        val title = TextView(this)
        title.text = "✦ ATEF STAR SOCIAL"
        title.textSize = 25f
        title.gravity = Gravity.CENTER
        title.setTextColor(Color.rgb(212,175,55))
        root.addView(title)

        val subtitle = TextView(this)
        subtitle.text = "إدارة ونشر السوشيال ميديا"
        subtitle.gravity = Gravity.CENTER
        subtitle.setTextColor(Color.WHITE)
        root.addView(subtitle)

        val choose = Button(this)
        choose.text = "اختيار صورة أو فيديو"
        root.addView(choose)

        choose.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.type = "*/*"
            intent.putExtra(Intent.EXTRA_MIME_TYPES,
                arrayOf("image/*","video/*"))
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            startActivityForResult(intent,100)
        }

        caption = EditText(this)
        caption.hint = "اكتب المنشور والهاشتاجات"
        caption.setTextColor(Color.WHITE)
        caption.setHintTextColor(Color.GRAY)
        root.addView(caption)

        val platforms = listOf(
            "TikTok","YouTube","Facebook","Instagram"
        )

        platforms.forEach {
            val check = CheckBox(this)
            check.text = it
            check.setTextColor(Color.WHITE)
            root.addView(check)
        }

        val share = Button(this)
        share.text = "مشاركة المنشور"
        root.addView(share)

        share.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = if(mediaUri != null)
                contentResolver.getType(mediaUri!!) ?: "*/*"
            else "text/plain"

            intent.putExtra(Intent.EXTRA_TEXT,
                caption.text.toString())

            if(mediaUri != null) {
                intent.putExtra(Intent.EXTRA_STREAM,mediaUri)
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }

            startActivity(Intent.createChooser(intent,"مشاركة المنشور"))
        }

        setContentView(root)
    }

    @Deprecated("Deprecated")
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode,resultCode,data)
        if(requestCode == 100 && resultCode == RESULT_OK) {
            mediaUri = data?.data
        }
    }
}
