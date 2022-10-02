package com.example.snailpasswordmanager.presentation.smilechat

import SmileChatAdapter
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.snailpasswordmanager.PasswordApp
import com.example.snailpasswordmanager.R
import com.example.snailpasswordmanager.databinding.ActivitySmileChatBinding
import com.example.snailpasswordmanager.databinding.SmileItemBinding
import com.example.snailpasswordmanager.di.IdentityStore
import com.example.snailpasswordmanager.domain.model.ChatEntity
import com.example.snailpasswordmanager.domain.model.SmileMessageEntity
import com.google.gson.JsonObject
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject

class SmileChatActivity : AppCompatActivity() {



    private val adapter : SmileChatAdapter = SmileChatAdapter()


    @Inject
    lateinit var vmFactory: SmileChatViewModelFactory

    private lateinit var viewModel : SmileChatViewModel

    lateinit var bindingClass : ActivitySmileChatBinding

/*
    var job: Job? = null
    fun stopUpdates() {
        job?.cancel()
        job = null
    }

    override fun onResume() {
        super.onResume()
        stopUpdates()
        job = lifecycleScope.launch {
            while(true) {
                System.out.println("test")
                delay(1000)
            }
        }
    }*/

    var t = 0
    var old : Boolean = true
    /*
    fun startUpdates() {
        val lifecycle = this // in Activity
        //val a : EditText = bindingClass.

        lifecycle.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                // this block is automatically executed when moving into
                // the started state, and cancelled when stopping.
                while (true) {
                    Log.d("test", ""+t)
                    //a.setText("test  : " + t)
                    t++
                    delay(1000)
                }
            }
        }
    }*/

    var job: Job? = null

    @SuppressLint("RepeatOnLifecycleWrongUsage")
    fun startUpdates() {
        stopUpdates()



        Log.d("test","test")


        val url = "http://${IdentityStore.ip}/messages?current_user_login=${IdentityStore.login}&other_user_login=${IdentityStore.loginM}"

        Log.d("test",IdentityStore.loginM)
        Log.d("test",IdentityStore.login)

        var t = Volley.newRequestQueue(this)
        val str = StringRequest(Request.Method.GET,
            url,
            { response ->
                val s =  JSONArray(response)
                Log.d("test",s.toString())

                var lisst :MutableList<SmileMessageEntity> = ArrayList<SmileMessageEntity>()

                for(a in 0..(s.length()-1)) {
                    var jobj = s.getJSONObject(a)

                    Log.d("test",jobj.getString("content"))
                    Log.d("test",jobj.getString("receiver_login"))
                    Log.d("test",jobj.getString("sender_login"))
                    //Log.d("test",jobj.getString("sender_login"))
                    //Log.d("test",jobj.getString("receiver_login"))
                    //Log.d("test",jobj.getString("content"))
                    lisst.add(SmileMessageEntity(
                        emo = jobj.getString("content"),
                        receiver_id = jobj.getString("receiver_login"),
                        sender_id = jobj.getString("sender_login")
                    ))
                }
                Log.d("test",lisst.size.toString())
                adapter.setSmileMessage(lisst.toList())
                //Thread.sleep(100)
            },
            {
                Log.d("test","er $it")


            }
        )


        val url2 = "http://${IdentityStore.ip}/emotion?login=${IdentityStore.login}"

        var t2 = Volley.newRequestQueue(this)
        val str2 = StringRequest(Request.Method.GET,
            url,
            { response ->

                val jsonObj = JSONObject(response)
                Log.d("test",jsonObj.toString())
                val s =  jsonObj.getString("result_emoji")
                Log.d("test",s)
                bindingClass.textView4.setText(s)
                //Thread.sleep(100) s.toList()
            },
            {
                Log.d("test","er $it")
            }
        )





        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                // this block is automatically executed when moving into
                // the started state, and cancelled when stopping.
                while (true) {
                    t.add(str)
                    //t2.add(str2)
                    delay(5000)
                }
            }
        }
    }

    fun stopUpdates() {
        job?.cancel()
        job = null
    }

    override fun onResume() {
        super.onResume()
        if(old)
            startUpdates()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingClass = ActivitySmileChatBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        bindingClass.textView5.setText(IdentityStore.loginM)

        (applicationContext as PasswordApp).appComponent.inject(this)


        viewModel = ViewModelProvider(this,vmFactory)
            .get(SmileChatViewModel::class.java)

        startUpdates()
        old = (intent.getBooleanExtra("OLD",true))

        init()

        bindingClass.button0100.setOnClickListener {

            Log.d("test","test")
        }

        bindingClass.button00.setOnClickListener {
            sendEmo("\uD83D\uDE22")
        }
    }
    private fun init() {
        bindingClass.apply {
            recyclerVggiew6.layoutManager = LinearLayoutManager(this@SmileChatActivity)
            recyclerVggiew6.adapter = adapter

        }
    }

    fun sendEmo(a : String){

        val url = "http://${IdentityStore.ip}/messages"

        "EMOJI_WEIGHTS = [\n" +
                "                [\"\uD83D\uDE22\", 0, 0],\n" +
                "                [\"\uD83D\uDE11\", 50, 0], \n" +
                "                [\"\uD83D\uDE0C\", 100, 0], \n" +
                "                [\"\uD83D\uDE41\", 0, 50], \n" +
                "                [\"\uD83D\uDE10\", 50, 50],\n" +
                "                [\"\uD83D\uDE42\", 100, 50], \n" +
                "                [\"\uD83D\uDE20\", 0, 100], \n" +
                "                [\"\uD83D\uDE03\", 50, 100], \n" +
                "                [\"\uD83D\uDE01\", 100, 100] \n" +
                "                ]"

        val params = HashMap<String,String>()
        params["content"] = a
        params["sender_login"] = IdentityStore.login
        params["receiver_login"] = IdentityStore.loginM

        val jsonObject = (params as Map<*, *>?)?.let { JSONObject(it) }
        var t = Volley.newRequestQueue(this)

        val request = JsonObjectRequest(Request.Method.POST,url,jsonObject,
            { response ->
                // Process the json
                try {
                    Log.d("test", "$response")
                    if(
                        response.getInt("status").equals(200)){
                        Log.d("test", "OK")
                    }
                }catch (e:Exception){
                    Log.d("test", "$e")
                }

            }, {
                // Error in request
                Log.d("test", "Error $it")
            })



        t.add(request)
    }
    fun B1(view: View) {
    }
}