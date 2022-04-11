package com.example.hiltimpl.presentation.photo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.UiThread
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.hiltimpl.R
import com.example.hiltimpl.di.events.SuccessEvent
import com.example.hiltimpl.di.events.UnauthorizedEvent
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


@AndroidEntryPoint
class PhotoFragment : Fragment() {

//    private val binding:

    private val model: PhotoViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.getAllPhotos().observe(viewLifecycleOwner) {
            Log.d(TAG, "onViewCreated: list photos header------------->${it.headers()}")
            Log.d(TAG, "onViewCreated: list photos body------------->${it.body()}")
        }
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this);
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onUnauthorizedEvent(e: UnauthorizedEvent?) {
        handleUnauthorizedEvent()
    }
    protected fun handleUnauthorizedEvent() {
        Toast.makeText(requireContext(), "تایید هویت صورت نگرفته", Toast.LENGTH_SHORT).show()
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onSuccessEvent(e:SuccessEvent){
        Toast.makeText(requireContext(), "با موفقیت جواب اومد...", Toast.LENGTH_SHORT).show()
        Toast.makeText(requireContext(),e.toString(), Toast.LENGTH_SHORT).show()
    }
    
    companion object {
        private const val TAG = "PhotoFragment"
    }
}