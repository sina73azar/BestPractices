package com.example.hiltimpl.presentation.photo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.hiltimpl.R
import com.example.hiltimpl.di.events.NetworkEvents
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
    fun onNetworkEvents(e: NetworkEvents) {
        when (e) {
            NetworkEvents.SUCCESS_EVENT->{Log.d(TAG, "onSuccessEvent: "+"با موفقیت جواب اومد...")}
            NetworkEvents.UN_AUTHORIZED_EVENT->{handleUnauthorizedEvent()}

        }
    }
    protected fun handleUnauthorizedEvent() {
        Toast.makeText(requireContext(), "تایید هویت صورت نگرفته", Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val TAG = "PhotoFragment"
    }
}