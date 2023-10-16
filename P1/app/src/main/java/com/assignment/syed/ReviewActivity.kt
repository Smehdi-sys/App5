package com.assignment.syed


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import com.assignment.mohammed.R

class ReviewActivity : AppCompatActivity() {
    private lateinit var preferences: SharedPreferences

    private lateinit var submitReviewButton: Button
    private lateinit var commentsEditText: EditText
    private lateinit var ratingBar: RatingBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating)

        val appPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val isDarkThemeEnabled = appPreferences.getBoolean("dark_theme", false)

        if (isDarkThemeEnabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        submitReviewButton = findViewById(R.id.submit_review_button)
        commentsEditText = findViewById(R.id.review_comments)
        ratingBar = findViewById(R.id.review_ratingbar)

        submitReviewButton.setOnClickListener {
            submitReview()
        }

        preferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val defaultRatingString = preferences.getString("default_rating", "0")
        val defaultRating = defaultRatingString!!.toFloat()

        ratingBar.rating = defaultRating
        commentsEditText.setText(preferences.getString("course_name", ""))
    }

    private fun submitReview() {
        val comments = commentsEditText.text.toString()
        val ratings = ratingBar.rating.toInt()

        val editor = preferences.edit()
        editor.putString("course_name", comments)
        editor.apply()

        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("comments", comments)
        intent.putExtra("ratings", ratings)
        startActivity(intent)
        finish()
    }
}
