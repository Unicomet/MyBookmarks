package com.memo.mybookmarks.data

import com.memo.mybookmarks.R
import com.memo.mybookmarks.model.Bookmark

class Datasource {

    fun loadBookmarks():List<Bookmark>{
        return listOf<Bookmark>(
            Bookmark(R.string.bookmark_title1,R.string.bookmark_description1),
            Bookmark(R.string.bookmark_title2,R.string.bookmark_description2),
            Bookmark(R.string.bookmark_title3,R.string.bookmark_description3),
            Bookmark(R.string.bookmark_title4,R.string.bookmark_description4),
            Bookmark(R.string.bookmark_title5,R.string.bookmark_description5),
            Bookmark(R.string.bookmark_title6,R.string.bookmark_description6),
            Bookmark(R.string.bookmark_title7,R.string.bookmark_description7),
            Bookmark(R.string.bookmark_title8,R.string.bookmark_description8),

//            Bookmark("Computer Science Distilled", "Bases de datos - Normalización "),
//            Bookmark("Art of Electronics","Creating a GPS" ),
//            Bookmark("The Hobbit", "When the hobbit hides from orcs"),
//            Bookmark("How to solve problems", "Test with cases"),
        )
    }
}