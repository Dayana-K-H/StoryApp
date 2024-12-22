package com.dicoding.picodiploma.storyapp.view.addStory

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.storyapp.data.UploadRepository
import java.io.File

class AddStoryViewModel(
    private val uploadRepository: UploadRepository)
    : ViewModel() {

    private val _imageUri = MutableLiveData<Uri?>()
    val imageUri: LiveData<Uri?> get() = _imageUri

    fun setImageUri(uri: Uri?) {
        _imageUri.value = uri
    }

    fun uploadImage(file: File, description: String) = uploadRepository.uploadImage(file, description)
}