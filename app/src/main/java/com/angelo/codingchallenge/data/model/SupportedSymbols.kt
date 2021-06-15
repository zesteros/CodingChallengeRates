package com.angelo.codingchallenge.data.model

data class SupportedSymbols(
    val success: Boolean,
    val symbols: Map<String, String>,
    val error: Error?
)