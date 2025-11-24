package com.example.movieapp.features.fragment.characters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.core.Resource
import com.example.movieapp.networks.CharResponse
import com.example.movieapp.networks.MovieApi
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel() {
    val data: MutableLiveData<Resource<CharResponse>> = MutableLiveData(null)

init {
    getCharacters()
}

    private fun getCharacters() {
        data.postValue(Resource.Progress(true))
        viewModelScope.launch(handler()) {
            try {
                val response = MovieApi.retrofitservice.getChars().await()
                data.value = Resource.Success(response)
                data.postValue(Resource.Progress(false))
            } catch (e: Exception) {
                data.postValue(Resource.Failure(e.message))
                data.postValue(Resource.Progress(false))
            }
        }
    }

    private fun handler() =
        CoroutineExceptionHandler { _, exception ->
            data.postValue(Resource.Failure(exception.message))
            data.postValue(Resource.Progress(false))

        }

}