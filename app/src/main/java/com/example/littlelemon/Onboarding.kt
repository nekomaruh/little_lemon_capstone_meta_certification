package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun Onboarding() {
    Scaffold(
        topBar = { Header() }
    ) {
        Content()
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

@Composable
private fun Content() {
    val firstName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    val address = remember { mutableStateOf("") }
    Column {
        Text(text = "Let's get to know you")
        TextField(
            value = firstName.value,
            label = { Text(text = "First Name")},
            onValueChange = { firstName.value = it }
        )
        TextField(
            value = lastName.value,
            label = { Text(text = "Last Name")},
            onValueChange = { lastName.value = it }
        )
        TextField(
            value = address.value,
            label = { Text(text = "Address")},
            onValueChange = { address.value = it }
        )
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Register")
        }
    }
}