package com.android.demo.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.demo.R
import com.android.demo.adapter.ViewPagerAdapter
import com.android.demo.databinding.ActivityHomeBinding
import com.android.demo.databinding.ActivityMainBinding
import com.android.demo.databinding.FragmentSlider1Binding
import com.android.demo.ui.fragment.Slider1Fragment
import com.android.demo.ui.fragment.Slider2Fragment
import com.android.demo.ui.fragment.Slider3Fragment
import com.android.demo.ui.fragment.Slider4Fragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.viewPager.adapter =
            ViewPagerAdapter(
                this,
                arrayListOf(
                    Slider1Fragment(),
                    Slider2Fragment(),
                    Slider3Fragment(),
                    Slider4Fragment()
                )
            )





    }
}