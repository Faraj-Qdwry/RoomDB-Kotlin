package com.qdwrysoft.roomdb

import android.arch.lifecycle.LiveData
import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.qdwrysoft.roomdb.data.Friend
import com.qdwrysoft.roomdb.data.FriendRepository


class FriendViewModel(application: Application) : AndroidViewModel(application) {

    private val mRepository: FriendRepository = FriendRepository(application)

    val allFriends: LiveData<List<Friend>>

    init {
        allFriends = mRepository.mAllFriends
    }

    fun insert(friend: Friend) {
        mRepository.insert(friend)
    }
}