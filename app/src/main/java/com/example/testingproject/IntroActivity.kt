package com.example.testingproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testingproject.R
import com.google.android.material.shape.CornerFamily

import kotlinx.android.synthetic.main.activity_intro.*


//This is the activity for Splash screen.
//Automatically navigate to Main Activity after several seconds
class IntroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        this.setupAppLogo()
        this.redirectToMainActivity()
    }


    private fun setupAppLogo() {
        introLogo.shapeAppearanceModel =
            introLogo.shapeAppearanceModel
                .toBuilder()
                .setAllCorners(
                    CornerFamily.ROUNDED,
                    resources.getDimension(R.dimen.profile_picture_radius)
                )
                .build()
    }


    //Function to Redirect to Main Activity
    private fun redirectToMainActivity() {
        Intent(this, MainActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(it)
        }
    }
}