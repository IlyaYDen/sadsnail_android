package com.example.snailpasswordmanager.presentation.smilechat

import SmileChatAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.snailpasswordmanager.PasswordApp
import com.example.snailpasswordmanager.R
import com.example.snailpasswordmanager.databinding.ActivitySmileChatBinding
import com.example.snailpasswordmanager.databinding.SmileItemBinding
import com.example.snailpasswordmanager.domain.model.SmileMessageEntity
import javax.inject.Inject

class SmileChatActivity : AppCompatActivity() {



    private val adapter : SmileChatAdapter = SmileChatAdapter()


    @Inject
    lateinit var vmFactory: SmileChatViewModelFactory

    private lateinit var viewModel : SmileChatViewModel

    lateinit var bindingClass : ActivitySmileChatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingClass = ActivitySmileChatBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        (applicationContext as PasswordApp).appComponent.inject(this)


        viewModel = ViewModelProvider(this,vmFactory)
            .get(SmileChatViewModel::class.java)

        init()

        bindingClass.button2.setOnClickListener {

            Log.d("test","test")
            adapter.addSmileMessage(
                SmileMessageEntity(
                    emo = "test",
                    receiver_id = 1,
                    sender_id = 1,
                    time = 1,
                )
            )
        }

        bindingClass.button1.setOnClickListener {

            Log.d("test", "test")
            adapter.addSmileMessage(
                SmileMessageEntity(
                    emo = "test",
                    receiver_id = 1,
                    sender_id = 1,
                    time = 1,
                )
            )
        }
    }
    private fun init() {
        bindingClass.apply {
            recyclerVggiew6.layoutManager = LinearLayoutManager(this@SmileChatActivity)
            recyclerVggiew6.adapter = adapter

        }
    }

    fun B1(view: View) {
    }
}