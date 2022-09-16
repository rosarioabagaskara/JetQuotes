package com.rosariobagaskara.jetquotes

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity


class InfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.toolbar_title_layout_info)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setContentView(R.layout.activity_info)
        val quotesTV = findViewById<TextView>(R.id.quotes)
        val authorTV = findViewById<TextView>(R.id.author)

        val quotesValue : String? = intent.getStringExtra("quotes")?.replace("""[\p{P}\p{S}&&[^.]]+""".toRegex(), "")
        val authorValue : String? = intent.getStringExtra("author")

        quotesTV.text = quotesValue
        authorTV.text = "- $authorValue"
        val quotesAndAuthor = "$quotesValue\n - $authorValue"
        copyQuote(quotesAndAuthor)
        shareQuote(quotesAndAuthor)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun copyQuote(quotes : String){
        val copyImageView = findViewById<ImageView>(R.id.copyImageView)
        copyImageView.setOnClickListener {
            val myClipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val myClip = ClipData.newPlainText("text", quotes)
            myClipboard.setPrimaryClip(myClip)

            Toast.makeText(applicationContext, "Text Copied", Toast.LENGTH_SHORT).show()
        }
    }

    private fun shareQuote(quotes : String){
        val shareImageView = findViewById<ImageView>(R.id.shareImageView)
        shareImageView.setOnClickListener {
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type="text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, quotes);
            startActivity(Intent.createChooser(shareIntent,"Share via"))
        }
    }
}