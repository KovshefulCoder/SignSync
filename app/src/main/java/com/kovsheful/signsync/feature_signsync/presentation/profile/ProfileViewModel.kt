package com.kovsheful.signsync.feature_signsync.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kovsheful.signsync.feature_signsync.core.util.ResourceStatus
import com.kovsheful.signsync.feature_signsync.domain.models.User
import com.kovsheful.signsync.feature_signsync.domain.use_cases.UserUseCases
import com.kovsheful.signsync.feature_signsync.presentation.core.PasswordTextFieldTypes
import com.kovsheful.signsync.feature_signsync.presentation.core.TextFieldValidityState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userUseCases: UserUseCases
) : ViewModel() {
    private val _state = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> = _state.asStateFlow()

    private val _eventFlow = MutableSharedFlow<ResourceStatus<String>>()
    val event = _eventFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            userUseCases.getUser().also { user ->
                _state.value = state.value.copy(
                    name = user.name,
                    email = user.email,
                    userID = user.id
                )
            }
        }
    }

    fun updateOneOfPasswords(fieldName: PasswordTextFieldTypes, fieldValue: String) {
        viewModelScope.launch {
            _state.update { value ->
                when (fieldName) {
                    PasswordTextFieldTypes.CurrentPassword -> value.copy(
                        currentPassword = fieldValue,
                        //isCurrentPasswordValid = if (!state.value.submitClicked) true else fieldValue.isNotEmpty()
                    )

                    PasswordTextFieldTypes.NewPassword -> value.copy(
                        newPassword = fieldValue,
                        //isNewPasswordValid = if (!state.value.submitClicked) true else fieldValue.isNotEmpty()
                    )

                    else -> {
                        value
                    }
                }
            }
        }
    }

    fun updatePassword() {
        viewModelScope.launch {
            _state.update { value ->
                value.copy(
                    submitClicked = true,
                    currentPasswordValidityState = if (!state.value.submitClicked) {
                        TextFieldValidityState.Valid
                    } else if (value.currentPassword.isNotEmpty()) {
                        TextFieldValidityState.Empty
                    } else {
                        TextFieldValidityState.Valid
                    },
                    newPasswordValidityState = if (!state.value.submitClicked) {
                        TextFieldValidityState.Valid
                    } else if (value.newPassword.isNotEmpty()) {
                        TextFieldValidityState.Empty
                    } else {
                        TextFieldValidityState.Valid
                    }
                )
            }
            val currentPassword = userUseCases.getUserPasswordByID(state.value.userID)
            if (currentPassword == state.value.currentPassword) {
                userUseCases.addOrUpdateUser(
                    User(
                        id = state.value.userID,
                        name = state.value.name,
                        email = state.value.email,
                        password = state.value.newPassword
                    )
                )
                _eventFlow.emit(ResourceStatus.Success("Пароль успешно изменен"))
                _state.update { value ->
                    value.copy(
                        currentPassword = "",
                        newPassword = ""
                    )
                }
            } else {
                _eventFlow.emit(ResourceStatus.Error("Неверный текущий пароль"))
                _state.update { value ->
                    value.copy(
                        currentPasswordValidityState = TextFieldValidityState.Invalid
                    )
                }
            }
        }
    }
}