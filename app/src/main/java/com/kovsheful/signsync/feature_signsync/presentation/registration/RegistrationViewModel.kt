package com.kovsheful.signsync.feature_signsync.presentation.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kovsheful.signsync.feature_signsync.presentation.core.ContactsTextFieldType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor () : ViewModel()  {
    private val _state = MutableStateFlow(RegistrationState())
    val state: StateFlow<RegistrationState> = _state.asStateFlow()

    fun updateDataField(fieldName: ContactsTextFieldType, fieldValue: String) {
        viewModelScope.launch {
            _state.update { value ->
                when (fieldName) {
                    ContactsTextFieldType.Name-> value.copy(
                        name = fieldValue,
                        isNameValid = if (!state.value.submitClicked) true else fieldValue.isNotEmpty()
                    )
                    ContactsTextFieldType.Email -> value.copy(
                        email = fieldValue,
                        isEmailValid = if (!state.value.submitClicked) true else fieldValue.isNotEmpty()
                    )
                    ContactsTextFieldType.Password -> value.copy(
                        password = fieldValue,
                        isPasswordValid = if (!state.value.submitClicked) true else fieldValue.isNotEmpty()
                    )
                }
            }
        }
    }

    fun onSubmitClicked() {
        viewModelScope.launch {
            _state.update { value ->
                value.copy(
                    submitClicked = true,
                    isNameValid = value.name.isNotEmpty(),
                    isEmailValid = value.email.isNotEmpty(),
                    isPasswordValid = value.password.isNotEmpty()
                )
            }
        }
    }

}