package com.example.desafiowebservicesdigitalhouse.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafiowebservicesdigitalhouse.api.MarvelComicsService
import com.example.desafiowebservicesdigitalhouse.data.ComicsResponseWrapper
import kotlinx.coroutines.launch

private const val SPIDER_MAN_ID = 1009610

class ComicListViewModel(val repository: MarvelComicsService) : ViewModel() {
    val comicList = MutableLiveData<ComicsResponseWrapper>()

    fun getSpiderManComics() {
        getComicsByCharacterId(SPIDER_MAN_ID)
    }

    fun getComicsByCharacterId(characterId: Int) {
        viewModelScope.launch {
            comicList.value = repository.getComicDataByCharacterId(characterId)
        }
    }
}
