package com.qdwrysoft.roomdb.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

@Entity (tableName = "friends_list")
data class Friend(
//        @PrimaryKey(autoGenerate = true) val friendId : Int,
        @PrimaryKey @NonNull @ColumnInfo(name = "name")val name: String)