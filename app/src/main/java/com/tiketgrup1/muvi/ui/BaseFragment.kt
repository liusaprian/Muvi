package com.tiketgrup1.muvi.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.tiketgrup1.muvi.R
import com.tiketgrup1.muvi.databinding.FragmentBaseBinding

class BaseFragment : Fragment() {

    private lateinit var binding: FragmentBaseBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentBaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var currentFragment = "Home"
        childFragmentManager.beginTransaction()
            .replace(binding.base.id, HomeFragment())
            .commit()

        binding.bottomNav.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.home_page -> {
                    if(currentFragment == "Home") return@setOnItemSelectedListener true
                    currentFragment = "Home"
                    childFragmentManager.beginTransaction()
                        .replace(binding.base.id, HomeFragment())
                        .commit()
                    true
                }
                R.id.bookmark_page -> {
                    currentFragment = "Bookmark"
//                    childFragmentManager.beginTransaction()
//                        .replace(binding.base.id, BookmarkFragment())
//                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}