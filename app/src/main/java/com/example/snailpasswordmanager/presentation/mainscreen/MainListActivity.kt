package com.example.snailpasswordmanager.presentation.mainscreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.snailpasswordmanager.PasswordApp
import com.example.snailpasswordmanager.R
import com.example.snailpasswordmanager.databinding.ActivityMainListBinding
import com.example.snailpasswordmanager.di.IdentityStory
import com.example.snailpasswordmanager.domain.model.ChatEntity
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject


class MainListActivity @Inject constructor(
    val identityStory: IdentityStory
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


        val url = "http://192.168.43.85:5000/users"
        var login = identityStory.login
        val params = HashMap<String,String>()
        params["login"] = login
        val jsonObject = (params as Map<*, *>?)?.let { JSONObject(it) }
        var t = Volley.newRequestQueue(this)

        val request = JsonObjectRequest(
            Request.Method.GET,url,jsonObject,
            { response ->
                // Process the json
                try {
                    Log.d("test", "$response")
                    
                }catch (e:Exception){
                    Log.d("test", "$e")
                }

            }, {
                // Error in request
                //a.setText("Exception: $it")
                Log.d("test", "$it")
            })



        t.add(request)


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

            adapter.addPassword(ChatEntity
                    (
                service = "",
                login = "",
                timestamp = 1,
                password = ""
            ))
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
