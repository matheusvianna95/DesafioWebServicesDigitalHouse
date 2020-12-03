package com.example.desafiowebservicesdigitalhouse.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.desafiowebservicesdigitalhouse.R
import com.example.desafiowebservicesdigitalhouse.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLoginBinding.inflate(inflater, container,false)

        val navController = findNavController()

        binding.textView2.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.materialButton.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_comicListFragment)
        }

        return binding.root
    }

}