package com.memo.mybookmarks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.memo.mybookmarks.databinding.FragmentCreateNewBookmarkBinding

class CreateNewBookmarkFragment :Fragment(){

    private var _binding : FragmentCreateNewBookmarkBinding? = null
    private val binding get() = _binding!!
    private lateinit var fabCheckedCreateNewBookmark:FloatingActionButton
    private val sharedViewModel: BookmarksViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateNewBookmarkBinding.inflate(inflater,container,false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fabCheckedCreateNewBookmark = binding.fabCheckedCreateBookmark

        fabCheckedCreateNewBookmark.setOnClickListener{
            Toast.makeText(this.requireContext(),"Bookmark created", Toast.LENGTH_SHORT).show()
            //Getting the data for the bookmark introduced by the user
            val title:String = binding.titleBookmark.text.toString()
            val description:String = binding.descriptionBookmark.text.toString()
            //Update the bookmark to update the adapter
            sharedViewModel.addBookmark(this.requireContext(), title, description)
            //Navigate to BookmarksListFragment
            val action  = CreateNewBookmarkFragmentDirections.actionCreateNewBookmarkFragmentToBookmarksListFragment()
            findNavController().navigate(action)
        }

    }



}


