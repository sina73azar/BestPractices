package com.example.hiltimpl

import android.content.ClipData
import android.content.ClipDescription
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.compose.ui.graphics.Color
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
        binding.dragView1.setImageResource(R.drawable.ic_launcher_foreground)
        binding.dragView2.setImageResource(R.drawable.ic_launcher_foreground)
        binding.dragView3.setImageResource(R.drawable.ic_launcher_foreground)
        binding.dragView4.setImageResource(R.drawable.ic_launcher_foreground)
        binding.llTop.setOnDragListener(dragListener)
        binding.llBottom.setOnDragListener(dragListener)
        binding.dragView1.setOnLongClickListener {
            val clipText = "11"
            val item = ClipData.Item(clipText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(clipText, mimeTypes, item)

            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)
            it.visibility=View.INVISIBLE
            true
        }
        binding.dragView2.setOnLongClickListener {
            val clipText = "22"
            val item = ClipData.Item(clipText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(clipText, mimeTypes, item)

            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)
            it.visibility=View.INVISIBLE
            true
        }
        binding.dragView3.setOnLongClickListener {
            val clipText = "33"
            val item = ClipData.Item(clipText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(clipText, mimeTypes, item)

            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)
            it.visibility=View.INVISIBLE
            true
        }
        binding.dragView4.setOnLongClickListener {
            val clipText = "44"
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
                destination.gravity=Gravity.CENTER
                val textView=TextView(requireContext())
                textView.text=item.text
                textView.setTextColor(R.color.black)
                destination.addView(textView)
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