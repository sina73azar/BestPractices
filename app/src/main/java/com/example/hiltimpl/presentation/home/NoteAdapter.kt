package com.example.hiltimpl.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hiltimpl.R
import com.example.hiltimpl.domain.Note

class NoteAdapter:RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private var oldList= emptyList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.note_item_card,parent,false))
    }
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(oldList[position])
    }
    override fun getItemCount(): Int {
        return oldList.size
    }
    var onButtonClick:((note:Note)-> Unit)?=null
    var onItemClick:((note:Note)-> Unit)?=null
    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val tvName : TextView = itemView.findViewById(R.id.tv_note_name)
        private val tvText : TextView = itemView.findViewById(R.id.tv_note_text)
        private val tvDate: TextView =itemView.findViewById(R.id.tv_date)
        private val btnStuff: Button =itemView.findViewById(R.id.btn_stuff)
        private val cardRoot: ConstraintLayout =itemView.findViewById(R.id.card_root)

        fun bind(note: Note?) {
            note?.let {
                tvName.text = it.name
                tvText.text = it.text
                tvDate.text = it.date
            }
            btnStuff.setOnClickListener {
                if (note != null) {
                    onButtonClick?.invoke(note)
                }
            }
            cardRoot.setOnClickListener {
                if (note != null) {
                    onItemClick?.invoke(note)
                }
            }



        }

    }

    fun setData(newList: List<Note>) {
        val diffUtil= NoteDiffUtilCallBack(oldList,newList)
        val diffResult= DiffUtil.calculateDiff(diffUtil)
        oldList=newList
        diffResult.dispatchUpdatesTo(this)
    }
}