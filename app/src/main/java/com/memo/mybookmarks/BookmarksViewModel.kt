package com.memo.mybookmarks

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.memo.mybookmarks.model.Bookmark
import com.memo.mybookmarks.provider.PreferencesKey
import com.memo.mybookmarks.provider.PreferencesProvider

class BookmarksViewModel: ViewModel() {
    //Get bookmarks from Datasource
    //We should use object to use "singletone" design pattern??
    //I have to add dependency "Data binding" to Gradle
    private val preferencesProvider = PreferencesProvider

    fun getBookmarks(context:Context): List<Bookmark> {
        val bookmarks = preferencesProvider.getList(context, PreferencesKey.LIST_BOOKMARKS)
        return bookmarks
    }

    fun updateBookmarks(context: Context, bookmarks: List<Bookmark>){
        preferencesProvider.setList(context,PreferencesKey.LIST_BOOKMARKS,bookmarks)
    }

    fun clearBookmarks(context: Context){
        preferencesProvider.clear(context)
    }

    fun updateBookmark(context: Context, index:Int, title: String, description: String) {
            preferencesProvider.editBookmark(context,PreferencesKey.LIST_BOOKMARKS,index, title,description )
    }

    fun addBookmark(context: Context,title: String, description: String ){
        preferencesProvider.addBookmark(context,PreferencesKey.LIST_BOOKMARKS, title, description)
    }

}