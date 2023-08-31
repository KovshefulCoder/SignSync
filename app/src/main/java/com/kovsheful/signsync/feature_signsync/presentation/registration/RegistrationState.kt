package com.kovsheful.signsync.feature_signsync.presentation.registration

data class RegistrationState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val isNameValid: Boolean = true,
    val isEmailValid: Boolean = true,
    val isPasswordValid: Boolean = true,
    val submitClicked: Boolean = false // if false, then we don't show error when text fields are empty
)
