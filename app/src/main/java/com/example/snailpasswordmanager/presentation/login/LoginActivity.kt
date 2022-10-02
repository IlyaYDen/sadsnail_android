package com.example.snailpasswordmanager.presentation.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.snailpasswordmanager.PasswordApp
import com.example.snailpasswordmanager.R
import com.example.snailpasswordmanager.di.IdentityStore
import com.example.snailpasswordmanager.domain.model.ChatEntity
import com.example.snailpasswordmanager.presentation.mainscreen.MainListActivity
import com.example.snailpasswordmanager.presentation.registration.RegistrationActivity
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject

class LoginActivity @Inject constructor(
) : AppCompatActivity(){

    lateinit var mainHandler: Handler



    private lateinit var vm: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        vm = ViewModelProvider(this).get(LoginViewModel::class.java)
        (applicationContext as PasswordApp).appComponent.inject(this)



        mainHandler = Handler(Looper.getMainLooper())
        //val scope = MainScope() // could also use an other scope such as viewModelScope if available


        //val a : EditText = findViewById(R.id.login_text)
        //a.setText("neutral face \uD83D\uDE11 ")

        val t = "[\n" +
                "    {\n" +
                "        \"sender_login\": \"Kirill Ponomarev\",\n" +
                "        \"receiver_login\": \"Ilya Kopchigashev\",\n" +
                "        \"content\": \"U+1F601\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"sender_login\": \"Kirill Ponomarev\",\n" +
                "        \"receiver_login\": \"Ilya Kopchigashev\",\n" +
                "        \"content\": \"U+1F60C\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"sender_login\": \"Ilya Kopchigashev\",\n" +
                "        \"receiver_login\": \"Kirill Ponomarev\",\n" +
                "        \"content\": \"U+1F620\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"sender_login\": \"Ilya Kopchigashev\",\n" +
                "        \"receiver_login\": \"Kirill Ponomarev\",\n" +
                "        \"content\": \"U+1F600\"\n" +
                "    }\n" +
                "]"
        /*
        val s =  JSONArray(t)

        for(a in 0..(s.length()-1)) {
            var jobj = s.getJSONObject(a)
            Log.d("test",jobj.getString("sender_login"))
            Log.d("test",jobj.getString("receiver_login"))
            Log.d("test",jobj.getString("content"))
        }*/


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


        val url = "http://${IdentityStore.ip}/users"
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
                        IdentityStore.login = a.text.toString()
                        Log.d("test", "OK")
                        startActivity(intent)
                        finish()
                    }
                }catch (e:Exception){
                    Log.d("test", "$e")
                }

            }, {
                // Error in request Kirill Ponomarev
                //a.setText("Exception: $it")
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