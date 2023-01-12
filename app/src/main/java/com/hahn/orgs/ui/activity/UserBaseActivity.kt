package com.hahn.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.lifecycleScope
import com.hahn.orgs.database.AppDatabase
import com.hahn.orgs.database.preferences.dataStore
import com.hahn.orgs.database.preferences.userLoggedPreferences
import com.hahn.orgs.extensions.navigateTo
import com.hahn.orgs.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch


abstract class UserBaseActivity: AppCompatActivity() {
    
    private val userDao by lazy {
        AppDatabase.getInstance(this).userDao()
    }
    private val _user: MutableStateFlow<User?> = MutableStateFlow(null)
    protected val user: StateFlow<User?> = _user
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState )
        lifecycleScope.launch {
            verifyUserLogin()
        }
    }
    
    private suspend fun verifyUserLogin(){
        dataStore.data.collect { preferences ->
            preferences[userLoggedPreferences]?.let { userId ->
                getUser(userId)
            } ?: navigateToLogin()
        }
    }
    
    private suspend fun getUser(userId: String) {
        _user.value = userDao.findById(userId).firstOrNull()
    }
    
    protected suspend fun logoutUser() {
        dataStore.edit { preferences ->
            preferences.remove(userLoggedPreferences)
        }
    }
    
    private fun navigateToLogin() {
        navigateTo(LoginActivity::class.java) {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }
        finish()
    }
}
