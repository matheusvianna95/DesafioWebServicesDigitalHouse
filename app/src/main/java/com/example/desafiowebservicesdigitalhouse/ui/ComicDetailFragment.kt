package com.example.desafiowebservicesdigitalhouse.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.desafiowebservicesdigitalhouse.R
import com.example.desafiowebservicesdigitalhouse.databinding.FragmentComicDetailBinding
import com.example.desafiowebservicesdigitalhouse.databinding.FragmentComicListBinding
import com.squareup.picasso.Picasso

class ComicDetailFragment : Fragment() {

    private lateinit var binding: FragmentComicDetailBinding
    private val args: ComicDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentComicDetailBinding.inflate(inflater, container, false)

        val navController = findNavController()

        // Comments represent the parameters being passed individually
        val comicTitleText = args.comicResponseResult.title //args.comicTitle
        val comicDescriptionText = args.comicResponseResult.description //args.comicDescription
        val comicPriceText = "$${args.comicResponseResult.prices.first { i -> i.type == "printPrice" }.price.toFloat()}" //"$${args.comicPrice}"
        val pageCountText = args.comicResponseResult.pageCount.toString() //args.pageCount.toString()
        val imgUrl = "${args.comicResponseResult.thumbnail.path}.${args.comicResponseResult.thumbnail.extension}" //args.imgUrl
        val publicationDate = args.comicResponseResult.dates.first { i -> i.type == "focDate" }.date.substring(0, 10) //args.publicationDate

        binding.toolbar.setNavigationOnClickListener {
            navController.navigateUp()
        }

        binding.imageCover.setOnClickListener {
            navController.navigate(
                ComicDetailFragmentDirections.actionComicDetailFragmentToCoverDetailFragment(
                    imgUrl
                )
            )
        }

        binding.comicTitle.text = comicTitleText
        binding.comicDescription.text = comicDescriptionText
        binding.comicPrice.text = comicPriceText
        binding.pageCount.text = pageCountText
        binding.publicationDate.text = publicationDate

        // Loads banner image
        Picasso.get().load(imgUrl).fit().centerCrop().into(binding.imageBanner)

        //Loads cover image
        Picasso.get().load(imgUrl).fit().centerInside().into(binding.imageCover)

        return binding.root
    }

}