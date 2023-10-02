package com.example.likescreen.rommtable

import androidx.room.Entity

@Entity(primaryKeys = ["login","title"]) //сдвоенный ключ
data class LikeVideo(
    val login:String,
    val thumbnailUrl:String,
    val title:String
)
