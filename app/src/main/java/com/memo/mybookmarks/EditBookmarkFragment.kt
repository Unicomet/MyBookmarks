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
import com.google.android.material.textfield.TextInputEditText
import com.memo.mybookmarks.databinding.FragmentEditBookmarkBinding

class EditBookmarkFragment: Fragment() {
    private var _binding:FragmentEditBookmarkBinding? = null
    private val binding get() = _binding!!
    private lateinit var fabCheckedEditBookmark:FloatingActionButton
    private lateinit var textInputTitle : TextInputEditText
    private lateinit var textInputDescription : TextInputEditText
    private lateinit var title: String
    private lateinit var description:String
    private var bookmarkSelectedIndex: Int = 0 //Set as lateinit var or something like that
    private val sharedViewModel: BookmarksViewModel by activityViewModels()

    companion object {
        val TITLE = "title_bookmark"
        val DESCRIPTION = "description_bookmark"
        val INDEX = "index"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditBookmarkBinding.inflate(inflater,container, false)
        return binding.root//view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            title = it.getString(TITLE).toString()
            description = it.getString(DESCRIPTION).toString()
            bookmarkSelectedIndex = it.getInt(INDEX)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textInputTitle = binding.tiItemTitle
        textInputTitle.setText(title)
        textInputDescription = binding.tiItemDescription
        textInputDescription.setText(description)


        fabCheckedEditBookmark = binding.fabCheckedEditBookmark
        fabCheckedEditBookmark.setOnClickListener{
            //val bundle = bundleOf(TITLE to textInputTitle, DESCRIPTION to textInputDescription)
            //Get values from textInputs
            val textTitle = textInputTitle.text.toString()
            val textDescription = textInputDescription.text.toString()
            //Update that bookmark through viewmodel
            sharedViewModel.updateBookmark(this.requireContext(), bookmarkSelectedIndex, textTitle, textDescription )
            
            findNavController().navigate(R.id.action_editBookmarkFragment_to_bookmarksListFragment)
            Toast.makeText(this.requireContext(), "Bookmark edited", Toast.LENGTH_SHORT).show()
        }   

    }


}