package com.example.snailpasswordmanager.presentation.AdduserChat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.snailpasswordmanager.PasswordApp
import com.example.snailpasswordmanager.R
import com.example.snailpasswordmanager.databinding.ActivityAddUserChatBinding
import com.example.snailpasswordmanager.databinding.ActivityMainListBinding
import com.example.snailpasswordmanager.di.IdentityStore
import com.example.snailpasswordmanager.presentation.mainscreen.MainListViewModel
import com.example.snailpasswordmanager.presentation.smilechat.SmileChatActivity

class AddUserChatActivity : AppCompatActivity() {

    lateinit var bindingClass : ActivityAddUserChatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user_chat)


        bindingClass = ActivityAddUserChatBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)


        val intent = Intent(this, SmileChatActivity::class.java).apply {
            putExtra("OLD", false)
        }

        bindingClass.AddUserButton.setOnClickListener() {
            //IdentityStore.userJSON
            //404 - error
            //200 - ok

            val url = "http://${IdentityStore.ip}/users"

            var t = Volley.newRequestQueue(this)
            val str = JsonObjectRequest(
                Request.Method.GET,
                url,null,
                { response ->
                    if(response.getInt("status") == 200) {
                        startActivity(intent)
                        finish()
                    }
                    else {
                        bindingClass.textView3.visibility = View.VISIBLE
                    }
                    //Thread.sleep(100)
                },
                {
                    Log.d("test","er $it")
                }
            )
            t.add(str)
        }
    }
}