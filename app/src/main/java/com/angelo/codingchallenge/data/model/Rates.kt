package com.angelo.codingchallenge.data.model

data class Rates(
    val base: String?,
    val date: String?,
    val rates: Map<String, Double>?,
    val success: Boolean,
    val timestamp: Int?,
    val error: Error?
)