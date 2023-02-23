package com.example.littlelemon.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.littlelemon.R

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily(Font(R.font.karla_regular)),
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        color = LemonDarkGreen
    ),
    h1 = TextStyle(
        fontFamily = FontFamily(Font(R.font.markazi_regular)),
        fontSize = 66.sp,
        color = LemonYellow
    ),
    h2 = TextStyle(
        fontFamily = FontFamily(Font(R.font.markazi_regular)),
        fontSize = 46.sp,
        color = LemonWhite
    ),
    button = TextStyle(
        fontFamily = FontFamily(Font(R.font.karla_regular)),
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = LemonBlack
    ),

    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)