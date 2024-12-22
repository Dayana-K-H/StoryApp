package com.dicoding.picodiploma.storyapp.view.maps

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.picodiploma.storyapp.data.Result
import com.dicoding.picodiploma.storyapp.data.StoryRepository
import com.dicoding.picodiploma.storyapp.data.response.ErrorResponse
import com.dicoding.picodiploma.storyapp.data.response.StoryResponse
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MapsViewModel( 
    private val storyRepository: StoryRepository
    ) : ViewModel() {
    private val _mapsResult = MutableLiveData<Result<StoryResponse>>()
    val mapsResult: LiveData<Result<StoryResponse>> = _mapsResult

    fun getMapsStories(location: Int) {
        viewModelScope.launch {
            _mapsResult.value = Result.Loading
            try {
                val response = storyRepository.getStoriesWithLocation(location)
                _mapsResult.value = Result.Success(response)
            } catch (e: HttpException) {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
                _mapsResult.value = Result.Error(errorBody.message)
            }
        }
    }
}