package com.example.backgroundtaskproject.fragments

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.backgroundtaskproject.R
import com.example.backgroundtaskproject.utils.CountDownService
import kotlinx.android.synthetic.main.fragment_layout.view.*

class ServicesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_layout, container, false)

        view.fragmentRlayout.setBackgroundColor(
            ContextCompat.getColor(
                activity!!,
                R.color.lightBlue
            )
        )
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            activity?.startForegroundService(Intent(activity, CountDownService::class.java))
        } else {
            activity?.startService(Intent(activity, CountDownService::class.java))
        }

        return view
    }

    private val countDownBr: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            val longmillis = (p1?.getLongExtra("countdown", 0))?.div(1000).toString()
            view?.counDownTimerTv?.text = longmillis
            if (longmillis == "0") {
                view?.counDownTimerTv?.text = getString(R.string.task_executed)
            }

        }

    }

    override fun onResume() {
        super.onResume()
        activity?.registerReceiver(countDownBr, IntentFilter(CountDownService.countdownBr))
    }

    override fun onPause() {
        super.onPause()
        activity?.unregisterReceiver(countDownBr)
    }

    override fun onStop() {
        super.onStop()
        try {
            activity?.unregisterReceiver(countDownBr)
        } catch (e: Exception) {

        }
    }

}