package com.kovsheful.signsync.feature_signsync.presentation.registration

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kovsheful.signsync.feature_signsync.domain.models.User
import com.kovsheful.signsync.feature_signsync.domain.use_cases.UserUseCases
import com.kovsheful.signsync.feature_signsync.presentation.core.RegistrationScreenTextFieldTypes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val userUseCases: UserUseCases
) : ViewModel() {
    private val _state = MutableStateFlow(RegistrationState())
    val state: StateFlow<RegistrationState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            userUseCases.clearUserOnStart()
        }
    }

    fun updateDataField(fieldName: RegistrationScreenTextFieldTypes, fieldValue: String) {
        viewModelScope.launch {
            Log.i("RegistrationViewModel", state.value.toString() + "new: $fieldValue")
            _state.update { value ->
                when (fieldName) {
                    RegistrationScreenTextFieldTypes.Name -> value.copy(
                        name = fieldValue,
                        isNameValid = if (!state.value.submitClicked) true else fieldValue.isNotEmpty()
                    )

                    RegistrationScreenTextFieldTypes.Email -> value.copy(
                        email = fieldValue,
                        isEmailValid = if (!state.value.submitClicked) true else fieldValue.isNotEmpty()
                    )

                    RegistrationScreenTextFieldTypes.Password -> value.copy(
                        password = fieldValue,
                        isPasswordValid = if (!state.value.submitClicked) true else fieldValue.isNotEmpty()
                    )
                }
            }
        }
    }

    fun onSubmitClicked() {
        _state.update { value ->
            value.copy(
                submitClicked = true,
                isNameValid = value.name.isNotEmpty(),
                isEmailValid = value.email.isNotEmpty(),
                isPasswordValid = value.password.isNotEmpty()
            )
        }
        viewModelScope.launch {
            if (state.value.isNameValid && state.value.isEmailValid && state.value.isPasswordValid)
            {
                userUseCases.addOrUpdateUser(
                    User(
                        name = state.value.name,
                        email = state.value.email,
                        password = state.value.password
                    )
                )
            }
        }
    }

}