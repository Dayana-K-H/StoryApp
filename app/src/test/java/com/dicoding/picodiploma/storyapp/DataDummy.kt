package com.dicoding.picodiploma.storyapp

import com.dicoding.picodiploma.storyapp.data.response.ListStoryItem

object DataDummy {

    fun generateDummyStoryResponse(): List<ListStoryItem> {
        val items: MutableList<ListStoryItem> = arrayListOf()
        for (i in 0..100) {
            val quote = ListStoryItem(
                i.toString(),
                "createdAt $i",
                "name $i",
                "description $i",
                0.0,
                "id $i",
                "author $i",
                1.1,
            )
            items.add(quote)
        }
        return items
    }
}