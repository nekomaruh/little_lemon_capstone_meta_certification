package com.example.littlelemon.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.LemonBlack
import com.example.littlelemon.ui.theme.LemonDarkGreen
import com.example.littlelemon.ui.theme.LemonGray
import com.example.littlelemon.ui.theme.LemonGreen
import com.example.littlelemon.ui.theme.LemonOrange
import com.example.littlelemon.ui.theme.LemonWhite

@Composable
private fun LemonTextFieldColors(
    textColor: Color = LemonBlack,
    disabledTextColor: Color = LemonGray,
    backgroundColor: Color = LemonWhite,
    cursorColor: Color = LemonDarkGreen,
    errorCursorColor: Color = LemonOrange
) = TextFieldDefaults.textFieldColors(
    textColor = textColor,
    disabledTextColor = disabledTextColor,
    backgroundColor = backgroundColor,
    cursorColor = cursorColor,
    errorCursorColor = errorCursorColor,
    focusedLabelColor = LemonDarkGreen
)

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LemonTextField(hint: String, value: String, onChange: (text: String) -> Unit) {
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        colors = LemonTextFieldColors(),
        singleLine = true,
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier.fillMaxWidth(),
        keyboardActions = KeyboardActions(
            onDone = { keyboardController?.hide() }),
        placeholder = { Text(hint) },
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchLemonTextField(hint: String, value: String, onChange: (text: String) -> Unit) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        value = value,
        onValueChange = onChange,
        colors = LemonTextFieldColors(),
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(hint) },
        singleLine = true,
        keyboardActions = KeyboardActions(
            onDone = { keyboardController?.hide() }),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_search_24),
                contentDescription = ""
            )
        }
    )
}