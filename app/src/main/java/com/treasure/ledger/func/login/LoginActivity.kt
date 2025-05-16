package com.treasure.ledger.func.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.treasure.ledger.BaseActivity
import com.treasure.ledger.databinding.ActivityLoginBinding
import com.treasure.ledger.func.MainActivity
import com.treasure.ledger.utils.afterTextChanged

class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        setupListeners()
        setupFormValidation()
    }

    private fun setupObservers() {
        viewModel.loginStatus.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let { result ->
                when (result) {
                    is LoginViewModel.LoginResult.Success -> {
                        // 登录成功，跳转到主界面
                        startActivity(Intent(this, MainActivity::class.java).apply {
                            putExtra("USERNAME", result.user.username)
                        })
                        finish()
                    }

                    is LoginViewModel.LoginResult.Error -> {
                        Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        viewModel.registerStatus.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let { result ->
                when (result) {
                    is LoginViewModel.RegisterResult.Success -> {
                        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show()
                        binding.usernameEditText.setText(result.user.username)
                        binding.passwordEditText.requestFocus()
                    }

                    is LoginViewModel.RegisterResult.Error -> {
                        Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun setupListeners() {
        binding.loginButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            viewModel.login(username, password)
        }

        binding.registerButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val email = binding.emailEditText.text.toString().takeIf { it.isNotBlank() }
            viewModel.register(username, password, email)
        }
    }

    private fun setupFormValidation() {
        binding.usernameEditText.afterTextChanged {
            validateForm()
        }

        binding.passwordEditText.afterTextChanged {
            validateForm()
        }

        binding.emailEditText.afterTextChanged {
            validateForm()
        }
    }

    private fun validateForm() {
        val usernameValid = binding.usernameEditText.text.toString().isNotBlank()
        val passwordValid = binding.passwordEditText.text.toString().isNotBlank()

        binding.loginButton.isEnabled = usernameValid && passwordValid
        binding.registerButton.isEnabled = usernameValid && passwordValid
    }
}