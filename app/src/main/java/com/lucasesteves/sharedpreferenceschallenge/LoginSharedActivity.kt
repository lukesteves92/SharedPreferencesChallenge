package com.lucasesteves.sharedpreferenceschallenge

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import com.lucasesteves.sharedpreferenceschallenge.databinding.ActivityLoginSharedBinding
import com.lucasesteves.sharedpreferenceschallenge.HomeSharedActivity.Companion.KEY_SHARED_PREFERENCES
import com.lucasesteves.sharedpreferenceschallenge.HomeSharedActivity.Companion.KEY_SHARED_PREFERENCES_EMAIL
import com.lucasesteves.sharedpreferenceschallenge.HomeSharedActivity.Companion.KEY_SHARED_PREFERENCES_IS_LOGGED_IN
import com.lucasesteves.sharedpreferenceschallenge.HomeSharedActivity.Companion.KEY_SHARED_PREFERENCES_KEEP_CONNECTED
import com.github.cesar1287.turma1dh.utils.getText

class LoginSharedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginSharedBinding

    private val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences(KEY_SHARED_PREFERENCES, MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginSharedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btLogin.setOnClickListener {
                val email = etEmail.getText()
                val keepConnected = cbKeepConnected.isChecked

                sharedPreferences.edit {
                    putString(KEY_SHARED_PREFERENCES_EMAIL, email)
                    putBoolean(KEY_SHARED_PREFERENCES_KEEP_CONNECTED, keepConnected)
                    putBoolean(KEY_SHARED_PREFERENCES_IS_LOGGED_IN, true)
                }

                startActivity(Intent(this@LoginSharedActivity, HomeSharedActivity::class.java))
                finish()
            }
        }
    }
}