package com.example.hiltimpl.presentation.drag_drop

import android.content.ClipData
import android.content.ClipDescription
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.example.hiltimpl.R
import com.example.hiltimpl.data.dto.DragAndDropBucketItemsDto
import com.example.hiltimpl.data.dto.QuestionDto
import com.example.hiltimpl.databinding.FragmentDragAndDropQuestionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DragAndDropQuestionFragment : Fragment() {
    private val viewModel: DragAndDropQuestionViewModel by viewModels()
    lateinit var binding: FragmentDragAndDropQuestionBinding
    lateinit var question: QuestionDto
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDragAndDropQuestionBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        question = viewModel.getQuestion()
        question.dragAndDropBucketOptions?.get(0)?.let {
            setupBucket(it.PhotoUrl)
            setupInitialPosition(it.DragAndDropBucketItems)
        }
        onLongClick()
        setupDragListener()
    }

    private fun setupDragListener() {
        binding.rlBucket.setOnDragListener(dragListenerBucket)
        binding.root.setOnDragListener(dragListenerContainer)
    }

    private fun onLongClick() {
        val items = question.dragAndDropBucketOptions?.get(0)?.DragAndDropBucketItems
        binding.item1.setOnLongClickListener {

            startDragDrop(it,"")
            true
        }
        binding.item2.setOnLongClickListener {
            val clipText = items?.get(1)?.title
            startDragDrop(it,"0")
            true
        }
        binding.item3.setOnLongClickListener {
            startDragDrop(it,"0")
            true
        }
        binding.item4.setOnLongClickListener {
            startDragDrop(it,"0")
            true
        }
        binding.item5.setOnLongClickListener {
            startDragDrop(it,"0")
            true
        }
        binding.item6.setOnLongClickListener {
            val clipText = items?.get(1)?.title
            startDragDrop(it,"0")
            true
        }
        binding.item7.setOnLongClickListener {
            val clipText = items?.get(1)?.title
            startDragDrop(it,"0")
            true
        }
    }
    private fun startDragDrop(view: View, text: String) {
        val item = ClipData.Item(text)
        val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
        val data = ClipData(text, mimeTypes, item)

        val dragShadowBuilder = View.DragShadowBuilder(view)
        view.startDragAndDrop(data, dragShadowBuilder, view, 0)
        view.visibility = View.INVISIBLE
    }
    val dragListenerContainer = View.OnDragListener { view, event ->
        if (event.action == DragEvent.ACTION_DROP) {
            val dragView = event.localState as View
            dragView.visibility=View.VISIBLE
         /*   when (dragView.id) {
                R.id.item1 -> {
                    binding.item1.visibility = View.VISIBLE
                }
                R.id.item2 -> {
                    binding.item2.visibility = View.VISIBLE
                }
                R.id.item3 -> {
                    binding.item3.visibility = View.VISIBLE
                }
                R.id.item4 -> {
                    binding.item4.visibility = View.VISIBLE
                }
                R.id.item5 -> {
                    binding.item5.visibility = View.VISIBLE
                }
                R.id.item6 -> {
                    binding.item6.visibility = View.VISIBLE
                }
                R.id.item7 -> {
                    binding.item7.visibility = View.VISIBLE
                }
            }*/
            binding.rlBucket.background = null
        }
        true
    }
    val dragListenerBucket = View.OnDragListener { view, dragEvent ->
        when (dragEvent.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                binding.rlBucket.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.background_dashed_bucket)
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
                val items = question.dragAndDropBucketOptions?.get(0)?.DragAndDropBucketItems
                val v = dragEvent.localState as View
                val isInTheBucketList = true
                if (isInTheBucketList) {
                    v.visibility=View.INVISIBLE
                    when (v.id) {
                        R.id.item1 ->{
                            binding.item11.visibility = View.VISIBLE
                        }
                        R.id.item2 ->{
                            binding.item22.visibility = View.VISIBLE
                        }
                        R.id.item3 ->{
                            binding.item33.visibility = View.VISIBLE
                        }
                        R.id.item4 ->{
                            binding.item44.visibility = View.VISIBLE
                        }
                        R.id.item5 ->{
                            binding.item55.visibility = View.VISIBLE
                        }
                        R.id.item6 ->{
                            binding.item66.visibility = View.VISIBLE
                        }
                        R.id.item7 ->{
                            binding.item77.visibility = View.VISIBLE
                        }
                    }
                }
                binding.rlBucket.background = null
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

    private fun setupBucket(photoUrl: String?) {
        binding.imgBucket.setImageResource(R.drawable.ic_sabad)
        binding.imgBucket.bringToFront()
    }

    private fun setupInitialPosition(items: List<DragAndDropBucketItemsDto>) {
        binding.apply {
            item1.background=ContextCompat.getDrawable(requireContext(),R.drawable.kiwi_fruit_kiwi)
            item11.background=ContextCompat.getDrawable(requireContext(),R.drawable.kiwi_fruit_kiwi)

            item2.background=ContextCompat.getDrawable(requireContext(),R.drawable.angoor)
            item22.background=ContextCompat.getDrawable(requireContext(),R.drawable.angoor)
            item3.background=ContextCompat.getDrawable(requireContext(),R.drawable.pears)
            item33.background=ContextCompat.getDrawable(requireContext(),R.drawable.pears)
            item4.background=ContextCompat.getDrawable(requireContext(),R.drawable.watermelon)
            item44.background=ContextCompat.getDrawable(requireContext(),R.drawable.watermelon)
            item5.background=ContextCompat.getDrawable(requireContext(),R.drawable.orange_fruit_vi)
            item55.background=ContextCompat.getDrawable(requireContext(),R.drawable.orange_fruit_vi)
            item6.background=ContextCompat.getDrawable(requireContext(),R.drawable.red_strawberrie)
            item66.background=ContextCompat.getDrawable(requireContext(),R.drawable.red_strawberrie)
            item7.background=ContextCompat.getDrawable(requireContext(),R.drawable.apple_eat_fruit)
            item77.background=ContextCompat.getDrawable(requireContext(),R.drawable.apple_eat_fruit)


        }
    }


}