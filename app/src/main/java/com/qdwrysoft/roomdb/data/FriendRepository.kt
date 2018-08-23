package com.qdwrysoft.roomdb.data

import android.arch.lifecycle.LiveData
import android.app.Application
import android.os.AsyncTask

class FriendRepository(application: Application) {

    val db = FriendRoomDataBase.getDatabase(application)

    lateinit var mFriendDao : FriendDao
    lateinit var mAllFriends : LiveData<List<Friend>>

    init {
        mFriendDao = db?.friendDao()!!
        mFriendDao.getAllFriends()
    }

    fun insert(friend: Friend) {
        insertAsyncTask(mFriendDao).execute(friend)
    }

    private class insertAsyncTask internal constructor(private val mAsyncTaskDao: FriendDao) : AsyncTask<Friend, Void, Void>() {
        override fun doInBackground(vararg params: Friend): Void? {
            mAsyncTaskDao.insert(params[0])
            return null
        }
    }
}