package com.example.localdatasource.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(primaryKeys = ["login","title"]) //сдвоенный ключ
data class LikeVideo(
    val login:String,
    val thumbnailUrl:String,
    val title:String
)




//data class LikeVideo(
//
//    val login:String,
//    val thumbnailUrl:String,
//    val title:String,
//    @PrimaryKey(autoGenerate = true)
//val id: Int = 0,
//)
