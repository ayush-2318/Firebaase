package com.example.firebaase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebaase.databinding.ActivitySigninBinding
import com.example.firebaase.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class SigninActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySigninBinding
    private lateinit var firebaseauth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivitySigninBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        firebaseauth = FirebaseAuth.getInstance()
        binding.tvalreadyregister.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java).also{
                startActivity(it)
            }


        }
        binding.btnsignup.setOnClickListener {
            val email = binding.etemail.text.toString()
            val pass = binding.etpassword.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {

                firebaseauth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {

                    if(it.isSuccessful){
                        val intent=Intent(this,MainActivity::class.java)
                        startActivity(intent)


                    }
                    else{

                        Toast.makeText(this@SigninActivity,it.exception.toString(),Toast.LENGTH_SHORT).show()
                    }

                }
            } else {
                Toast.makeText(
                    this@SigninActivity,
                    "empty field are not allowed",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }
    }
