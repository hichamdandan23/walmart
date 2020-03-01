package com.example.walmart

import User
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.icu.text.UnicodeSetSpanner
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    var list = ArrayList<User>(listOf(
        User("Hicham","Dandan","hdandan@miu.edu","hicham123"),
        User("Yassin","Nouiri","ynouiri@miu.edu","yassin123"),
        User("Adam","Louly","alouly@miu.edu","adam123"),
        User("Mohamed","Elmaachi","melmaachi@miu.edu","mohamed123"),
        User("Hesham","Afifi","hafifi@miu.edu","hesham123")))

    //var sUsername = username.text.toString()
    //var sPassword = password.text.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        login.setOnClickListener { v ->
            var sUsername = username.text.toString()
            var sPassword = password.text.toString()
            if(isUsernameExist(sUsername) && isPasswordExist(sPassword)){
                val intent = Intent(this@MainActivity, ShoppingCategory::class.java)
                intent.putExtra("username", sUsername)
                var tst = Toast.makeText(this, "login successful !!", Toast.LENGTH_LONG)
                tst.setGravity(Gravity.CENTER_HORIZONTAL, Gravity.CENTER, Gravity.CENTER)
                tst.show()
                startActivity(intent)

            }else if(!isUsernameExist(sUsername))
            {
                Toast.makeText(this, "Your username is incorrect !", Toast.LENGTH_LONG).show()
            }else if(!isPasswordExist(sPassword))
            {
                Toast.makeText(this, "Your password is incorrect !", Toast.LENGTH_LONG).show()
            }


        }

        register.setOnClickListener { v ->
            val intent = Intent(this@MainActivity, RegisterActivity::class.java)
            //startActivity(intent)
            startActivityForResult(intent, 1)
        }

        forgetPass.setOnClickListener{ v ->
            val recipient = username.text.toString().trim()
            val subject = "Forget Password"
            var message : String = ""
            for(i in list)
            {
                if(i.username.equals(recipient))  message = i.password
            }



            //method call for email intent with these inputs as parameters
            sendEmail(recipient, subject, message)

        }




    }

    private fun sendEmail(recipient: String, subject: String, message: String) {
        /*ACTION_SEND action to launch an email client installed on your Android device.*/
        val mIntent = Intent(Intent.ACTION_SEND)
        /*To send an email you need to specify mailto: as URI using setData() method
        and data type will be to text/plain using setType() method*/
        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "text/plain"
        // put recipient email in intent
        /* recipient is put as array because you may wanna send email to multiple emails
           so enter comma(,) separated emails, it will be stored in array*/
        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
        //put the Subject in the intent
        mIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        //put the message in the intent
        mIntent.putExtra(Intent.EXTRA_TEXT, message)


        try {
            //start email intent
            startActivity(Intent.createChooser(mIntent, "Choose Email Client..."))
        }
        catch (e: Exception){
            //if any thing goes wrong for example no email client application or any exception
            //get and show exception message
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }

    }
     public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
               // val returnedResult : User = data?.getStringExtra("newUser") as User
                //list.add(returnedResult)
                //t?.setTextColor(Color.RED)
                val returnedResult = data!!.data!!.toString()
                var jsonObject = JSONObject(returnedResult)
                var newUser = User(jsonObject.getString("firstName"), jsonObject.getString("lastName"), jsonObject.getString("username"), jsonObject.getString("password"))
                list.add(newUser)
                //t?.text = returnedResult
                Toast.makeText(this, returnedResult, Toast.LENGTH_LONG).show()
            }
        }
    }

    fun isUsernameExist(susername : String): Boolean
    {
        for(user in list)
            if(user.username.equals(susername) ) return true
        return false
    }

    fun isPasswordExist(spassword : String): Boolean
    {
        for(user in list)
            if(user.password.equals(spassword)) return true
        return false
    }

}
