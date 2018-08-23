package com.qdwrysoft.roomdb.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Entity
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.arch.persistence.room.Room



@Database(entities = [Friend::class],version = 1)
abstract class FriendRoomDataBase : RoomDatabase() {
    abstract fun friendDao(): FriendDao

    companion object {
        private var INSTANCE: FriendRoomDataBase? = null

        @JvmStatic
        fun getDatabase(context: Context): FriendRoomDataBase? {
            if (INSTANCE == null) {
                synchronized(FriendRoomDataBase::class.java) {
                    if (INSTANCE == null) {
                        // Create database here
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                                FriendRoomDataBase::class.java, "friend_database")
                                .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}