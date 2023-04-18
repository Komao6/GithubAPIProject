package com.example.april13project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editName = findViewById<EditText>(R.id.enter_name_et)
        val nextBt = findViewById<Button>(R.id.next_bt)
        val showName = findViewById<TextView>(R.id.show_name)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/users/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val githubApi = retrofit.create(githubAPI::class.java)
        nextBt.setOnClickListener{
            val getName = editName.text
            githubApi.getUser("${getName}").enqueue( object : Callback<UserModule>{
                override fun onResponse(call: Call<UserModule>, response: Response<UserModule>) {
                    val name = response.body()?.name
                    showName.text = name.toString()

                    println(response.body())
                }

                override fun onFailure(call: Call<UserModule>, t: Throwable) {

                }
            })
        }

    }
}