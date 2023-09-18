package com.example.rutube.model

data class ResponseModel(
    val has_next: Boolean,
    val next: String,
    val page: Int,
    val per_page: Int,
    val previous: Any,
    val results: List<Result>
)