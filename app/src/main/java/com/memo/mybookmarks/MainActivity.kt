package com.memo.mybookmarks


import android.app.KeyguardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
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
        setupAuth()
        authenticate({
        })

    }

    private var canAuthenticate = false
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    private fun setupAuth(){

        val biometricManager = BiometricManager.from(this)
        val keyGuardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        if(keyGuardManager.isDeviceSecure)
            canAuthenticate = true

            promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle("Autenticación")
                .setSubtitle("Ingresa tu huella para acceder")
                .setNegativeButtonText("Cancel")
               .build()
        }

    private fun authenticate(auth: (auth:Boolean) -> Unit) {
        if(canAuthenticate) {

            BiometricPrompt(this,ContextCompat.getMainExecutor(this),
                object: BiometricPrompt.AuthenticationCallback() {

                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                        super.onAuthenticationSucceeded(result)
                        auth(true)
                    }
                }).authenticate(promptInfo)
        } else {
            auth(true)
        }
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

