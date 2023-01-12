package com.hahn.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.hahn.orgs.database.AppDatabase
import com.hahn.orgs.databinding.ActivityFormUserRegisterBinding
import com.hahn.orgs.extensions.toast
import com.hahn.orgs.model.User
import kotlinx.coroutines.launch

class FormRegisterUserActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityFormUserRegisterBinding.inflate(layoutInflater)
    }
    
    private val userDao by lazy {
        AppDatabase.getInstance(this).userDao()
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        handleButtonSignUp()
    }
    
    private fun handleButtonSignUp() {
        binding.activityFormBtnRegister.setOnClickListener {
            val newUser = createUser()
            handleSignUp(newUser)
        }
    }
    
    private fun handleSignUp(user: User) {
        lifecycleScope.launch {
            try {
                userDao.add(user)
                finish()
            }catch (e: Exception){
                Log.e("CadastroUsuario", "configuraBotaoCadastrar: ", e)
                toast("Falha ao cadastrar usuário")
            }
        }
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