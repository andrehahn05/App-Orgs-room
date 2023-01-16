package com.hahn.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.hahn.orgs.database.AppDatabase
import com.hahn.orgs.databinding.ActivityFormUserRegisterBinding
import com.hahn.orgs.extensions.toast
import com.hahn.orgs.model.User
import kotlinx.coroutines.launch

class FormUserRegisterActivity: AppCompatActivity() {
    private  val binding by lazy {
        ActivityFormUserRegisterBinding.inflate(layoutInflater)
    }
    private val dao by lazy {
        AppDatabase.getInstance(this).userDao()
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        handleBtnSinup()
    }
    
    private fun handleBtnSinup() {
        binding.activityFormBtnRegister.setOnClickListener {
            val newUser = createUser()
            lifecycleScope.launch {
                try {
                    dao.add(newUser)
                    finish()
                } catch (e: Exception){
                    toast("Falha ao cadastrar usuário")
                }
            }
        }
    }
    
    private fun createUser(): User {
        val user = binding.activityFormRegisterUser.text.toString()
        val name = binding.activityFormRegisterName.text.toString()
        val password = binding.activityFormRegisterPassword.text.toString()
        return User (user, name, password)
    }
}