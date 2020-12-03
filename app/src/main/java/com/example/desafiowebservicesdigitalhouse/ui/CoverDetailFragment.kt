package com.example.desafiowebservicesdigitalhouse.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.desafiowebservicesdigitalhouse.R
import com.example.desafiowebservicesdigitalhouse.databinding.FragmentCoverDetailBinding
import com.squareup.picasso.Picasso

class CoverDetailFragment : Fragment() {
    private val args: CoverDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCoverDetailBinding.inflate(inflater, container, false)

        val navController = findNavController()
        val imgUrl = args.imgUrl

        binding.toolbar.setNavigationOnClickListener {
            navController.navigateUp()
        }

        Picasso.get().load(imgUrl).fit().centerInside().into(binding.coverFull)

        return binding.root
    }

}