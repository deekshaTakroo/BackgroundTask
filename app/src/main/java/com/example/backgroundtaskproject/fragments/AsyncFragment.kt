package com.example.backgroundtaskproject.fragments

import android.os.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.backgroundtaskproject.R
import kotlinx.android.synthetic.main.fragment_layout.view.*


class AsyncFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_layout, container, false)

        view.fragmentRlayout.setBackgroundColor(
            ContextCompat.getColor(
                activity!!,
                R.color.lightPurple
            )
        )

        CountDownTimerAsync(view).execute()

        return view
    }


}

private class CountDownTimerAsync(val view: View) : AsyncTask<String, String, String>() {
    lateinit var countDownTimer: CountDownTimer

    override fun doInBackground(vararg p0: String?): String {
        Handler(Looper.getMainLooper()).post {
            val countDownTimer = object : CountDownTimer(10000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    publishProgress((millisUntilFinished / 1000).toString())
                }

                override fun onFinish() {
                }
            }.start()
        }
        return ""
    }

    override fun onProgressUpdate(vararg values: String?) {
        super.onProgressUpdate(*values)
        view.counDownTimerTv.text = values[0].toString()
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        view.counDownTimerTv.text = view.context.getString(R.string.task_executed)
    }
}