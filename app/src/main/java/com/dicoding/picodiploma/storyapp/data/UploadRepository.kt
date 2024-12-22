package com.dicoding.picodiploma.storyapp.data

import androidx.lifecycle.liveData
import com.dicoding.picodiploma.storyapp.data.response.UploadResponse
import com.dicoding.picodiploma.storyapp.data.retrofit.StoryApiService
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException
import java.io.File


class UploadRepository private constructor(
    private val apiService: StoryApiService
) {

    fun uploadImage(imageFile: File, description: String) = liveData {
        emit(Result.Loading)
        val requestBody = description.toRequestBody("text/plain".toMediaType())
        val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
        val multipartBody = MultipartBody.Part.createFormData(
            "photo",
            imageFile.name,
            requestImageFile
        )
        try {
            val successResponse = apiService.postStory(multipartBody, requestBody)
            emit(Result.Success(successResponse))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, UploadResponse::class.java)
            emit(Result.Error(errorResponse.message))
        }

    }


    companion object {
        fun getInstance(
            apiService: StoryApiService
        ): UploadRepository {
            return UploadRepository(apiService)
        }
    }
}