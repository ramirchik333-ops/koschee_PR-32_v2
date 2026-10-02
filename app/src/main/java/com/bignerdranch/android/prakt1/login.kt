package com.example.worldcinema

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.prakt1.R
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val etLogin = findViewById<EditText>(R.id.etLogin)
        val etPass = findViewById<EditText>(R.id.etPass)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnReg = findViewById<Button>(R.id.btnReg)
        val prefs = getSharedPreferences("app", Context.MODE_PRIVATE)

        btnLogin.setOnClickListener {
            val login = etLogin.text.toString().trim()
            val pass = etPass.text.toString().trim()

            if (login.isEmpty() || pass.isEmpty()) {
                Snackbar.make(it, "Введите логин и пароль", Snackbar.LENGTH_LONG)
                    .setAction("OK"){}.show()
                return@setOnClickListener
            }

            val savedLogin = prefs.getString("login", "")
            val savedPass = prefs.getString("pass", "")

            if (savedLogin!!.isEmpty()) {
                prefs.edit().putString("login", login).putString("pass", pass).apply()
                startActivity(Intent(this, MainActivity::class.java))
            } else if (login == "ects" && pass == "ects2025") {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                Snackbar.make(it, "Неверный логин или пароль", Snackbar.LENGTH_LONG).show()
            }
        }

        btnReg.setOnClickListener {
            val login = etLogin.text.toString().trim()
            val pass = etPass.text.toString().trim()
            if (login.isNotEmpty() && pass.isNotEmpty()) {
                prefs.edit().putString("login", login).putString("pass", pass).apply()
                Snackbar.make(it, "Регистрация успешна", Snackbar.LENGTH_SHORT).show()
            } else {
                Snackbar.make(it, "Введите логин и пароль", Snackbar.LENGTH_LONG)
                    .setAction("OK"){}.show()
            }
        }
    }
}