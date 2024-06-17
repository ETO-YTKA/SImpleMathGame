package com.example.mathapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val LightTypography = Typography(
    bodyMedium = TextStyle(
        color = DarkGray,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal
    ),
    headlineMedium = TextStyle(
        color = DarkGray,
        fontSize = 42.sp,
        fontWeight = FontWeight.Medium
    )
)

val DarkTypography = Typography(
    bodyMedium = TextStyle(
        color = LightGray,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal
    ),
    headlineMedium = TextStyle(
        color = LightGray,
        fontSize = 42.sp,
        fontWeight = FontWeight.Medium
    )
)