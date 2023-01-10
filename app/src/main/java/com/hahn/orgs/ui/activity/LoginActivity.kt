package com.hahn.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.lifecycleScope
import com.hahn.orgs.database.AppDatabase
import com.hahn.orgs.database.preferences.dataStore
import com.hahn.orgs.database.preferences.userLoggedPreferences
import com.hahn.orgs.databinding.ActivityLoginBinding
import com.hahn.orgs.extensions.navigateTo
import com.hahn.orgs.extensions.toast
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private val userDao by lazy {
        AppDatabase.getInstance(this).userDao()
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        handleBtnSignIn()
        handleBtnSignUp()
    }
    
    private fun handleBtnSignIn() {
        binding.activityLoginBtnSignIn.setOnClickListener {
            val user = binding.activityLoginUser.text.toString()
            val passowrd = binding.activityLoginPassword.text.toString()
            verifyAuth(user, passowrd)
        }
    }
    
    private fun verifyAuth(user: String, passowrd: String) {
        lifecycleScope.launch {
            userDao.auth(user, passowrd)?.let { user ->
                dataStore.edit { preferences ->
                    preferences[userLoggedPreferences] = user.id
                }
                navigateTo(ListProductActivity::class.java)
                finish()
            } ?: toast("Usuário não encontrado")
        }
    }
    
    private fun handleBtnSignUp() {
        binding.activityLoginBtnSignup.setOnClickListener {
           navigateTo(FormUserRegisterActivity::class.java)
        }
    }
}