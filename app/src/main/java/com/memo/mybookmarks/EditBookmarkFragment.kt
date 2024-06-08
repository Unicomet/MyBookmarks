package com.memo.mybookmarks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.memo.mybookmarks.databinding.FragmentEditBookmarkBinding

class EditBookmarkFragment: Fragment() {
    private var _binding:FragmentEditBookmarkBinding? = null
    private val binding get() = _binding!!
    private lateinit var fabCheckedEditBookmark:FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditBookmarkBinding.inflate(inflater,container, false)
        return binding.root//view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fabCheckedEditBookmark = binding.fabCheckedEditBookmark
        fabCheckedEditBookmark.setOnClickListener{
            Toast.makeText(this.requireContext(), "Bookmark edited", Toast.LENGTH_SHORT).show()
            val action =  EditBookmarkFragmentDirections.actionEditBookmarkFragmentToBookmarksListFragment()
            this.findNavController().navigate(action)
        }
    }



}