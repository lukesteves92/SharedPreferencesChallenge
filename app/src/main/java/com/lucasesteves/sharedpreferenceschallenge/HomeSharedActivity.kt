package com.lucasesteves.sharedpreferenceschallenge

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import com.lucasesteves.sharedpreferenceschallenge.databinding.ActivityHomeSharedBinding

class HomeSharedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeSharedBinding

    private val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences(KEY_SHARED_PREFERENCES, MODE_PRIVATE)
    }

    private val keepConnected: Boolean by lazy {
        sharedPreferences.getBoolean(KEY_SHARED_PREFERENCES_KEEP_CONNECTED, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeSharedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isLoggedIn()
    }

    private fun isLoggedIn() {
        val isLoggedIn = sharedPreferences.getBoolean(KEY_SHARED_PREFERENCES_IS_LOGGED_IN, false)
        if (!keepConnected && !isLoggedIn) {
            startActivity(Intent(this@HomeSharedActivity, LoginSharedActivity::class.java))
            finish()
        } else {
            binding.tvHomeSharedName.text = sharedPreferences.getString(KEY_SHARED_PREFERENCES_EMAIL, "")
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        if (!keepConnected) {
            sharedPreferences.edit {
                putBoolean(KEY_SHARED_PREFERENCES_IS_LOGGED_IN, false)
            }
        }
    }

    companion object {
        const val KEY_SHARED_PREFERENCES = "user"
        const val KEY_SHARED_PREFERENCES_KEEP_CONNECTED = "keepConnected"
        const val KEY_SHARED_PREFERENCES_EMAIL = "email"
        const val KEY_SHARED_PREFERENCES_IS_LOGGED_IN = "isLoggedIn"
    }
}