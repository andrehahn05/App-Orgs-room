package com.hahn.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hahn.orgs.databinding.ActivityFormUserRegisterBinding

class FormUserRegisterActivity: AppCompatActivity() {
    private  val binding by lazy {
        ActivityFormUserRegisterBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}