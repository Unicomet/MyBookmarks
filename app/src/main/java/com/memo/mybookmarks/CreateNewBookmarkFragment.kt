package com.memo.mybookmarks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.memo.mybookmarks.databinding.FragmentCreateNewBookmarkBinding

class CreateNewBookmarkFragment :Fragment(){

    private var _binding : FragmentCreateNewBookmarkBinding? = null
    private val binding get() = _binding!!
    private lateinit var fabCheckedCreateNewBookmark:FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateNewBookmarkBinding.inflate(inflater,container,false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fabCheckedCreateNewBookmark = binding.fabCheckedCreateBookmark

        fabCheckedCreateNewBookmark.setOnClickListener{
            Toast.makeText(this.requireContext(),"Bookmark created", Toast.LENGTH_SHORT).show()

            val title = binding.titleBookmark.toString()
            val description = binding.descriptionBookmark.toString()

            val action = CreateNewBookmarkFragmentDirections.actionCreateNewBookmarkFragmentToBookmarksListFragment( )
            view.findNavController().navigate(action)
        }


        //Cuando se presione regresar, borrar los datos introducidos


    }

}