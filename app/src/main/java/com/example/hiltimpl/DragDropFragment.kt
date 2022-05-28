package com.example.hiltimpl

import android.content.ClipData
import android.content.ClipDescription
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.example.hiltimpl.databinding.FragmentDragDropBinding


class DragDropFragment : Fragment() {
    lateinit var binding: FragmentDragDropBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding=FragmentDragDropBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dragView1.setImageResource(R.drawable.gooloole)
        binding.dragView2.setImageResource(R.drawable.goodoole)
        binding.llTop.setOnDragListener(dragListener)
        binding.llBottom.setOnDragListener(dragListener)
        binding.dragView1.setOnLongClickListener {
            val clipText = "gooloole"
            val item = ClipData.Item(clipText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(clipText, mimeTypes, item)

            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)
            it.visibility=View.INVISIBLE
            true
        }
        binding.dragView2.setOnLongClickListener {
            val clipText = "goodoole"
            val item = ClipData.Item(clipText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(clipText, mimeTypes, item)

            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)
            it.visibility=View.INVISIBLE
            true
        }

    }
    val dragListener=View.OnDragListener { view, dragEvent ->
        when (dragEvent.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                Log.d("TAG", "onViewCreated: ACTION_DRAG_STARTED")
                true
            }
            DragEvent.ACTION_DRAG_ENTERED -> {
                Log.d("TAG", "onViewCreated: ACTION_DRAG_ENTERED")
                view.invalidate()
                true
            }
            DragEvent.ACTION_DRAG_LOCATION -> {
                Log.d("TAG", "onViewCreated: ACTION_DRAG_LOCATION")
                true
            }
            DragEvent.ACTION_DRAG_EXITED -> {
                Log.d("TAG", "onViewCreated: ACTION_DRAG_EXITED")
                view.invalidate()
                true
            }
            DragEvent.ACTION_DROP -> {
                Log.d("TAG", "onViewCreated: ACTION_DROP")
                val item = dragEvent.clipData.getItemAt(0)
                Toast.makeText(requireContext(), "${item.text}", Toast.LENGTH_SHORT).show()
                view.invalidate()
                val v = dragEvent.localState as View
                val owner = v.parent as ViewGroup
                owner.removeView(v)
                val destination = view as LinearLayout
                destination.addView(v)
                v.visibility = View.VISIBLE
                true
            }
            DragEvent.ACTION_DRAG_ENDED -> {
                view.invalidate()
                true
            }
            else -> {
                false
            }
        }
    }
}