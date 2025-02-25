package com.example.testingproject.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.example.testingproject.IntroActivity
import com.example.testingproject.NavigationDirections
import com.example.testingproject.R
import com.example.testingproject.sharedPreference.LoginStatusPreference
import java.lang.Exception

object WarningDialogUtility {
    fun showLoginObligatoryWarning(context: Context, fragment: Fragment) {
        MaterialAlertDialogBuilder(context)
            .setTitle("Peringatan")
            .setMessage(context.getString(R.string.login_obligatory_warning))
            .setCancelable(false)
            .setPositiveButton("OK") { _, _ ->
                fragment.findNavController().navigate(R.id.loginFragment, null)
            }
            .show()
    }

    fun showTokenExpirationWarning(context: Context, fragment: Fragment) {

        LoginStatusPreference(context).setAsLoggedOut()

        MaterialAlertDialogBuilder(context)
            .setTitle("Peringatan")
            .setMessage(context.getString(R.string.token_expiration_warning))
            .setCancelable(false)
            .setPositiveButton("OK") { _, _ ->
                fragment.findNavController().navigate(NavigationDirections.redirectToLogin())
            }
            .show()
    }

    fun showUpdateObligatoryWarning(context: Context, activity: IntroActivity, urlToPlayStore: String) {

        MaterialAlertDialogBuilder(context)
            .setTitle("Peringatan")
            .setMessage(context.getString(R.string.app_update_obligatory_warning))
            .setCancelable(false)
            .setPositiveButton("OK") { _, _ ->
                try {
                    activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(urlToPlayStore)))
                    activity.finish()
                } catch (exception: Exception) {
                    Log.i("appNotFound", "$exception")
                    Toast.makeText(context, "Aplikasi tidak ditemukan", Toast.LENGTH_SHORT).show()
                }
            }
            .show()
    }

    fun showMaintenanceDialog(context: Context, activity: IntroActivity) {

        MaterialAlertDialogBuilder(context)
            .setTitle("System Maintenance")
            .setMessage(context.getString(R.string.app_maintenance_warning))
            .setCancelable(false)
            .setPositiveButton("OK") { _, _ ->
                activity.finish()
            }
            .show()
    }

    fun showRemoteConfigFetchFailure(context: Context, activity: IntroActivity) {

        MaterialAlertDialogBuilder(context)
            .setTitle("Peringatan")
            .setMessage("Terjadi Kesalahan, mohon coba beberapa saat lagi")
            .setCancelable(false)
            .setPositiveButton("OK") { _, _ ->
                activity.finish()
            }
            .show()
    }

}