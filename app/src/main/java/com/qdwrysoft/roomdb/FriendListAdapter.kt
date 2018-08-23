package com.qdwrysoft.roomdb

import android.content.Context
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.View
import com.qdwrysoft.roomdb.data.Friend


class FriendListAdapter(context: Context) : RecyclerView.Adapter<FriendListAdapter.FriendViewHolder>() {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private var mFriends = ArrayList<Friend>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder  =
            FriendViewHolder(mInflater.inflate(R.layout.friend_item_view, parent, false))


    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
            holder.wordItemView.text = mFriends[position].name
    }

    fun setWords(words: List<Friend>) {
        mFriends.addAll(words)
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int = mFriends.size

    class FriendViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView = itemView.findViewById(R.id.textView)
    }
}