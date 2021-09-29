package com.daman.activitylifecycle

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity(),View.OnClickListener{
    override fun onClick(p0: View?){
    Toast.makeText(
    this@LoginActivity,
    "We clicked on this button to see the toast!",
    Toast.LENGTH_LONG).show()}



     lateinit var etMobileNumer  : EditText
    lateinit var etPassword : EditText
    lateinit var buttonPanelLogin : Button
    lateinit var textForgotPassword : TextView
    lateinit var textSignup : TextView
    val validMobileNumber ="0123456789"
    val validPassword = arrayOf("tony","thor","steve","thanos")

    lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        if(isLoggedIn){
            val intent = Intent(this@LoginActivity,MainActivity::class.java)
            startActivity(intent)
        }else{
            setContentView(R.layout.activity_new)
        }


        setContentView(R.layout.activity_new)
        title ="LOG IN"
        etMobileNumer = findViewById(R.id.etMobileNumber)
        etPassword = findViewById(R.id.etPassword)
        buttonPanelLogin = findViewById(R.id.buttonPanelLogin)
        textForgotPassword = findViewById(R.id.textForgotPassword)
        textSignup = findViewById(R.id.textSignup)

        buttonPanelLogin.setOnClickListener{
            val mobileNumber = etMobileNumer.text.toString()
            var password = etPassword.text.toString()
            var nameOfAvenger="Avenger"
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            if(mobileNumber== validMobileNumber){
                if (password==validPassword[0]){
                    savePreferences(nameOfAvenger)
                    nameOfAvenger= "Iron Man"
                    startActivity(intent)
                }else if(password== validPassword[1]){
                    savePreferences(nameOfAvenger)
                    nameOfAvenger="Thor"
                    startActivity(intent)

                }else if(password== validPassword[2]){
                    savePreferences(nameOfAvenger)
                    nameOfAvenger="Captain America"

                    startActivity(intent)

                }else if(password== validPassword[3]){
                    savePreferences(nameOfAvenger)
                    nameOfAvenger="The Avengers"
                    startActivity(intent)

                }
            }
            else{
                Toast.makeText(this@LoginActivity,"Incorrect Credentials", Toast.LENGTH_LONG).show()
            }
        }
    }
    override fun onPause(){
        super.onPause()
        finish()
    }
    fun savePreferences(title : String){
        sharedPreferences.edit().putBoolean("isLoggedIn", true).apply()
        sharedPreferences.edit().putString("Title", title).apply()
    }
}