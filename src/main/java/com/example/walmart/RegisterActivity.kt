package com.example.walmart

import User
import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_register.*
import java.io.Serializable

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        register.setOnClickListener { v ->
            val data = Intent()

            //val intent = Intent(this@RegisterActivity, MainActivity::class.java)

            val firstName = firstName?.text.toString()
            val lastName = lastName?.text.toString()
            val username = username?.text.toString()
            val password = password?.text.toString()

            //val usr = User(firstName, lastName, username, password)

            //val text = input?.text.toString()
            //---set the data to pass back
            data.data = Uri.parse(firstName)
            data.data = Uri.parse("{\"firstName\":\"${firstName}\",\"lastName\":\"$lastName\",\"username\":\"$username\",\"password\":\"$password\"}")



            /*intent.putExtra("first", firstName)
            intent.putExtra("last", lastName)
            intent.putExtra("usr", username)
            intent.putExtra("pswd", password)*/
            //startActivity(intent)
            setResult(Activity.RESULT_OK, data)
            //---close the activity---
            finish()
        }
    }
}
