package com.example.littlelemon

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.preference.PreferenceManager
import com.example.littlelemon.ui.component.LemonButton
import com.example.littlelemon.ui.component.LemonHeader
import com.example.littlelemon.ui.component.LemonTextField
import com.example.littlelemon.ui.component.TextH2
import com.example.littlelemon.ui.theme.LemonDarkGreen


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Onboarding(navController: NavController) {
    Scaffold(
        topBar = { LemonHeader() }
    ) {
        Content(navController)
    }
}

enum class DialogState { Disabled, Success, Error }

@Composable
fun GreenHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(color = LemonDarkGreen),
        contentAlignment = Alignment.Center
    ) {
        TextH2(text = "Let's get to know you")
    }
}

@Composable
private fun Content(navController: NavController) {
    val firstName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val dialogState = remember { mutableStateOf(DialogState.Disabled) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        GreenHeader()
        Column(
            modifier = Modifier.padding(all = 20.dp)
        ) {

            Text(
                text = "Personal Information",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )

            Box(modifier = Modifier.height(20.dp))

            Text(text = "First Name")
            Box(modifier = Modifier.height(6.dp))
            LemonTextField(
                hint = "",
                value = firstName.value
            ) { firstName.value = it }

            Box(modifier = Modifier.height(10.dp))

            Text(text = "Last Name")
            Box(modifier = Modifier.height(6.dp))
            LemonTextField(
                hint = "",
                value = lastName.value
            ) { lastName.value = it }

            Box(modifier = Modifier.height(10.dp))

            Text(text = "Email Address")
            Box(modifier = Modifier.height(6.dp))
            LemonTextField(
                hint = "",
                value = email.value
            ) { email.value = it }

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
        LemonButton(text = "Register") {
            if (firstName.value.isBlank() &&
                lastName.value.isBlank() &&
                email.value.isBlank()
            ) dialogState.value = DialogState.Error
            else dialogState.value = DialogState.Success
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
