package com.example.mynotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.os.IResultReceiver
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynotes.R
import com.example.mynotes.INotesRVAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), INotesRVAdapter {
    lateinit var viewModel: ViewModel
    lateinit var curNote:Note
    companion object{
        const val headNote = "headNote"
        const val bodyNote = "bodyNote"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getSupportActionBar()?.setTitle("Notes")


        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NotesRVAdapter(this, this)
        recyclerView.adapter=adapter

        viewModel = ViewModelProvider(this,
        ViewModelProvider.AndroidViewModelFactory.getInstance(application))[ViewModel::class.java]

        viewModel.allNotes.observe(this, Observer {list->
            list?.let{
                adapter.updateList(it)
            }

        })



    }

    override fun onItemClicked(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this,"Note Deleted",Toast.LENGTH_SHORT).show()
    }

    override fun onNoteClicked(note: Note) {
        val intent  =Intent(this,otherActivity::class.java)
        curNote = note
        intent.putExtra(otherActivity.currentNote,note)
        intent.putExtra(otherActivity.code,"120121")
        startActivityForResult(intent,1)

    }

    fun submitData(view: View) {

        val intent = Intent(this, otherActivity::class.java)
        startActivityForResult(intent,0)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==0 && data !=null){
            if (resultCode == RESULT_OK) {
                // Get the result from intent
                val head = data.getStringExtra(headNote)
                val body = data.getStringExtra(bodyNote)
                Log.d("gg","Text Added $head")
                // set the result to the text view
                if(body !=null && body.isNotEmpty()){
                    viewModel.insertNote(Note(body.toString(),head.toString()))
                    Toast.makeText(this,"Note Added",Toast.LENGTH_SHORT).show()

                }
            }
        }
        if(requestCode==1 && data !=null){
            if (resultCode == RESULT_OK) {
                // Get the result from intent
                val head = data.getStringExtra(headNote)
                val body = data.getStringExtra(bodyNote)
                Log.d("gg","Text Added $head")
                // set the result to the text view
                curNote.text = body.toString()
                curNote.heading = head.toString()


                    viewModel.updateNote(curNote)
                    Toast.makeText(this,"Note Changed",Toast.LENGTH_SHORT).show()


            }
        }
    }
}