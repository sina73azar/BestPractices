package com.example.hiltimpl.presentation.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hiltimpl.R
import com.example.hiltimpl.databinding.FragmentListNotesBinding
import com.example.hiltimpl.domain.Note
import com.example.hiltimpl.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListNotesFragment : Fragment() {
    lateinit var binding: FragmentListNotesBinding
    val listNoteViewModel:ListNoteViewModel by viewModels()
    private val noteAdapter by lazy { NoteAdapter() }
    var fakeNoteList: List<Note>? = null
    lateinit var slowRotate: Animation

    var isStarRotationStarted=false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding= FragmentListNotesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDataStore()
        onClicks()

        initNoteList()
        binding.rvNotes.adapter = noteAdapter
        noteAdapter.setData(fakeNoteList!!)
        noteAdapter.onButtonClick={
            Log.d(MainActivity.TAG, "button clicked for note name ${it.name}")
        }
        noteAdapter.onItemClick={
            listNoteViewModel.setCurNote(it.name)
            Log.d(MainActivity.TAG, "item click ${it.name}")
        }
        observe()
        slowRotate= AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_slow)
        binding.tvShow.setOnClickListener {
            val rvNotes=binding.rvNotes
            rvNotes.visibility= View.INVISIBLE
            Handler(Looper.getMainLooper()).postDelayed({
                rvNotes.visibility= View.VISIBLE
                rvNotes.startLayoutAnimation()
            },200)
            if (::slowRotate.isInitialized) {
                Log.d(MainActivity.TAG, "onCreate: is slowrotate started ${slowRotate.hasStarted()}")
                if (isStarRotationStarted) {
                    Log.d(MainActivity.TAG, "onCreate: cancel animation")
                    binding.imgStar.clearAnimation()
                    isStarRotationStarted=false
                } else {
                    Log.d(MainActivity.TAG, "onCreate: start animation")
                    binding.imgStar.startAnimation(slowRotate)
                    isStarRotationStarted=true
                }
            }

        }
        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_listNotesFragment_to_loginFragment)
        }
    }

    private fun onClicks() {
        binding.btnDragDrop.setOnClickListener {
            findNavController().navigate(R.id.action_listNotesFragment_to_dragAndDropQuestionFragment)
        }
        binding.imgStar.setOnClickListener {
            findNavController().navigate(R.id.dragDropFragment)
        }

    }

    private fun initDataStore() {

    }

    private fun initNoteList() {
        fakeNoteList = listOf(
            Note( name = "shomare1", text = "hey how you doing", ),
            Note(name = "shomare2", text = "hey fdghkdfg sfldfing", ),
            Note(name = "shomare3", text = "hey fdghkdfg sfldfing", ),
            Note( name = "shomare4",text =  "hdfgdfgsadfasd sfldfing", ),
            Note(name = "shomare5", text =  "rtytytret ertre tfldfing", ),
        )

    }
    private fun observe() {
        listNoteViewModel.curNote.observe(viewLifecycleOwner){
            binding.curNote=it
        }
    }

}