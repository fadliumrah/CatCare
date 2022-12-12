package com.fadli.catcare.ui.dashboard.care

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fadli.catcare.databinding.FragmentCareBinding

class CareFragment : Fragment() {
    private lateinit var binding: FragmentCareBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCareBinding.inflate(inflater, container, false)
        return binding.root
    }

}