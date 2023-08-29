package com.kovsheful.signsync.feature_signsync.presentation.core

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kovsheful.signsync.ui.theme.ErrorColor
import com.kovsheful.signsync.ui.theme.SecondaryText
import com.kovsheful.signsync.ui.theme.TextFieldColor
import com.kovsheful.signsync.ui.theme.poppinsItalic
import com.kovsheful.signsync.ui.theme.typography

sealed class ContactsTextFieldType(val text: String, val keyboardType: KeyboardType) {
    data object Name : ContactsTextFieldType(text = "Name", keyboardType = KeyboardType.Text)
    data object Email : ContactsTextFieldType(text = "Email", keyboardType = KeyboardType.Email)
    data object Password : ContactsTextFieldType(text = "Password", keyboardType = KeyboardType.Password)
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, showSystemUi = true)
@Composable
fun PrevContactsTextField() {
    var prev = ""
    ContactsTextField(
        value = prev,
        onValueChange = { prev = it },
        textFieldType = ContactsTextFieldType.Name,
    )
}

@Composable
fun ContactsTextField(
    value: String,
    onValueChange: (String) -> Unit,
    textFieldType: ContactsTextFieldType,
    validityState: Boolean = true,
) {

    val isPlaceholderDisplayed = remember { mutableStateOf(true) }
    val placeholder = "Enter your ${textFieldType.text.lowercase()}..."
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "${textFieldType.text}*",
            style = typography.labelMedium
        )
        BasicTextField(
            value = value,
            onValueChange = {
                onValueChange(it)
                isPlaceholderDisplayed.value = it.isEmpty()
            },
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = if (validityState) TextFieldColor else ErrorColor,
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
                            color = SecondaryText)
                    )
                }
                innerTextField()
            }
        )
    }
}