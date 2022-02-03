package com.example.mynotes

import android.content.Context
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter

class NotesRVAdapter(private val context: Context, private val listener: INotesRVAdapter): Adapter<NotesRVAdapter.NoteViewHolder>() {
    val allNote=ArrayList<Note>()
    inner class NoteViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val textView=itemView.findViewById<TextView>(R.id.text)
        val deleteButton=itemView.findViewById<ImageView>(R.id.delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder= NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item,parent,false))
        viewHolder.deleteButton.setOnClickListener{
            listener.onItemClicked(allNote[viewHolder.adapterPosition])
        }
        viewHolder.textView.setOnClickListener{
            listener.onNoteClicked(allNote[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote=allNote[position]
        holder.textView.text=currentNote.heading
    }

    override fun getItemCount(): Int {
        return allNote.size
    }
    fun updateList(newList: List<Note>){
        allNote.clear()
        allNote.addAll(newList)
        notifyDataSetChanged()
    }
}
interface INotesRVAdapter{
    fun onItemClicked(note:Note)
    fun onNoteClicked(note:Note)
}