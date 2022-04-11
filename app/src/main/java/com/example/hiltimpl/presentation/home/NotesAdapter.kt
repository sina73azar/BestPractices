package com.example.hiltimpl.presentation.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hiltimpl.R
import com.example.hiltimpl.domain.Note


class NotesAdapter: ListAdapter<Note, NotesAdapter.NoteViewHolder>(differCallback) {

    companion object{
        private const val TAG = "NotesAdapter"
        private val differCallback = object : DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                Log.d(TAG,Thread.currentThread().name)
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                Log.d(TAG,Thread.currentThread().name)
                return (oldItem.name==newItem.name)&&
                        (oldItem.text==newItem.text)&&
                        (oldItem.date==newItem.date)
            }

        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val tvName :TextView= itemView.findViewById(R.id.tv_note_name)
        private val tvText :TextView= itemView.findViewById(R.id.tv_note_text)
        private val tvDate:TextView=itemView.findViewById(R.id.tv_date)

        fun bind(note: Note?) {
            note?.let {
                tvName.text = it.name
                tvText.text = it.text
                tvDate.text = it.date
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.note_item_card,parent,false)
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        differ.currentList.let {
            val note = it[position]
            holder.bind(note)
        }

    }
}