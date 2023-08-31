package com.kovsheful.signsync.feature_signsync.presentation.profile

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kovsheful.signsync.R
import com.kovsheful.signsync.feature_signsync.core.util.ResourceStatus
import com.kovsheful.signsync.feature_signsync.presentation.core.PasswordTextField
import com.kovsheful.signsync.feature_signsync.presentation.core.PasswordTextFieldTypes
import com.kovsheful.signsync.feature_signsync.presentation.core.TextFieldValidityState
import com.kovsheful.signsync.feature_signsync.presentation.registration.SubmitButton
import com.kovsheful.signsync.ui.theme.Background
import com.kovsheful.signsync.ui.theme.BlockColor
import com.kovsheful.signsync.ui.theme.SecondaryText
import com.kovsheful.signsync.ui.theme.typography
import com.ramcosta.composedestinations.annotation.Destination

@Destination("Profile")
@Composable
internal fun ProfileView() {
    val viewModel: ProfileViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val event by viewModel.event.collectAsStateWithLifecycle(initialValue = ResourceStatus.None())
    ProfileView(
        name = state.name,
        email = state.email,
        onPasswordChanged = viewModel::updateOneOfPasswords,
        onChangePassword = viewModel::updatePassword,
        currentPassword = state.currentPassword,
        newPassword = state.newPassword,
        textFieldsValidity = Pair(
            state.currentPasswordValidityState,
            state.newPasswordValidityState
        ),
        flowEvent = event,
    )
}

@Preview
@Composable
fun PrevProfileView() {
    ProfileView(
        name = "Денc",
        email = "2323@gmail.com",
        onPasswordChanged = { _, _ -> },
        onChangePassword = {},
        currentPassword = "",
        newPassword = "",
        textFieldsValidity = Pair(TextFieldValidityState.Valid, TextFieldValidityState.Valid),
        flowEvent = ResourceStatus.None()
    )
}

@Composable
private fun ProfileView(
    name: String,
    email: String,
    onPasswordChanged: (PasswordTextFieldTypes, String) -> Unit,
    onChangePassword: () -> Unit,
    currentPassword: String,
    newPassword: String,
    textFieldsValidity: Pair<TextFieldValidityState, TextFieldValidityState>,
    flowEvent: ResourceStatus<String>,

    ) {
    val snackBarHostState = remember { SnackbarHostState() }
    LaunchedEffect(flowEvent) {
        when (flowEvent) {
            is ResourceStatus.Success<String> -> {
                snackBarHostState.showSnackbar(
                    message = flowEvent.data ?: "",
                    duration = SnackbarDuration.Short
                )
            }
            is ResourceStatus.Error<String> -> {
                snackBarHostState.showSnackbar(
                    message = flowEvent.message ?: "",
                    duration = SnackbarDuration.Short
                )
            }

            else -> {}
        }
    }
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
                Spacer(modifier = Modifier.height(32.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.profile_welcome_text) + "$name!",
                        style = typography.displayMedium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                color = BlockColor,
                                shape = RoundedCornerShape(5.dp)
                            )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 16.dp),
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Text(
                                    text = stringResource(R.string.contacts_title_text),
                                    style = typography.bodyMedium
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(
                                    imageVector = Icons.Filled.Edit,
                                    contentDescription = stringResource(R.string.contacts_editicon_description),
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 4.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = stringResource(R.string.name_conatacts_list_text),
                                    modifier = Modifier.padding(end = 8.dp),
                                    style = typography.bodySmall.copy(
                                        color = SecondaryText
                                    ),
                                )
                                Text(
                                    text = name,
                                    style = typography.bodySmall,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 4.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = stringResource(R.string.email_conatacts_list_text),
                                    modifier = Modifier.padding(end = 8.dp),
                                    style = typography.bodySmall.copy(
                                        color = SecondaryText
                                    )
                                )
                                Text(
                                    text = email,
                                    style = typography.bodySmall,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(48.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.update_password_title_text),
                        style = typography.titleMedium
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                PasswordTextField(
                    value = currentPassword,
                    onValueChange = { newValue ->
                        onPasswordChanged(
                            PasswordTextFieldTypes.CurrentPassword, newValue
                        )
                    },
                    textFieldType = PasswordTextFieldTypes.CurrentPassword,
                    validityState = textFieldsValidity.first
                )
                Spacer(modifier = Modifier.height(24.dp))
                Spacer(
                    modifier = Modifier.height(
                        if (textFieldsValidity.first == TextFieldValidityState.Valid) 24.dp else 6.dp
                    )
                )
                PasswordTextField(
                    value = newPassword,
                    onValueChange = { newValue ->
                        onPasswordChanged(
                            PasswordTextFieldTypes.NewPassword, newValue
                        )
                    },
                    textFieldType = PasswordTextFieldTypes.NewPassword,
                    validityState = textFieldsValidity.second
                )
                Spacer(modifier = Modifier.height(48.dp))
                SubmitButton(
                    onClick = onChangePassword,
                    buttonText = stringResource(R.string.profile_editsubmit_button_text)
                )
            }
        }
    }
}