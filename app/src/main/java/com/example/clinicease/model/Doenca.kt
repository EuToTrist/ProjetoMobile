package com.example.clinicease.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Doenca(
    val name: String,
    val symptoms: List<String>
): Parcelable