package com.example.hiltimpl.presentation

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.example.hiltimpl.domain.Note

import com.example.hiltimpl.domain.Photo
import com.example.hiltimpl.domain.Student
import com.example.hiltimpl.repo.NoteRepo
import com.example.hiltimpl.repo.PhotoRepo
import com.example.hiltimpl.repo.StudentRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val studentRepositoryImpl: StudentRepositoryImpl,
    private val photoRepo: PhotoRepo,
    private val noteRepo: NoteRepo
) : ViewModel() {
    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/photos"
    }

    private var _allStudents = MutableLiveData<List<Student>>()
    val allStudents: LiveData<List<Student>> = _allStudents
    fun setAllStudents(list: List<Student>) {
        _allStudents.value = list
    }

    //    private var _allPhotos = MutableLiveData<List<Photo>>()
//    val allPhotos:LiveData<List<Photo>> = _allPhotos
//    fun setAllPhotos(list:List<Photo>){
//        _allPhotos.value=list
//    }
    private var _allPhotosString = MutableLiveData<String>()
    val allPhotosString: LiveData<String> = _allPhotosString
    fun setAllPhotos(str: String) {
        _allPhotosString.value = str
    }

    fun getAllStudents() {
        viewModelScope.launch {
            _allStudents.postValue(studentRepositoryImpl.getAllStudent())

        }
    }

    fun insertStudent(student: Student) {
        viewModelScope.launch {
            studentRepositoryImpl.insertStudent(student)
        }
    }

    val allNotes: LiveData<List<Note>> = liveData(context = Dispatchers.Default) {
        emit(noteRepo.getAllNotes())
    }

    fun insertNote(note: Note) {
        viewModelScope.launch {
            noteRepo.insertNote(note)
        }
    }



    private var _curNote: MutableLiveData<String> = MutableLiveData()
    val curNote: LiveData<String> = _curNote
    fun setCurNote(noteName:String){
        _curNote.value=noteName
    }


}
