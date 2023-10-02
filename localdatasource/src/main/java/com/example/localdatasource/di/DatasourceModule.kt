package com.example.localdatasource.di

import androidx.room.Room
import com.example.localdatasource.daos.LikeDao
import com.example.localdatasource.daos.MembersDao
import com.example.localdatasource.database.AppDataBase
import org.koin.dsl.module

val datasourceModule = module {

    single<AppDataBase> {
        Room.databaseBuilder(get(), AppDataBase::class.java, "RutubeDataBase").build()
    }
        single<MembersDao> {
        val dataBase = get<AppDataBase>()
        dataBase.getDao()
    }
    single<LikeDao> {
        val dataBase = get<AppDataBase>()
        dataBase.getLikeDao()
    }
}