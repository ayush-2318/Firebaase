package com.example.firebaase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebaase.databinding.ActivityMainBinding
import com.example.firebaase.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivitySignupBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        firebaseAuth= FirebaseAuth.getInstance()
        binding.tvalreadyregister.setOnClickListener{
            val intent=Intent(this,SigninActivity::class.java).also{
                startActivity(it)
            }

        }

        binding.btnsignup.setOnClickListener{
            val email=binding.etemail.text.toString()
            val pass=binding.etpassword.text.toString()
            val confpass=binding.etconiformpassword.text.toString()
            if(email.isNotEmpty()&&pass.isNotEmpty()&&confpass.isNotEmpty()){
                if(pass==confpass){
                    firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this, SigninActivity::class.java)
                            startActivity(intent)


                        } else {

                            Toast.makeText(
                                this@SignupActivity,
                                it.exception.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    }
                }
                else{
                    Toast.makeText(this@SignupActivity,"password is not matching",Toast.LENGTH_SHORT).show()
                }

            }
            else{
              Toast.makeText(this@SignupActivity,"empty field are not allowed",Toast.LENGTH_SHORT).show()
            }

        }
    }
}