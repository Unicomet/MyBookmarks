package com.memo.mybookmarks.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.memo.mybookmarks.BookmarksListFragmentDirections
import com.memo.mybookmarks.R
import com.memo.mybookmarks.model.Bookmark

class BookmarksItemsAdapter(private val context: Context, private val dataset: List<Bookmark>): RecyclerView.Adapter<BookmarksItemsAdapter.ViewHolder>() {
    companion object {
        val EXTRA_NAME = ""
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    // 1 - Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just an Affirmation object.
    class ViewHolder(val view: View):RecyclerView.ViewHolder(view){
        val title: TextView = view.findViewById<TextView>(R.id.tv_item_list_book_title)
        val description:TextView     = view.findViewById<TextView>(R.id.tv_item_list_description)
    }

    //2-Create new views (invoked by layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        //Create a new view, which defines the UI of the list item
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.bookmark_list_item, parent, false)

        return ViewHolder(adapterLayout)
    }

    //3 - Replace the contents of a view that was invoked by the layout manager
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataset[position]

        //Asigna datos a las vistas del item envuelto en el viewholder
        holder.title.text = context.resources.getString(item.titleId)
        holder.description.text = context.resources.getString(item.descriptionId)

        holder.view.setOnClickListener{

            Toast.makeText(holder.view.context,"Editing bookmark", Toast.LENGTH_SHORT).show()
            val action = BookmarksListFragmentDirections.actionBookmarksListFragmentToEditBookmarkFragment()
            holder.view.findNavController().navigate(action)
        }


    }

    override fun getItemCount(): Int = dataset.size

}