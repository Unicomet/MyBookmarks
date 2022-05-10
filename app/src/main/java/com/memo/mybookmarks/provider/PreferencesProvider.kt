package com.memo.mybookmarks.provider

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.memo.mybookmarks.model.Bookmark


enum class PreferencesKey(val value: String) {
    LIST_BOOKMARKS("bookmarks"),
}

//We use singleton pattern  to create just an instance of this class
object PreferencesProvider {

    fun setList(context: Context, key: PreferencesKey, list: List<Bookmark>){
        //Convert List  to a JSON object (Serializing)
        val gson = Gson()
        val json: String = gson.toJson(list)
        //Adding list to preferences as a json string
        val editor = prefs(context).edit()
        editor.putString(key.value,json).apply()
        Log.d("json", json)
    }

    fun getList(context: Context, key: PreferencesKey): List<Bookmark>{
        // Deserialize JSON to List<Bookmark>
        val gson = Gson()
        val json = prefs(context).getString(key.value,null)
        //Managing when it´s null, adapter of recyclerview needs a list
        if(json==null){
            val bookmarksList = listOf<Bookmark>()
            return bookmarksList
        }else{
            val sType = object : TypeToken<List<Bookmark>>(){}.type
            val bookmarksList = gson.fromJson<List<Bookmark>>(json, sType)
            return bookmarksList
        }

    }

    fun addBookmark(context: Context, key: PreferencesKey,title: String,description: String){
        val newBookmark = Bookmark(title,description)
        val bookmarksList = getList(context,key).toMutableList()
        bookmarksList.add(newBookmark)
        setList(context,key,bookmarksList )
    }

    fun editBookmark(context: Context, key: PreferencesKey,index:Int,title: String,description: String){
        val bookmarksList = getList(context,key).toMutableList()
        val bookmarkToEdit = bookmarksList[index]
        bookmarkToEdit.title = title
        bookmarkToEdit.description = description
        setList(context,key,bookmarksList )
    }


    fun clear(context: Context){
        val editor = prefs(context).edit()
        editor.clear().apply()
    }


}

private fun prefs(context:Context):SharedPreferences{
    return PreferenceManager.getDefaultSharedPreferences(context)
}