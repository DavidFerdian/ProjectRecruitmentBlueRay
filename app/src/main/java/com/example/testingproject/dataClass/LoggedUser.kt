package com.example.testingproject.dataClass

data class LoggedUser(
    var isLoggedIn: Boolean? = null,
    var loginToken: String? = null,
)