package com.example.login

import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val olvidecontrasena = findViewById<TextView>(R.id.tvForgotPassword)
        olvidecontrasena.setOnClickListener{
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        val crear = findViewById<TextView>(R.id.tvSignUp)
        crear.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        //Inicializar Firebase Auth
        auth = FirebaseAuth.getInstance()

        val editTextEmail = findViewById<EditText>(R.id.etEmail)
        val editTextPassword = findViewById<EditText>(R.id.etPassword)
        val buttonLogin = findViewById<Button>(R.id.btnLogin)

        buttonLogin.setOnClickListener{
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            //Validar los campos de correo y contraseña
            if (email.isEmpty() || password.isEmpty() ){
                Toast.makeText(this,"Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // Iniciar sesion con correo y contraseña
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){ task ->
                if (task.isSuccessful){
                    // Inicio de sesion exitoso
                    val user = auth.currentUser
                    val intent = Intent(this, SuccessActivity::class.java)
                    startActivity(intent)
                    finish()
                    // aqui puedes redirigir al usuario a la siguiente actividad
                }else{
                    //error en el inicio de sesion
                    Toast.makeText(this,"Error en el inicio de sesion: " + "${task.exception?.message}",Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
}