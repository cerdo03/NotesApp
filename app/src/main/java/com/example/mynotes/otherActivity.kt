package com.example.mynotes

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.other_activity.*

class otherActivity : AppCompatActivity() {
    companion object{
        const val currentNote = "currentNote"
        const val code = "code"
    }
    lateinit var ggCode:String
    lateinit var Cnote:Note
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.other_activity)
        getSupportActionBar()?.setTitle("Notes");
        ggCode = intent.getStringExtra(code).toString()
        if(ggCode=="120121"){
            Cnote = intent.getSerializableExtra(currentNote) as  Note
            heading.setText(Cnote.heading)
            body.setText(Cnote.text)
        }
    }

    fun submitNote(view: View) {
        val intent =Intent()
        val head = heading.text.toString()
        val body = body.text.toString()
        intent.putExtra(MainActivity.bodyNote,body)
        intent.putExtra(MainActivity.headNote,head)
        setResult(Activity.RESULT_OK,intent)
        finish()


    }
}