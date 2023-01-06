package com.hahn.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.hahn.orgs.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupBtnSignIn()
        setupBtnSignUp()
    }
    
    private fun setupBtnSignIn() {
        binding.activityLoginBtnSignIn.setOnClickListener {
            val username = binding.activityLoginUsername.text.toString()
            val passowrd = binding.activityLoginPassword.text.toString()
            Log.i("LoginActivity", "onCreate: $username - $passowrd")
            val intent = Intent(this, ListProductActivity::class.java)
            startActivity(intent)
        }
    }
    
    private fun setupBtnSignUp() {
        binding.activityLoginBtnSignup.setOnClickListener {
            val intent = Intent(this, FormUserRegisterActivity::class.java)
            startActivity(intent)
        }
    }
    
    
}