package com.example.littlelemon

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.littlelemon.ui.component.LemonHeader
import com.example.littlelemon.ui.theme.LemonDarkGreen
import com.example.littlelemon.ui.theme.LemonYellow

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Home(navController: NavController) {
    Scaffold(
        topBar = { ProfileLemonHeader(navController) }
    ) {
        Content()
    }
}

@Composable
private fun ProfileLemonHeader(navController: NavController) {
    LemonHeader()
    Row(
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(20.dp)
    ) {
        Box(modifier = Modifier.weight(1f))
        ProfileImageButton(navController = navController)
    }
}

@Composable
private fun ProfileImageButton(navController: NavController) {
    Image(
        painter = painterResource(id = R.drawable.profile),
        contentDescription = "",
        modifier = Modifier
            .height(60.dp)
            .clickable {
                navController.navigate(Profile.route)
            }
    )
}

@Composable
private fun Content() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .background(color = LemonDarkGreen)
                .fillMaxWidth()
                .height(100.dp)
        )
    }
}