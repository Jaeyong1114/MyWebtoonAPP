package com.example.mywebtoonapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mywebtoonapp.databinding.FragmentSecondBinding
import com.example.mywebtoonapp.databinding.FragmentWebviewBinding

class BFragmnet: Fragment() {
    private lateinit var binding : FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater)
        return binding.root

    }

}