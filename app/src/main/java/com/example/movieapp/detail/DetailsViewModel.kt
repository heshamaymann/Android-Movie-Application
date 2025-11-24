package com.example.movieapp.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.core.Resource
import com.example.movieapp.features.fragment.ItemCharacter
import com.example.movieapp.networks.MovieApi
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel(){
    val data: MutableLiveData<Resource<ItemCharacter>> = MutableLiveData(null)


    fun getCharDetail(id:Int){
        data.postValue(Resource.Progress(true))
        viewModelScope.launch (handler()) {
            try {
                val result = MovieApi.retrofitservice.getCharDetails(id).await()
                data.value = Resource.Success(result)
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
