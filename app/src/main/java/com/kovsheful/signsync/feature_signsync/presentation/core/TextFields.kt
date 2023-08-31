package com.kovsheful.signsync.feature_signsync.presentation.core

import androidx.compose.foundation.border
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kovsheful.signsync.R
import com.kovsheful.signsync.ui.theme.ErrorColor
import com.kovsheful.signsync.ui.theme.SecondaryText
import com.kovsheful.signsync.ui.theme.poppinsItalic
import com.kovsheful.signsync.ui.theme.typography

sealed class RegistrationScreenTextFieldTypes(val text: String, val keyboardType: KeyboardType) {
    data object Name :
        RegistrationScreenTextFieldTypes(text = "Имя", keyboardType = KeyboardType.Text)
    data object Email :
        RegistrationScreenTextFieldTypes(text = "Почта", keyboardType = KeyboardType.Email)
    data object Password :
        RegistrationScreenTextFieldTypes(text = "Пароль", keyboardType = KeyboardType.Password)
}

sealed class PasswordTextFieldTypes(
    val text: String,
    val keyboardType: KeyboardType = KeyboardType.Password
) {
    data object Password :
        PasswordTextFieldTypes(text = "Пароль", keyboardType = KeyboardType.Password)
    data object CurrentPassword : PasswordTextFieldTypes(text = "Текущий пароль")
    data object NewPassword : PasswordTextFieldTypes(text = "Новый пароль")
}

sealed class TextFieldValidityState {
    data object Valid : TextFieldValidityState()
    data object Empty : TextFieldValidityState()
    data object Invalid : TextFieldValidityState()
}

fun colorBasedOnValidity(
    validityState: TextFieldValidityState
): Color = when (validityState) {
    TextFieldValidityState.Valid -> Color.Black
    else -> ErrorColor
}


@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, showSystemUi = true)
@Composable
fun PrevContactsTextField() {
    var prev = ""
    ContactsTextField(
        value = prev,
        onValueChange = { prev = it },
        textFieldType = RegistrationScreenTextFieldTypes.Name,
    )
}

@Composable
fun ContactsTextField(
    value: String,
    onValueChange: (String) -> Unit,
    textFieldType: RegistrationScreenTextFieldTypes,
    validityState: TextFieldValidityState = TextFieldValidityState.Valid,
) {
    val isPlaceholderDisplayed = remember { mutableStateOf(true) }
    val placeholder =
        stringResource(R.string.textfield_placeholder_prefix) + " " + textFieldType.text.lowercase() + "..."
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = textFieldType.text,
                style = typography.labelMedium,
                color = colorBasedOnValidity(validityState)
            )
            Text(
                text = stringResource(R.string.required_field_star),
                style = typography.labelMedium
            )
        }
        Spacer(modifier = Modifier.height(2.dp))
        BasicTextField(
            value = value,
            onValueChange = {
                onValueChange(it)
                isPlaceholderDisplayed.value = it.isEmpty()
            },
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = colorBasedOnValidity(validityState),
                    shape = RoundedCornerShape(5.dp)
                )
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            textStyle = typography.bodyMedium,
            keyboardOptions = KeyboardOptions(
                keyboardType = textFieldType.keyboardType,
                imeAction = ImeAction.Done // Type of "Enter" button on keyboard
            ),
            singleLine = true,
            decorationBox = { innerTextField ->
                if (isPlaceholderDisplayed.value) {
                    Text(
                        text = placeholder,
                        style = typography.bodyMedium.copy(
                            fontFamily = poppinsItalic,
                            color = SecondaryText
                        )
                    )
                }
                innerTextField()
            }
        )
        if (validityState == TextFieldValidityState.Empty) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = stringResource(R.string.textfield_empty_bottomtext),
                    style = typography.labelMedium,
                    color = ErrorColor
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, showSystemUi = true)
@Composable
fun PrevPasswordTextField() {
    var prev = ""
    PasswordTextField(
        value = prev,
        onValueChange = { prev = it },
        textFieldType = PasswordTextFieldTypes.Password,
        validityState = TextFieldValidityState.Invalid
    )
}


@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    textFieldType: PasswordTextFieldTypes,
    validityState: TextFieldValidityState = TextFieldValidityState.Valid
) {
    val placeholder =
        stringResource(R.string.textfield_placeholder_prefix) + " " + textFieldType.text.lowercase() + "..."
    val isPlaceholderDisplayed = remember { mutableStateOf(true) }
    val isPasswordVisible = remember { mutableStateOf(false) }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = textFieldType.text,
                style = typography.labelMedium,
                color = colorBasedOnValidity(validityState)
            )
            if (textFieldType == PasswordTextFieldTypes.Password) {
                Text(
                    text = stringResource(R.string.required_field_star),
                    style = typography.labelMedium
                )
            }
        }
        Spacer(modifier = Modifier.height(2.dp))
        BasicTextField(
            value = value,
            onValueChange = {
                onValueChange(it)
                isPlaceholderDisplayed.value = it.isEmpty()
            },
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = colorBasedOnValidity(validityState),
                    shape = RoundedCornerShape(5.dp)
                )
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            textStyle = typography.bodyMedium,
            keyboardOptions = KeyboardOptions(
                keyboardType = textFieldType.keyboardType,
                imeAction = ImeAction.Done // Type of "Enter" button on keyboard
            ),
            singleLine = true,
            visualTransformation = if (!isPasswordVisible.value)
                PasswordVisualTransformation()
            else
                VisualTransformation.None,
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    ) {
                        if (isPlaceholderDisplayed.value) {
                            Text(
                                text = placeholder,
                                style = typography.bodyMedium.copy(
                                    fontFamily = poppinsItalic,
                                    color = SecondaryText
                                )
                            )
                        }
                        innerTextField()
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    IconButton(
                        onClick = { isPasswordVisible.value = !isPasswordVisible.value },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            modifier = Modifier.fillMaxSize(),
                            painter = painterResource(
                                id = if (!isPasswordVisible.value)
                                    R.drawable.ic_show_password
                                else
                                    R.drawable.ic_dont_show_password
                            ),
                            contentDescription = stringResource(R.string.password_icon_description),
                            tint = colorBasedOnValidity(validityState),
                        )
                    }
                }
            }
        )
        if (validityState != TextFieldValidityState.Valid) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = if (validityState == TextFieldValidityState.Invalid)
                        stringResource(R.string.textfield_invalid_password_bottomtext)
                    else
                        stringResource(R.string.textfield_empty_bottomtext),
                    style = typography.labelMedium,
                    color = ErrorColor
                )
            }
        }
    }
}