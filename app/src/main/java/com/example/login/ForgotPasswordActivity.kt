package com.example.login

import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var etEmail: EditText
    private lateinit var btnResetPassword: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        etEmail = findViewById(R.id.etEmail)
        btnResetPassword = findViewById(R.id.btnResetPassword)

        btnResetPassword.setOnClickListener {
            val email = etEmail.text.toString().trim()
            if (email.isNotEmpty()){
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                this,
                                "Se ha enviado un correo electronico para restablecer la " + "contraseña",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                            finish()
                        }else{
                            Toast.makeText(this,"No se pudo enviar el correo electronico de " + "restablecimiento de contraseña",
                            Toast.LENGTH_SHORT)
                            .show()
                        }
                    }
            }else {
                Toast.makeText(
                    this, "por favor, ingrese su direccion de " + "correo electronico",
                    Toast.LENGTH_SHORT
                )
                    .show()

                }
            }
        }
    }
