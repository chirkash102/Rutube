package com.example.auth.likedata

import androidx.room.Entity

@Entity(primaryKeys = ["login","title"]) //сдвоенный ключ
data class LikeVideo(
    val login:String,
    val thumbnailUrl:String,
    val title:String
)
