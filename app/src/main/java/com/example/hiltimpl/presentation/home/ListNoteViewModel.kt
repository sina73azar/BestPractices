package com.example.hiltimpl.presentation.home

import androidx.lifecycle.*
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

    private var tokenAcceppted: MutableLiveData<Boolean> = MutableLiveData(false)
    lateinit var tokenStateLiveData:LiveData<Boolean>

/*    fun getTokenState():LiveData<Boolean>{
        Transformations.switchMap()
    }*/
}