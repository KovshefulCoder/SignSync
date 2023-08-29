package com.kovsheful.signsync.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kovsheful.signsync.R

val poppins = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal),
)

val poppinsItalic = FontFamily(
    Font(R.font.poppins_lightitalic, FontWeight.ExtraLight),
)

// Set of Material typography styles to start with
val typography = Typography(
    bodyMedium = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = PrimaryTextColor
    ),
    displayLarge = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 44.sp,
        color = PrimaryTextColor
    ),
    displayMedium = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        color = PrimaryTextColor
    ),
    headlineLarge = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        color = PrimaryTextColor
    ),
    titleLarge = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = PrimaryTextColor
    ),
    titleMedium = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        color = PrimaryTextColor
    ),
    bodySmall = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = PrimaryTextColor
    ),
    labelMedium = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        color = PrimaryTextColor
    )
)