package com.example.testingproject.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

object ExternalUrlLauncher {

    var remoteConfigUtility = RemoteConfigUtility()

    fun launchInstagramUrl(context: Context, fragment: Fragment) {
        Toast.makeText(
            context,
            "Redirecting to our Instagram Account...",
            Toast.LENGTH_SHORT
        ).show()

        remoteConfigUtility.remoteConfig.fetchAndActivate()
            .addOnCompleteListener(fragment.requireActivity()) { task ->
                if (task.isSuccessful) {
                    val instagramUrl =
                        remoteConfigUtility.remoteConfig.getString("instagram_url")
                    this.launchUrl(fragment, instagramUrl)
                }
            }

    }

    fun launchWhatsappUrl(context: Context, fragment: Fragment) {
        Toast.makeText(
            context,
            "Redirecting to our contact support...",
            Toast.LENGTH_SHORT
        ).show()

        remoteConfigUtility.remoteConfig.fetchAndActivate()
            .addOnCompleteListener(fragment.requireActivity()) { task ->
                if (task.isSuccessful) {
                    val whatsappNumber ="6281119113338"
                    val whatsappUrl = "https://wa.me/$whatsappNumber"

                    this.launchUrl(fragment, whatsappUrl)
                }
            }
    }

    private fun launchUrl(fragment: Fragment, url: String) {
        val intent = Intent(Intent.ACTION_VIEW)

        intent.data = Uri.parse(url)
        fragment.startActivity(intent)
    }
}