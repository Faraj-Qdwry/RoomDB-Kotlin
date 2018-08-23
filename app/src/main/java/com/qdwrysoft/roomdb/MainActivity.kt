package com.qdwrysoft.roomdb

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.RoomDatabase
import android.os.AsyncTask
import android.support.v7.app.AlertDialog
import com.qdwrysoft.roomdb.data.Friend
import com.qdwrysoft.roomdb.data.FriendDao
import com.qdwrysoft.roomdb.data.FriendRoomDataBase
import android.widget.LinearLayout
import android.widget.EditText
import android.arch.lifecycle.ViewModelProviders
import android.support.annotation.Nullable


class MainActivity : AppCompatActivity() {

    lateinit var alertDialog: AlertDialog
    private lateinit var mFriendViewModel: FriendViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = FriendListAdapter(this)

        mFriendViewModel = ViewModelProviders.of(this).get(FriendViewModel::class.java)

        mFriendViewModel.allFriends.observe(this, Observer<List<Friend>> { t -> adapter.setWords(t!!) })

        fab.setOnClickListener { view ->

            mFriendViewModel.insert(Friend(getFreinName().toString()))

            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }


        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        val sRoomDatabaseCallback = object : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                PopulateDbAsync(FriendRoomDataBase.getDatabase(this@MainActivity)).execute()
            }
        }

    }

    class PopulateDbAsync (db: FriendRoomDataBase?) : AsyncTask<Void, Void, Void>() {

        private val mDao: FriendDao = db!!.friendDao()

        override fun doInBackground(vararg params: Void): Void? {
            mDao.deletAll()
            var friend = Friend("Hello")
            mDao.insert(friend)
            friend = Friend("World")
            mDao.insert(friend)
            return null
        }
    }

    fun getFreinName():String{
        var name : String = ""
        val input = EditText(this@MainActivity)
        val lp = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT)
        input.layoutParams = lp
        alertDialog = AlertDialog.Builder(this)
                .setView(input) // uncomment this line
                .setTitle("Friend Name ...")
                .setPositiveButton("OK"){ _,dialoge ->
                    name = input.text.toString()
                }
                .create()

        alertDialog.show()
        return name
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
