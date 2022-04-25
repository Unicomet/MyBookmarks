package com.memo.mybookmarks

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.memo.mybookmarks.adapter.BookmarksItemsAdapter
import com.memo.mybookmarks.data.Bookmark
import com.memo.mybookmarks.data.Datasource
import com.memo.mybookmarks.databinding.FragmentBookmarksListBinding
import com.memo.mybookmarks.model.BookmarksViewModel
import javax.sql.DataSource


class BookmarksListFragment : Fragment() {
    private var _binding:FragmentBookmarksListBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView:RecyclerView
    private lateinit var fabButton:FloatingActionButton
    //Data for new or edited bookmark
    private lateinit var titleBookmark: String
    private lateinit var descriptionBookmark: String
    private var bookmarkSelectedIndex = 0
    private val viewModel: BookmarksViewModel by viewModels()
    private lateinit var bookmarksList: MutableList<Bookmark>

    companion object {
        val TITLE = "title_bookmark"
        val DESCRIPTION = "description_bookmark"
        val INDEX = "index"
    }


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

        viewModel.getBookmarks().observe(viewLifecycleOwner) {

            recyclerView.adapter = BookmarksItemsAdapter(it) {
                //Implement clickListener for list items
            }
        }

        fabButton = binding.fabCreateNewBookmark
        fabButton.setOnClickListener{
            Toast.makeText( this.requireContext(),"Create a Bookmark", Toast.LENGTH_SHORT).show()
            viewModel.addBookmark("New", "new")

        }

    }

}