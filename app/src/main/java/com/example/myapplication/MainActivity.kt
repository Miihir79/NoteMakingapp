package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), inotesRVadapter {

    lateinit var viewModel: noteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        recycler.layoutManager=LinearLayoutManager(this)
        val adapter = NotesRVAdapter(this,this)
        recycler.adapter = adapter

        viewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(noteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer {list ->
            list?.let{
                adapter.updateList(it)
            }
        })

    }

    override fun onitemclicked(note: note) {
        alertDialouge(note)
    }
    fun submitData(view:View){
        val noteText = input.text.toString()
        if(noteText.isNotEmpty()){
            viewModel.addNote(note(noteText))
            Toast.makeText(this,"Added!",Toast.LENGTH_LONG).show()
            input.setText(null)
        }
    }
    private fun alertDialouge(note: note){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Are you sure?")
        builder.setMessage("Do you want to delete this message?")
        builder.setIcon(android.R.drawable.ic_menu_delete)

        builder.setPositiveButton("Yes"){dialogInterface,which->
            Toast.makeText(applicationContext,"Deleting",Toast.LENGTH_LONG).show()
            dialogInterface.dismiss()
            viewModel.deleteNode(note)
            Toast.makeText(this,"Deleted!",Toast.LENGTH_LONG).show()
        }

        builder.setNegativeButton("No"){dialogInterface,which->
            Toast.makeText(applicationContext,"Not deleting",Toast.LENGTH_LONG).show()
            dialogInterface.dismiss()
        }
        builder.setNeutralButton("Cancel"){dialogInterface,which->
            //Toast.makeText(applicationContext,"Deleting",Toast.LENGTH_LONG).show()
            dialogInterface.dismiss()
        }

        val alertDialog:AlertDialog = builder.create()
        alertDialog.setCancelable(true)
        alertDialog.show()
    }
}