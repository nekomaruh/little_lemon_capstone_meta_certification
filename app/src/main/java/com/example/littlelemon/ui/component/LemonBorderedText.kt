package com.example.littlelemon.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.littlelemon.ui.theme.LemonGray
import com.example.littlelemon.ui.theme.LemonWhite

@Composable
fun LemonBorderedText(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .background(color = LemonWhite)
            .border(1.dp, color = LemonGray, shape = RoundedCornerShape(5.dp))
            .padding(10.dp)
            .fillMaxWidth()
    )
}