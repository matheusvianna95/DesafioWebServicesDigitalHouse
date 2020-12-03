package com.example.desafiowebservicesdigitalhouse.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import com.example.desafiowebservicesdigitalhouse.R
import com.example.desafiowebservicesdigitalhouse.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val navController = findNavController()

        binding.toolbar.setNavigationOnClickListener {
            navController.navigateUp()
        }

        binding.materialButton.setOnClickListener {
            navController.navigate(R.id.action_registerFragment_to_comicListFragment)
        }

        return binding.root
    }

}