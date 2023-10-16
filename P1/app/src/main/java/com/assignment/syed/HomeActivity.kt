package com.assignment.syed


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import com.assignment.mohammed.R

class HomeActivity : AppCompatActivity() {
    private lateinit var preferences: SharedPreferences

    private lateinit var addReviewButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val appPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val isDarkThemeEnabled = appPreferences.getBoolean("dark_theme", false)

        if (isDarkThemeEnabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        addReviewButton = findViewById(R.id.add_review_button)

        addReviewButton.setOnClickListener {
            val intent = Intent(this, ReviewActivity::class.java)
            startActivity(intent)
        }

        intent?.extras?.let {
            val comments = it.getString("comments")
            val ratings = it.getInt("ratings")
            Toast.makeText(this, "Comments: $comments\nRating: $ratings Stars", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                val settingsIntent = Intent(this, CustomSettingsActivity::class.java)
                startActivity(settingsIntent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
