package com.example.desafiowebservicesdigitalhouse.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.desafiowebservicesdigitalhouse.R
import com.example.desafiowebservicesdigitalhouse.adapters.ComicListAdapter
import com.example.desafiowebservicesdigitalhouse.api.MarvelComicsService
import com.example.desafiowebservicesdigitalhouse.data.ComicsResponseWrapper
import com.example.desafiowebservicesdigitalhouse.databinding.FragmentComicListBinding
import com.example.desafiowebservicesdigitalhouse.viewmodels.ComicListViewModel

class ComicListFragment : Fragment(), ComicListAdapter.OnItemClickListener {

    private val viewModel: ComicListViewModel by viewModels() {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ComicListViewModel(MarvelComicsService.create()) as T
            }
        }
    }

    private lateinit var binding: FragmentComicListBinding
    private lateinit var comicResponse: ComicsResponseWrapper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentComicListBinding.inflate(inflater, container, false)

        val comicAdapter = ComicListAdapter(null, this)
        val recyclerView = binding.recyclerComicList
        recyclerView.layoutManager = GridLayoutManager(context, 3)
        recyclerView.adapter = comicAdapter


        viewModel.comicList.observe(viewLifecycleOwner) {
            Log.i("ComicList", it.toString())
            comicResponse = it
            comicAdapter.comicResponse = comicResponse
            recyclerView.adapter = comicAdapter
        }

        viewModel.getSpiderManComics()

        return binding.root
    }

    override fun onItemClick(position: Int) {
        val clickedItem = comicResponse.data.results[position]

        NavHostFragment.findNavController(this).navigate(
            ComicListFragmentDirections.actionComicListFragmentToComicDetailFragment(
                "${clickedItem.thumbnail.path}.${clickedItem.thumbnail.extension}",
                clickedItem.title,
                clickedItem.description ?: "No description.", // issue #700 description is null
                clickedItem.prices.first { i -> i.type == "printPrice" }.price.toFloat(),
                clickedItem.pageCount,
                clickedItem.dates.first { i -> i.type == "focDate" }.date.substring(0, 10)
            )
        )
    }

}