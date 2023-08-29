package com.kovsheful.signsync.feature_signsync.presentation.core

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kovsheful.signsync.ui.theme.ErrorColor
import com.kovsheful.signsync.ui.theme.SecondaryText
import com.kovsheful.signsync.ui.theme.TextFieldColor
import com.kovsheful.signsync.ui.theme.poppinsItalic
import com.kovsheful.signsync.ui.theme.typography


sealed class ContactsTextFieldType(val labelText: String, val keyboardType: KeyboardType) {
    class Name(label: String) : ContactsTextFieldType(label, KeyboardType.Text)
    class Email(label: String) : ContactsTextFieldType(label, KeyboardType.Email)
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, showSystemUi = true)
@Composable
fun PrevContactsTextField() {
    var prev = ""
    ContactsTextField(
        value = prev,
        onValueChange = { prev = it },
        textFieldType = ContactsTextFieldType.Name("Name")
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
    val placeholder = "Enter your ${textFieldType.labelText}..."
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "${textFieldType.labelText}*",
            style = typography.labelMedium
        )
        BasicTextField(
            value = if (isPlaceholderDisplayed.value) placeholder else value,
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
                .fillMaxWidth(),
            textStyle = if (isPlaceholderDisplayed.value) {
                typography.bodyMedium.copy(
                    fontFamily = poppinsItalic,
                    color = SecondaryText)
            } else typography.bodyMedium,
            keyboardOptions = KeyboardOptions(
                keyboardType = textFieldType.keyboardType,
                imeAction = ImeAction.Done // Type of "Enter" button on keyboard
            ),
            singleLine = true,
        )
    }
}