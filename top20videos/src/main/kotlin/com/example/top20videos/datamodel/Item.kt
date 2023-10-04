package com.example.top20videos.datamodel

data class Item(
    val image: String,
    val text: String,
    val isLiked:Boolean = false
)