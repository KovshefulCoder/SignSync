package com.kovsheful.signsync.feature_signsync.presentation.registration

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
import androidx.compose.material.ButtonColors
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
        },
        name = state.name,
        email = state.email,
        password = state.password,
        onDataChanged = viewModel::updateDataField,
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
        onDataChanged = { _, _ -> }
    )
}

@Composable
fun RegistrationView(
    onRegistration: () -> Unit,
    name: String,
    email: String,
    password: String,
    onDataChanged: (ContactsTextFieldType, String) -> Unit,
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
                        style = typography.displayMedium
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))
                ContactsTextField(
                    value = name,
                    onValueChange = { newValue ->
                        onDataChanged(ContactsTextFieldType.Name, newValue)
                    },
                    textFieldType = ContactsTextFieldType.Name
                )
                Spacer(modifier = Modifier.height(16.dp))
                ContactsTextField(
                    value = email,
                    onValueChange = { newValue ->
                        onDataChanged(ContactsTextFieldType.Email, newValue)
                    },
                    textFieldType = ContactsTextFieldType.Email
                )
                Spacer(modifier = Modifier.height(16.dp))
                ContactsTextField(
                    value = password,
                    onValueChange = { newValue ->
                        onDataChanged(ContactsTextFieldType.Password, newValue)
                    },
                    textFieldType = ContactsTextFieldType.Password
                )
                Spacer(modifier = Modifier.height(60.dp))
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
