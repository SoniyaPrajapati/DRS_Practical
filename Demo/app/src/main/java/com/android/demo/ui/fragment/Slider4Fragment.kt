package com.android.demo.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.demo.R
import com.android.demo.databinding.ActivityMainBinding
import com.android.demo.databinding.FragmentSlider4Binding
import com.android.demo.ui.activity.HomeActivity
import com.android.demo.ui.activity.MainActivity


class Slider4Fragment : Fragment() , View.OnClickListener{

    private lateinit var binding: FragmentSlider4Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSlider4Binding.inflate(layoutInflater)

        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivNext.setOnClickListener(this)


    }
    override fun onClick(p0: View?) {
        when (p0) {
            binding.ivNext -> {

                startActivity(Intent(context, HomeActivity::class.java))


            }

        }
    }
}