package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesRVAdapter(private val context: Context,private val listner :inotesRVadapter):RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder> (){

    val allNotes = ArrayList<note>()
    inner class NoteViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val textView = itemView.findViewById<TextView>(R.id.text)

        val deleteButton = itemView.findViewById<ImageView>(R.id.deletebutton)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
       val viewHolder= NoteViewHolder( LayoutInflater.from(context).inflate(R.layout.item_note,parent,false))
        viewHolder.deleteButton.setOnClickListener{
            listner.onitemclicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentnote = allNotes[position]
        holder.textView.text=currentnote.text

    }

    override fun getItemCount(): Int {
        return allNotes.size
    }
    fun updateList(newList:List<note>){
        allNotes.clear()
        allNotes.addAll(newList)

        notifyDataSetChanged()
    }
}
interface inotesRVadapter{
    fun onitemclicked(note:note){

    }
}