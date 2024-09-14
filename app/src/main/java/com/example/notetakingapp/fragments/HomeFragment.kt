package com.example.notetakingapp.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notetakingapp.MainActivity
import com.example.notetakingapp.R
import com.example.notetakingapp.adapter.NoteAdapter
import com.example.notetakingapp.databinding.FragmentHomeBinding
import com.example.notetakingapp.roomDatabase.Note
import com.example.notetakingapp.viewmodel.NoteViewModel

class HomeFragment : Fragment(R.layout.fragment_home), SearchView.OnQueryTextListener {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var notesViewModel : NoteViewModel
    private lateinit var noteAdapter : NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        _binding = FragmentHomeBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Set the ActionBar title to an empty string to remove the app name
        (activity as AppCompatActivity).supportActionBar?.title = ""
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        notesViewModel = (activity as MainActivity).noteViewModel

        setUpRecyclerView()

        binding.fabAddNote.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_newNoteFragment)
        }
    }

    private fun setUpRecyclerView() {
        noteAdapter = NoteAdapter()

        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter = noteAdapter
        }

        activity?.let {
            notesViewModel.getAllNote().observe(viewLifecycleOwner,{
                note -> noteAdapter.differ.submitList(note)
                updateUI(note)
            })
        }
    }

    private fun updateUI(note: List<Note>?) {
        if (note != null) {
            if (note.isNotEmpty()){
                binding.cardView.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
            } else {
                binding.cardView.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        menu.clear()
        inflater.inflate(R.menu.menu_home, menu)

        val mMenuSearch = menu.findItem(R.id.menu_search).actionView as SearchView
        mMenuSearch.isSubmitButtonEnabled = false
        mMenuSearch.setOnQueryTextListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_deleteall -> {
                deleteNote()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteNote(){
        AlertDialog.Builder(activity).apply {
            setTitle("Delete All")
            setMessage("You want to delete all Note?")
            setPositiveButton("Delete All"){_,_ ->
                notesViewModel.deleteAll()
//                view?.findNavController()?.navigate(R.id.action_updateNoteFragment_to_homeFragment)
            }
            setNegativeButton("Cancel", null)
        }.create().show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
//        if (query != null) {
//            searchNote(query)
//        }
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if(newText != null){
            searchNote(newText)
        }
        return true
    }

    private fun searchNote(query: String) {
        val searchQuery = "%$query"

        notesViewModel.searchNote(query).observe(this, { list -> noteAdapter.differ.submitList(list)})
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }

}