package com.example.notetakingapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notetakingapp.R
import com.example.notetakingapp.databinding.NoteItemBinding
import com.example.notetakingapp.fragments.HomeFragmentDirections
import com.example.notetakingapp.roomDatabase.Note
import java.util.Random

/**
 * Created by Aswin on 05-09-2024.
 */
class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(val itembinding :NoteItemBinding ): RecyclerView.ViewHolder(itembinding.root)

    private val differCallback = object :DiffUtil.ItemCallback<Note>(){
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id && oldItem.noteBody == newItem.noteBody && oldItem.noteTitle == newItem.noteTitle
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: NoteItemBinding = DataBindingUtil.inflate(layoutInflater,
            R.layout.note_item, parent, false)
        return NoteViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val curerntNote = differ.currentList[position]
        holder.itembinding.tvNoteTitle.text = curerntNote.noteTitle
        holder.itembinding.tvNoteBody.text = curerntNote.noteBody

        val random = Random()

        val color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))

        holder.itembinding.ibColor.setBackgroundColor(color)

        holder.itemView.setOnClickListener{
            val direction = HomeFragmentDirections.actionHomeFragmentToUpdateNoteFragment(curerntNote)

            it.findNavController().navigate(direction)
        }
    }
}