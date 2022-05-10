package com.memo.mybookmarks

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.memo.mybookmarks.adapter.BookmarksItemsAdapter
import com.memo.mybookmarks.model.Bookmark
import com.memo.mybookmarks.databinding.FragmentBookmarksListBinding


class BookmarksListFragment : Fragment() {
    private var _binding:FragmentBookmarksListBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView:RecyclerView
    private lateinit var fabButton:FloatingActionButton
    //Data for new or edited bookmark
    private lateinit var titleBookmark: String
    private lateinit var descriptionBookmark: String
    private var bookmarkSelectedIndex = 0
    private val sharedViewModel: BookmarksViewModel by activityViewModels()
    private lateinit var bookmarksList: MutableList<Bookmark>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookmarksListBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.rvBookmarks
        recyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        //Getting list of Bookmarks from sharedpreferences for the recyclerView
        var dataset = sharedViewModel.getBookmarks(this.requireContext())?.toMutableList()
        Log.d("preferences","data from preferences is charged")
        //We set the adapter for the recyclerView , with data obtained before
        val recyclerViewAdapter = BookmarksItemsAdapter(dataset!!) { indexBookmark ->
            //Implement clickListener for list items
            //'indexBookmark' is the viewholder position and in consequence bookmark position too

            //Obtaining the data to edit the bookmark
            val titleBookmark = dataset!![indexBookmark].title
            val descriptionBookmark = dataset!![indexBookmark].description
            //Navigate to EditBookmarkFragment passing data to edit that bookmark
            val action = BookmarksListFragmentDirections.actionBookmarksListFragmentToEditBookmarkFragment(titleBookmark, descriptionBookmark,indexBookmark)
            findNavController().navigate(action)
        }
        recyclerView.adapter = recyclerViewAdapter
        //We set an observer to bookmarksList of ViewModel
        /*sharedViewModel.getBookmarks().observe(viewLifecycleOwner) {
            When the data changes, the adapter of recyclerView
            is updated with the new data
            Log.d("Recyclerview","Observer initiated")
            val dataset1 = sharedViewModel.bookmarksLiveData.value?.toMutableList()

            recyclerViewAdapter.updateList(dataset1!!)
        }*/

        fabButton = binding.fabCreateNewBookmark
        fabButton.setOnClickListener{
            val action = BookmarksListFragmentDirections.actionBookmarksListFragmentToCreateNewBookmarkFragment()
            findNavController().navigate(action)
            Toast.makeText( this.requireContext(),"Creating a new bookmark", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
