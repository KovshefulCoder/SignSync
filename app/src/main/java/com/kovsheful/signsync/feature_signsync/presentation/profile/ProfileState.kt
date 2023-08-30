package com.kovsheful.signsync.feature_signsync.presentation.profile

import com.kovsheful.signsync.feature_signsync.presentation.core.TextFieldValidityState

data class ProfileState(
    val name: String = "",
    val email: String = "",
    val userID: Int = 0,
    val currentPassword: String = "",
    val currentPasswordValidityState: TextFieldValidityState = TextFieldValidityState.Valid,
    val newPassword: String = "",
    val newPasswordValidityState: TextFieldValidityState = TextFieldValidityState.Valid,
    val submitClicked: Boolean = false // if false, then we don't show error when text fields are empty
)
