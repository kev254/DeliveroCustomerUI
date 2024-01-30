package com.delivero.customer

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.delivero.customer.auth.AuthActivity
import com.delivero.customer.databinding.ActivityMainBinding
import com.delivero.customer.helpers.Preference
import com.delivero.customer.interfaces.Collections
import com.delivero.customer.models.Token
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging

class MainActivity : AppCompatActivity(),LifecycleObserver {
    private lateinit var binding:ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val navHostFragment=supportFragmentManager.findFragmentById(R.id.homeContainerView) as NavHostFragment
        navController=navHostFragment.navController
        appBarConfiguration= AppBarConfiguration.Builder(navController.graph).build()
        setupActionBarWithNavController(navController,appBarConfiguration)
        setUpMessaging()
        setUpMenu()

    }

    private fun setUpMenu() {
        addMenuProvider(object :MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.default_menu,menu)
                val user= Preference.getUser(this@MainActivity) ?: return
                if (user.roles.contains("Driver")){
                    menu.findItem(R.id.addService).isVisible=false
                }
            }

            override fun onMenuItemSelected(item: MenuItem): Boolean {
                when(item.itemId){
                    R.id.addService->{
                        navController.navigate(R.id.riderDetailsFragment)
                        return true
                    }
                    R.id.logout->{
                        Firebase.auth.signOut()
                        startActivity(Intent(this@MainActivity,AuthActivity::class.java))
                        return true
                    }
                    R.id.bookings->{
                        navController.navigate(R.id.requestsFragment)
                        return true
                    }

                    R.id.profile->{
                        navController.navigate(R.id.profileFragment)
                        return true
                    }

                }
                return true
            }

        },this,Lifecycle.State.RESUMED)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)||super.onSupportNavigateUp()
    }

    private fun setUpMessaging() {
        Firebase.messaging.subscribeToTopic("Customer")
        Firebase.messaging.subscribeToTopic("Delivero")

        Firebase.messaging.token.addOnSuccessListener {
            val token= Token(Firebase.auth.currentUser!!.uid,it)
            Firebase.firestore.collection(Collections.TOKENS)
                .document(Firebase.auth.currentUser!!.uid)
                .set(token)
                .addOnSuccessListener {

                }
        }
    }




}