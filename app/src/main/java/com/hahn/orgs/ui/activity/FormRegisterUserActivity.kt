package com.hahn.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hahn.orgs.databinding.ActivityFormUserRegisterBinding
import com.hahn.orgs.model.User

class FormRegisterUserActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityFormUserRegisterBinding.inflate(layoutInflater)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
    
    private fun createUser(): User {
        val user = binding.activityFormRegisterUser.text.toString()
        val name = binding.activityFormRegisterName.text.toString()
        val password = binding.activityFormRegisterPassword.text.toString()
        return User(
            user,
            name,
            password
        )
    }
}