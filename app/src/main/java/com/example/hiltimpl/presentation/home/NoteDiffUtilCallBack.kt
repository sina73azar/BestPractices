package com.example.hiltimpl.presentation.home

import androidx.recyclerview.widget.DiffUtil
import com.example.hiltimpl.domain.Note
import java.util.concurrent.RecursiveTask

class NoteDiffUtilCallBack(
    private val oldList: List<Note>,
    private val newList: List<Note>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id==newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].id != newList[newItemPosition].id -> false
            oldList[oldItemPosition].name != newList[newItemPosition].name -> false
            oldList[oldItemPosition].text != newList[newItemPosition].text -> false
            oldList[oldItemPosition].date != newList[newItemPosition].date -> false
            else->true
        }
    }
}