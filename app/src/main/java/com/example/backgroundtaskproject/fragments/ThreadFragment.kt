package com.example.backgroundtaskproject.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.backgroundtaskproject.R
import kotlinx.android.synthetic.main.fragment_layout.view.*

class ThreadFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_layout, container, false)

        view.fragmentRlayout.setBackgroundColor(
                ContextCompat.getColor(
                        activity!!,
                        R.color.lightPink
                )
        )

        val countDownTimer = object : CountDownTimer(10000, 1000) {
            override fun onFinish() {
                activity?.runOnUiThread {
                    view?.counDownTimerTv?.text = getString(R.string.task_executed)
                }
            }

            override fun onTick(p0: Long) {
                activity?.runOnUiThread {
                    view.counDownTimerTv.text = (p0 / 1000).toString()
                }
            }
        }

        countDownTimer.start()

        return view
    }
}