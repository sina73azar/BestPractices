package com.example.hiltimpl.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.example.hiltimpl.R
import com.example.hiltimpl.databinding.ActivityMainBinding
import com.example.hiltimpl.domain.Note
import com.example.hiltimpl.presentation.home.NoteAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val model: MainViewModel by viewModels()

    companion object {
        const val TAG = "MainActivity"
    }

    lateinit var tvShow: TextView
    lateinit var rvNotes: RecyclerView
    var fakeNoteList: List<Note>? = null
    private val noteAdapter by lazy { NoteAdapter() }
    private var queue: RequestQueue? = null
    private var stringRequest: StringRequest? = null

    lateinit var slowRotate:Animation
    var isStarRotationStarted=false
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        slowRotate=AnimationUtils.loadAnimation(this,R.anim.rotate_slow)

        model.getAllStudents()
        observe()


        val list = listOf<String>("sina", "hasan", "naghi")
        val mrList = list.map {
            "mr $it"
        }

    }

    override fun onStart() {
        super.onStart()
    }


    private fun observe() {

    }
}