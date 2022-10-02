package com.example.snailpasswordmanager.presentation.mainscreen

import android.content.Intent
import android.os.Bundle
import android.util.JsonReader
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.snailpasswordmanager.PasswordApp
import com.example.snailpasswordmanager.databinding.ActivityMainListBinding
import com.example.snailpasswordmanager.di.IdentityStore
import com.example.snailpasswordmanager.domain.model.ChatEntity
import com.example.snailpasswordmanager.presentation.AdduserChat.AddUserChatActivity
import com.google.gson.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.StringReader
import java.lang.reflect.Type
import javax.inject.Inject


class MainListActivity @Inject constructor(
) : AppCompatActivity() {

    lateinit var bindingClass : ActivityMainListBinding

    private var launcher: ActivityResultLauncher<Intent>? = null
    @Inject
    lateinit var vmFactory: MainListViewModelFactory


    private lateinit var viewModel : MainListViewModel
    private val adapter :PasswordListAdapter = PasswordListAdapter()

    override fun onResume() {
        super.onResume()
        adapter.setPasswords(viewModel.getChats())

        val url = "http://${IdentityStore.ip}/chats?login=${IdentityStore.login}"

        Log.d("test", "gggg")
        var t = Volley.newRequestQueue(this)
        val str = StringRequest(Request.Method.GET,
            url,
            { response ->
                Log.d("test","resp $response")
                val obj = JSONObject("$response")
                if(response.contains("\"status\": 404")){

                } else {
                    val jsonObj = JSONObject(response)
                    val s =  jsonObj.getJSONArray("chats")
                    for(a in 0..(s.length()-1)) {
                        //Log.d("test",s.get(a).toString())
                        adapter.addPassword(ChatEntity(s.get(a).toString()))
                    }

                    //var gson = Gson()
                    //val t = gson.fromJson(response, String::class.java)
                }
            /*else {
                    val obj = JSONObject(response)
                    val foodJson = obj.getJSONArray("chats")
                    Log.d("test",foodJson.toString())
                    //for(a in 0..foodJson.length()){
                    //    Log.d("test",foodJson.getString(a))
                    //}
                    //val data = obj.optJSONArray("chats")?.
                    //let { it } // returns an array of JSONObject
                         // transforms each JSONObject of the array into Foo

                }*/
            },
            {
                Log.d("test","er $it")
            }
        )
        t.add(str)

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingClass = ActivityMainListBinding.inflate(layoutInflater)

        (applicationContext as PasswordApp).appComponent.inject(this)

        setContentView(bindingClass.root)


        viewModel = ViewModelProvider(this,vmFactory)
            .get(MainListViewModel::class.java)

        init()

        adapter.setPasswords(viewModel.getChats())



        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result: ActivityResult ->

        }


        bindingClass.ButtonAdd.setOnClickListener {
            val intent = Intent(this, AddUserChatActivity::class.java).apply {
                //putExtra("EXTRA_MESSAGE", "")
            }
            startActivity(intent)
            //adapter.addPassword(ChatEntity
            //        (
            //    service = "",
            //    login = "",
            //    timestamp = 1,
            //    password = ""
            //))
        }
    }
    private fun init() {
        bindingClass.apply {
            rcView.layoutManager = LinearLayoutManager(this@MainListActivity)
            rcView.adapter = adapter

        }
    }


    /*fun passwordAddButton(view: View) {

        val intent = Intent(this, RegistrationActivity::class.java).apply {
            //putExtra("EXTRA_MESSAGE", "")
        }
        startActivity(intent)

    }*/
}
/*
class StringDeserializer : JsonDeserializer<String> {

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): String? {
        val jsonObj = json as JsonObject
        val jsonWeatherArray = jsonObj.getAsJsonArray()
        jsonWeatherArray.get()

        return jsonObj

    }
}*/
