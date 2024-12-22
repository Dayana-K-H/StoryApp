package com.dicoding.picodiploma.storyapp.data

import com.dicoding.picodiploma.storyapp.data.pref.UserModel
import com.dicoding.picodiploma.storyapp.data.pref.UserPreference
import com.dicoding.picodiploma.storyapp.data.response.LoginResponse
import com.dicoding.picodiploma.storyapp.data.response.RegisterResponse
import com.dicoding.picodiploma.storyapp.data.retrofit.UserApiService
import kotlinx.coroutines.flow.Flow

class UserRepository private constructor(
    private val userPreference: UserPreference,
    private val apiService: UserApiService,
) {

    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    suspend fun logout() {
        userPreference.logout()
    }

    suspend fun register(name: String, email: String, password: String) : RegisterResponse {
        return apiService.register(name, email, password)
    }

    suspend fun login(email: String, password: String) : LoginResponse {
        val response =  apiService.login(email, password)

        if (!response.error) {
            val user = UserModel(
                userId = response.loginResult.userId,
                name = response.loginResult.name,
                token = response.loginResult.token,
                isLogin = true
            )
            saveSession(user)
        }
        return response
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            userPreference: UserPreference,
            userApiService: UserApiService,
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(userPreference, userApiService)
            }.also { instance = it }
    }
}