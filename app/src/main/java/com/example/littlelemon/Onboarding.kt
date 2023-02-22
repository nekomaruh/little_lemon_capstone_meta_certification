package com.example.littlelemon

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.preference.PreferenceManager


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Onboarding(navController: NavController) {
    Scaffold(
        topBar = { Header() }
    ) {
        Content(navController)
    }
}

@Composable
private fun Header() {
    TopAppBar {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "",
            modifier = Modifier.fillMaxWidth()
        )
    }
}

enum class DialogState { Disabled, Success, Error }

@Composable
private fun Content(navController: NavController) {
    val firstName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val dialogState = remember { mutableStateOf(DialogState.Disabled) }
    Column {
        Text(text = "Let's get to know you")
        TextField(
            value = firstName.value,
            label = { Text(text = "First Name") },
            onValueChange = { firstName.value = it }
        )
        TextField(
            value = lastName.value,
            label = { Text(text = "Last Name") },
            onValueChange = { lastName.value = it }
        )
        TextField(
            value = email.value,
            label = { Text(text = "Email Address") },
            onValueChange = { email.value = it }
        )
        Button(onClick = {
            if (firstName.value.isBlank() &&
                lastName.value.isBlank() &&
                email.value.isBlank()
            ) {
                dialogState.value = DialogState.Error
            } else {
                dialogState.value = DialogState.Success
            }
        }) {
            Text(text = "Register")
        }

        when (dialogState.value) {
            DialogState.Disabled -> {}
            DialogState.Success -> {
                val context = LocalContext.current
                ShowDialog(text = "Registration successful!") {
                    dialogState.value = DialogState.Disabled
                    PreferenceManager.getDefaultSharedPreferences(context).edit().apply {
                        putBoolean("userIsLogged", true)
                        putString("firstName", firstName.value)
                        putString("lastName", lastName.value)
                        putString("email", email.value)
                        apply()
                    }
                    navController.navigate(Home.route)
                }
            }
            DialogState.Error -> {
                ShowDialog(text = "Registration unsuccessful. Please enter all data.") {
                    dialogState.value = DialogState.Disabled
                }
            }
        }
    }
}

@Composable
fun ShowDialog(text: String, onTap: () -> Unit) {
    AlertDialog(
        onDismissRequest = { },
        confirmButton = {
            TextButton(onClick = onTap)
            { Text(text = "OK") }
        },
        text = { Text(text = text) }
    )
}
