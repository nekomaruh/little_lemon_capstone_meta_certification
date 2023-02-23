package com.example.littlelemon.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.littlelemon.ui.theme.LemonBlack
import com.example.littlelemon.ui.theme.LemonDarkGreen
import com.example.littlelemon.ui.theme.LemonGreen
import com.example.littlelemon.ui.theme.LemonOrange
import com.example.littlelemon.ui.theme.LemonWhite
import com.example.littlelemon.ui.theme.LemonYellow

@Composable
fun LemonButton(text: String, onTap: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val elevation = ButtonDefaults.elevation(
        defaultElevation = 0.dp,
        pressedElevation = 0.dp
    )

    val colors = ButtonDefaults.buttonColors(
        backgroundColor = if (isPressed) LemonDarkGreen else LemonYellow,
    )

    val textColor = if (isPressed) LemonWhite else LemonBlack

    val modifier = Modifier
        .fillMaxWidth()
        .padding(all = 20.dp)

    Button(
        border = BorderStroke(1.dp, Color.Transparent),
        interactionSource = interactionSource,
        elevation = elevation,
        modifier = modifier,
        colors = colors,
        onClick = onTap
    ) {
        Text(text = text, color = textColor)
    }
}

@Composable
fun LemonOutlinedButton(text: String, onTap: () -> Unit) {
    val elevation = ButtonDefaults.elevation(
        defaultElevation = 0.dp,
        pressedElevation = 0.dp
    )

    val colors = ButtonDefaults.buttonColors(
        backgroundColor = LemonGreen.copy(alpha = 0.15F),
    )

    val textColor = LemonDarkGreen

    OutlinedButton(
        border = BorderStroke(1.dp, Color.Transparent),
        elevation = elevation,
        colors = colors,
        shape = RoundedCornerShape(40),
        onClick = onTap
    ) {
        Text(text = text, color = textColor, fontWeight = FontWeight.Bold)
    }
}