package com.dicoding.picodiploma.storyapp.data.retrofit

import com.dicoding.picodiploma.storyapp.data.response.StoryResponse
import com.dicoding.picodiploma.storyapp.data.response.UploadResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface StoryApiService {
    @GET("stories")
    suspend fun getStories(
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 20
    ): StoryResponse

@Multipart
@POST("stories")
suspend fun postStory(
    @Part file: MultipartBody.Part,
    @Part("description") description: RequestBody
): UploadResponse

@GET("stories")
suspend fun getStoriesWithLocation(
    @Query("location") location : Int = 1,
    ): StoryResponse
}
