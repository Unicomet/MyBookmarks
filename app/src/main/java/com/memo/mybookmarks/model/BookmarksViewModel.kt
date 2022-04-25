package com.memo.mybookmarks.model

import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.memo.mybookmarks.data.Bookmark
import com.memo.mybookmarks.data.Datasource

class BookmarksViewModel: ViewModel() {
    //Get bookmarks from Datasource
    //We should use object to use "singletone" design pattern??
    //I have to add dependency "Data binding" to Gradle
    private val dataSource: Datasource = Datasource()
    val bookmarksLiveData: LiveData<MutableList<Bookmark>> = dataSource.getBookmarksList()



    fun updateBookmark(index: Int, title: String, description: String) {
            dataSource.updateBookmark(index,title,description)
    }

    fun addBookmark(title: String, description: String){
        dataSource.addBookmark(title,description)
    }

    fun getBookmarks(): LiveData<MutableList<Bookmark>>{
        return bookmarksLiveData
    }
}