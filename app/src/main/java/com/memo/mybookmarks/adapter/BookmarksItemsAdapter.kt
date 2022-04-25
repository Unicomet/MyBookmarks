package com.memo.mybookmarks.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.memo.mybookmarks.databinding.BookmarkListItemBinding
import com.memo.mybookmarks.data.Bookmark

class BookmarksItemsAdapter(private val dataset: MutableList<Bookmark>,
                            private val clickListener: (Int) -> Unit): RecyclerView.Adapter<BookmarksItemsAdapter.ViewHolder>() {


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    // 1 - Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just an Affirmation object.

    //clickPosition type is lambda syntax, that means that clickAtPosition will execute a function and return a Int.
    class ViewHolder(var binding: BookmarkListItemBinding,clickAtPosition: (Int) -> Unit ):RecyclerView.ViewHolder(binding.root) {

        init{
            binding.root.setOnClickListener{
                //We only pass parameters to the function
                clickAtPosition(adapterPosition)
            }
        }

        fun bind(bookmark: Bookmark) {
            binding.tvItemListBookTitle.text = bookmark.title
            binding.tvItemListDescription.text = bookmark.description
        }
    }

    //2-Create new views (invoked by layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        //Create a new view, which defines the UI of the list item
        val layoutInflater = LayoutInflater.from(parent.context)

        val viewHolder = ViewHolder(
            BookmarkListItemBinding.inflate(layoutInflater,parent, false)
        ){  //Here in this block we define the function clickAtPosition()
            clickListener(it)
        }

        return viewHolder
    }

    //3 - Replace the contents of a view that was invoked by the layout manager
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bookmark = dataset[position]
        //Assign data to the BookmarkListItem view involved in the viewholder.
        holder.bind(bookmark)
    }

    fun updateList(){

    }

        override fun getItemCount(): Int = dataset.size

}