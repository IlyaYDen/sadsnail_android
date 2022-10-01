package com.example.snailpasswordmanager.presentation.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.snailpasswordmanager.PasswordApp
import com.example.snailpasswordmanager.R
import com.example.snailpasswordmanager.databinding.ActivityMainListBinding
import com.example.snailpasswordmanager.di.IdentityStory
import com.example.snailpasswordmanager.presentation.mainscreen.MainListActivity
import com.example.snailpasswordmanager.presentation.registration.RegistrationActivity
import org.json.JSONObject
import javax.inject.Inject
import kotlin.math.log

class LoginActivity @Inject constructor(
    var identityStory: IdentityStory
) : AppCompatActivity(){


    private lateinit var vm: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        vm = ViewModelProvider(this).get(LoginViewModel::class.java)
        (applicationContext as PasswordApp).appComponent.inject(this)

    }

    fun registrationButton(view: View) {

        val intent = Intent(this, RegistrationActivity::class.java).apply {
            //putExtra("EXTRA_MESSAGE", "")
        }
        startActivity(intent)

    }

    fun loginButton(view: View) {
        val intent = Intent(this, MainListActivity::class.java).apply {
            putExtra("EXTRA_MESSAGE", "")
        }


        val url = "http://192.168.43.85:5000/users"
        val a : EditText = findViewById(R.id.login_text)
        val b : EditText = findViewById(R.id.password_text)

        val params = HashMap<String,String>()
        params["login"] = a.text.toString()
        params["password"] = b.text.toString()
        val jsonObject = (params as Map<*, *>?)?.let { JSONObject(it) }
        var t = Volley.newRequestQueue(this)

        val request = JsonObjectRequest(Request.Method.POST,url,jsonObject,
            { response ->
                // Process the json
                try {
                    Log.d("test", "$response")
                    if(response.getInt("status").equals(201) ||
                        response.getInt("status").equals(200)){
                        identityStory.login = a.text.toString()
                        Log.d("test", "OK")
                        startActivity(intent)
                        finish()
                    }
                }catch (e:Exception){
                    Log.d("test", "$e")
                }

            }, {
                // Error in request
                a.setText("Exception: $it")
                Log.d("test", "$it")
            })



        t.add(request)




        //startActivity(intent)
        //finish()
    }



}
        /*

        val url = "https://postman-echo.com/post"
        val a : EditText = findViewById(R.id.login_text)

        val params = HashMap<String,String>()
        params["foo1"] = "bar1"
        params["foo2"] = "bar2"
        val jsonObject = (params as Map<*, *>?)?.let { JSONObject(it) }

        val j = JSONObject("{\"test\": 1 }")

        var t = Volley.newRequestQueue(this)
        val request = JsonObjectRequest(Request.Method.POST,url,j,
            { response ->
                // Process the json
                try {
                    a.setText("Exception: $response")
                }catch (e:Exception){
                }

            }, {
                // Error in request
                a.setText("Exception: $it")
            })

        t.add(request)











        val url = "https://jsonplaceholder.typicode.com/posts"
        var t = Volley.newRequestQueue(this)
        val str = StringRequest(
            Request.Method.POST,
            url,
            { response ->
                Log.d("mylog","resp $response")
            },
            {
                Log.d("mylog","er $it")
            }
        )
        t.add(str)

        val intent = Intent(this, MainListActivity::class.java).apply {
            putExtra("EXTRA_MESSAGE", "")
        }
        startActivity(intent)
        finish()

    }
}*/