package com.example.littlelemon.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.LemonWhite

@Composable
fun LemonHeader() {
    TopAppBar(
        backgroundColor = LemonWhite,
        elevation = 0.dp,
        modifier = Modifier.height(100.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        )
    }
}