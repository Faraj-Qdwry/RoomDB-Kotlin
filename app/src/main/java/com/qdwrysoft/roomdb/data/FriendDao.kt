package com.qdwrysoft.roomdb.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.lifecycle.LiveData

@Dao
interface FriendDao {
    @Insert
    fun insert(friend: Friend)

    @Query("DELETE FROM friends_list")
    fun deletAll()

    @Query("SELECT * from friends_list ORDER BY name ASC")
    fun getAllFriends(): LiveData<List<Friend>>

//    @Query("DELETE FROM friends_list WHERE friendId")
//    fun delete(friend: Friend)
}