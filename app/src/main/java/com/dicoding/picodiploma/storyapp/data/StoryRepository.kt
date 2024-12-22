package com.dicoding.picodiploma.storyapp.data

import androidx.lifecycle.LiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.dicoding.picodiploma.storyapp.data.response.ListStoryItem
import com.dicoding.picodiploma.storyapp.data.response.StoryResponse
import com.dicoding.picodiploma.storyapp.data.retrofit.StoryApiService
import com.dicoding.picodiploma.storyapp.database.StoryDatabase
import com.dicoding.picodiploma.storyapp.database.StoryRemoteMediator


class StoryRepository private constructor(
    private val apiService: StoryApiService,
    private val storyDatabase: StoryDatabase
) {
    @OptIn(ExperimentalPagingApi::class)
    fun getStories(): LiveData<PagingData<ListStoryItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            remoteMediator = StoryRemoteMediator(storyDatabase, apiService),
            pagingSourceFactory = {
                storyDatabase.storyDao().getAllStory()
            }
        ).liveData
    }

    suspend fun getStoriesWithLocation(location: Int): StoryResponse {
        return apiService.getStoriesWithLocation(location)
    }

    companion object {
        fun getInstance(
            apiService: StoryApiService,
            storyDatabase: StoryDatabase
        ): StoryRepository {
            return StoryRepository(apiService, storyDatabase)
        }
    }
}
