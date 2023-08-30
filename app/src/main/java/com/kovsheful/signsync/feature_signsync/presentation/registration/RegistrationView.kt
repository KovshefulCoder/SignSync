package com.kovsheful.signsync.feature_signsync.presentation.registration

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kovsheful.signsync.R
import com.kovsheful.signsync.feature_signsync.presentation.core.ContactsTextField
import com.kovsheful.signsync.feature_signsync.presentation.core.ContactsTextFieldType
import com.kovsheful.signsync.feature_signsync.presentation.core.PasswordTextField
import com.kovsheful.signsync.feature_signsync.presentation.core.TextFieldValidityState
import com.kovsheful.signsync.ui.theme.Background
import com.kovsheful.signsync.ui.theme.PrimaryColor
import com.kovsheful.signsync.ui.theme.typography
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
internal fun RegistrationView(
    navigator: DestinationsNavigator
) {
    val viewModel: RegistrationViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    RegistrationView(
        onRegistration = {
            viewModel.onSubmitClicked()
            if (state.isNameValid && state.isEmailValid && state.isPasswordValid) {
                Log.i("RegistrationView", "Success")
            }
        },
        name = state.name,
        email = state.email,
        password = state.password,
        onDataChanged = viewModel::updateDataField,
        textFieldsValidity = Triple(state.isNameValid, state.isEmailValid, state.isPasswordValid)
    )
}

@Preview
@Composable
fun PrevRegistrationView() {
    RegistrationView(
        onRegistration = {},
        name = "",
        email = "",
        password = "",
        onDataChanged = { _, _ -> },
        textFieldsValidity = Triple(true, true, true)
    )
}

@Composable
private fun RegistrationView(
    onRegistration: () -> Unit,
    name: String,
    email: String,
    password: String,
    onDataChanged: (ContactsTextFieldType, String) -> Unit,
    textFieldsValidity: Triple<Boolean, Boolean, Boolean>
) {
    val snackBarHostState = remember { SnackbarHostState() }
    Scaffold(
        backgroundColor = Background,
        snackbarHost = {
            SnackbarHost(snackBarHostState)
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter = painterResource(id = R.drawable.introduction_image),
                    contentDescription = null,
                    modifier = Modifier.size(260.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Welcome aboard!",
                        style = typography.displayMedium,

                        )
                }
                Spacer(modifier = Modifier.height(16.dp))
                ContactsTextField(
                    value = name,
                    onValueChange = { newValue ->
                        onDataChanged(ContactsTextFieldType.Name, newValue)
                    },
                    textFieldType = ContactsTextFieldType.Name,
                    validityState = if (textFieldsValidity.first) {
                        TextFieldValidityState.Valid
                    } else {
                        TextFieldValidityState.Empty
                    }
                )
                Spacer(modifier = Modifier.height(if (textFieldsValidity.first) 24.dp else 6.dp))
                ContactsTextField(
                    value = email,
                    onValueChange = { newValue ->
                        onDataChanged(ContactsTextFieldType.Email, newValue)
                    },
                    textFieldType = ContactsTextFieldType.Email,
                    validityState = if (textFieldsValidity.second) {
                        TextFieldValidityState.Valid
                    } else {
                        TextFieldValidityState.Empty
                    }
                )
                Spacer(modifier = Modifier.height(if (textFieldsValidity.second) 24.dp else 6.dp))
                PasswordTextField(
                    value = password,
                    onValueChange = { newValue ->
                        onDataChanged(ContactsTextFieldType.Password, newValue)
                    },
                    textFieldType = ContactsTextFieldType.Password,
                    validityState = if (textFieldsValidity.third) {
                        TextFieldValidityState.Valid
                    } else {
                        TextFieldValidityState.Empty
                    }
                )
                Spacer(modifier = Modifier.height(if (textFieldsValidity.third) 8.dp else 4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "* - required field",
                        style = typography.labelMedium
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))
                SubmitButton(
                    onClick = onRegistration
                )
            }
        }
    }
}

@Composable
fun SubmitButton(
    buttonText: String = "Sign up",
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(5.dp),
        colors = buttonColors(
            containerColor = PrimaryColor,
        )
    ) {
        Text(
            text = buttonText,
            style = typography.titleLarge.copy(
                color = Color.White
            )
        )
    }
}
