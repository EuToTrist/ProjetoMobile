package com.example.clinicease.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Repository (
    val name: String,
    val symptoms: String,

) : Parcelable
