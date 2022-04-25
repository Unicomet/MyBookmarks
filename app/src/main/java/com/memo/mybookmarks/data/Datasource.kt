package com.memo.mybookmarks.data


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/* Handles operations on bookmarksLiveData and holds details about it. */
class Datasource {

    private val bookmarks = mutableListOf<Bookmark>(
            Bookmark("Computer Science Distilled", "Bases de datos - Normalización "),
            Bookmark("Art of Electronics", "Creating a GPS"),
            Bookmark("The Hobbit", "When the hobbit hides from"),
            Bookmark("1How to solve problems", "Test with cases"),
            Bookmark("H2ow to solve problems", "Test with cases"),
            Bookmark("Ho3w to solve problems", "Test with cases"),
            Bookmark("How4 to solve problems", "Test with cases"),
            Bookmark("How 5to solve problems", "Test with cases"),
        )

    private val bookmarksLiveData = MutableLiveData(bookmarks)

    fun addBookmark(title: String, description: String){
        val bookmark = Bookmark(title, description)
        val currentList = bookmarksLiveData.value
        if(currentList ==null){
            bookmarksLiveData.postValue(mutableListOf(bookmark))
        }else{
            val updatedList = currentList.toMutableList()
            updatedList.add(0,bookmark)
            bookmarksLiveData.postValue(updatedList)
        }
    }

    fun updateBookmark(index: Int, title: String, description: String) {
        Log.d(
            "dataset",
            "Bookmark changed initiated, title: $title, description: $description"
        )
        val updatedList = bookmarksLiveData.value?.toMutableList()
        if(updatedList != null){
            //This update the item of the list of Bookmarks
            updatedList[index].title = title
            updatedList[index].description = description
            //Helps to check if the title and description were changed successfully
            Log.d("dataset", "title: ${updatedList[index].title}")
            Log.d("dataset", "description: ${updatedList[index].description}")
            bookmarksLiveData.postValue(updatedList)
        }
    }

    fun getBookmarksList(): MutableLiveData<MutableList<Bookmark>> {
        return bookmarksLiveData
    }
}
