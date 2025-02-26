package com.example.testingproject.sharedPreference

import android.content.Context
import com.example.testingproject.dataClass.LoggedUser
class LoginStatusPreference(context: Context) {
    companion object{
        const val LOGIN_STAT_PREFERENCE_NAME = "login_status_preference"
        const val LOGGED_STATUS = "logged_status"
        const val LOGGED_MEMBER_ID = "logged_member_id"
        const val LOGGED_MEMBER_USERNAME = "logged_member_username"
        const val LOGIN_TOKEN = "logged_member_token"
        const val LOGIN_OUTLETID = "logged_member_outletId"
    }

    val loginStatusPreference = context.getSharedPreferences(LOGIN_STAT_PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun setAsLoggedIn(loggedUser: LoggedUser) {
        val preferenceEditor = loginStatusPreference.edit()

        preferenceEditor.putBoolean(LOGGED_STATUS, true)
        preferenceEditor.apply()


        this.setToken(loggedUser.loginToken)

    }

    fun setAsLoggedOut() {
        val preferenceEditor = loginStatusPreference.edit()

        preferenceEditor.putBoolean(LOGGED_STATUS, false)
        preferenceEditor.putString(LOGGED_MEMBER_ID, null)
        preferenceEditor.putString(LOGGED_MEMBER_USERNAME, null)
        preferenceEditor.putString(LOGIN_TOKEN, null)
        preferenceEditor.putString(LOGIN_OUTLETID, null)

        preferenceEditor.apply()
    }

    fun getUserLoggedStatus(): LoggedUser {
        val loggedUserStatus =
            LoggedUser()
        loggedUserStatus.isLoggedIn = loginStatusPreference.getBoolean(LOGGED_STATUS, false)
        loggedUserStatus.loginToken =
            loginStatusPreference.getString(LOGIN_TOKEN, null)
        return loggedUserStatus
    }

    fun getLoginToken(): String? {
        return loginStatusPreference.getString(LOGIN_TOKEN, null)
    }

    fun setToken(loginToken: String?) {
        val preferenceEditor = loginStatusPreference.edit()

        preferenceEditor.putString(LOGIN_TOKEN, loginToken)

        preferenceEditor.apply()
    }

}