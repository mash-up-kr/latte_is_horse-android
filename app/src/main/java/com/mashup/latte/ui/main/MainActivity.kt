package com.mashup.latte.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.mashup.latte.R
import com.mashup.latte.ui.add.AddActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener {
            val nextIntent = Intent(this, AddActivity::class.java)
            startActivity(nextIntent)
        }
    }


    private fun init(){

    }

    private fun initView(){
        bottomAppbar.setOnMenuItemClickListener {
            when(it.itemId){

            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater.inflate(R.menu.menu_bottom_app_bar,menu)
        return true
    }
}
