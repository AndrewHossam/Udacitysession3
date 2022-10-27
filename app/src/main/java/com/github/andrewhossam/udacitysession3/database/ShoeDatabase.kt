package com.github.andrewhossam.udacitysession3.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Shoe::class], version = 1)
abstract class ShoeDatabase : RoomDatabase() {

    // Create our DAO
    abstract val shoeDatabaseDao: ShoeDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: ShoeDatabase? = null
        fun getInstance(context: Context): ShoeDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ShoeDatabase::class.java,
                        "ShoeDatabase"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}