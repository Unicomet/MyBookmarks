package com.memo.mybookmarks


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.memo.mybookmarks.model.Bookmark


class MainActivity : AppCompatActivity() {
    private lateinit var navController:NavController
    private val sharedViewModel: BookmarksViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId) {
        R.id.action_delete -> {
            sharedViewModel.clearBookmarks(this)
            //Restart activity to show the changes
            /*val mIntent = intent
            finish()
            startActivity(mIntent)*/
            //Get an instance here of a fragment is bad?
            val bookmarksList = listOf<Bookmark>().toMutableList()

            sharedViewModel.recyclerViewAdapter.updateList(bookmarksList)
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }


    //This enables "up" button to go to previous fragment
   override fun onSupportNavigateUp(): Boolean {
       return navController.navigateUp() || super.onSupportNavigateUp()

   }





}
