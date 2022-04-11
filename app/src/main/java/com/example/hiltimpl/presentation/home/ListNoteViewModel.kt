package com.example.hiltimpl.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiltimpl.repo.NoteRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ListNoteViewModel @Inject constructor(
    private val noteRepo: NoteRepo
):ViewModel(){

    private var _curNote: MutableLiveData<String> = MutableLiveData()
    val curNote: LiveData<String> = _curNote
    fun setCurNote(noteName:String){
        _curNote.value=noteName
    }
}