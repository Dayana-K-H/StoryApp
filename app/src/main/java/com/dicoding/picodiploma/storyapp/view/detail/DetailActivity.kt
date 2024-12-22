package com.dicoding.picodiploma.storyapp.view.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.storyapp.databinding.ActivityDetailBinding


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val storyName = intent.getStringExtra("storyName")
        val storyDescription = intent.getStringExtra("storyDescription")
        val storyPhotoUrl = intent.getStringExtra("storyPhotoUrl")

        binding.apply {
            tvDetailName.text = storyName
            tvDetailDescription.text = storyDescription
        }

        Glide.with(this)
            .load(storyPhotoUrl)
            .into(binding.ivDetailPhoto)
    }
}


