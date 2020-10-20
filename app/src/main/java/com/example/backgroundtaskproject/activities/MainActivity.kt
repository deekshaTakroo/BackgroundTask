package com.example.backgroundtaskproject.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.backgroundtaskproject.R
import com.example.backgroundtaskproject.fragments.AsyncFragment
import com.example.backgroundtaskproject.fragments.ServicesFragment
import com.example.backgroundtaskproject.fragments.ThreadFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        servicesBt.setOnClickListener(this)
        threadBt.setOnClickListener(this)
        asyncBt.setOnClickListener(this)

        addFragment(ServicesFragment())
    }

    override fun onClick(view: View?) {
        when (view?.id)
        {
            R.id.servicesBt ->
                addFragment(ServicesFragment())
            R.id.threadBt ->
                addFragment(ThreadFragment())
            R.id.asyncBt ->
                addFragment(AsyncFragment())
        }
    }

    private fun addFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,fragment).commit()
    }
}