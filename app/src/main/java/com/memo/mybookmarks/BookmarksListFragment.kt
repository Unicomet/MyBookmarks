package com.memo.mybookmarks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.memo.mybookmarks.adapter.BookmarksItemsAdapter
import com.memo.mybookmarks.data.Datasource
import com.memo.mybookmarks.databinding.FragmentBookmarksListBinding


class BookmarksListFragment : Fragment() {
    private var _binding:FragmentBookmarksListBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView:RecyclerView
    private lateinit var fabButton:FloatingActionButton



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
        val myDataSet = Datasource().loadBookmarks()
        recyclerView.adapter = BookmarksItemsAdapter(this.requireContext(),myDataSet)

        fabButton = binding.fabCreateNewBookmark
        fabButton.setOnClickListener{
            Toast.makeText(this.requireContext(), "Create a Bookmark", Toast.LENGTH_SHORT).show()
            val action = BookmarksListFragmentDirections.actionBookmarksListFragmentToCreateNewBookmarkFragment()

            view.findNavController().navigate(action)

        }

    }

}