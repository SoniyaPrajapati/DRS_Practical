package com.android.demo.ui.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.demo.R
import com.android.demo.adapter.MyRecyclerViewAdapter
import com.android.demo.databinding.ActivityHomeBinding
import com.android.demo.model.TaskTableModel
import com.android.demo.repository.TaskRepository
import com.android.demo.room.TaskDatabase
import com.android.demo.viewmodel.TaskViewModel
import com.anushka.roommvvmcrudapp.TaskViewModelFactory

class HomeActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var taskViewModel: TaskViewModel
    private lateinit var adapter: MyRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        val name: String =  Build.MODEL
        binding.tvProfileName.text = name


        val dao = TaskDatabase.getInstance(application).taskDAO
        val repository = TaskRepository(dao)
        val factory = TaskViewModelFactory(repository)

        taskViewModel = ViewModelProvider(this, factory).get(TaskViewModel::class.java)
        binding.myViewModel = taskViewModel
        binding.lifecycleOwner = this

        taskViewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })

        binding.ivAdd.setOnClickListener(this)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.subscriberRecyclerView.layoutManager = LinearLayoutManager(this)
        val layoutManager = GridLayoutManager(this, 2)

        adapter = MyRecyclerViewAdapter({ selectedItem: TaskTableModel -> listItemClicked(selectedItem) })
        binding.subscriberRecyclerView.layoutManager = layoutManager
        binding.subscriberRecyclerView.adapter = adapter

        displaySubscribersList()



    }

        public fun selectedCount(selectedCount: Int) {


    }

    private fun displaySubscribersList() {
        taskViewModel.getSavedSubscribers().observe(this, Observer {
            adapter.setList(it)
            adapter.notifyDataSetChanged()


        })


    }

    private fun listItemClicked(taskTableModel: TaskTableModel) {
        taskViewModel.initUpdateAndDelete(taskTableModel)

        var percentage = (adapter.selectedCount()*100)/adapter.itemCount.toDouble()

        binding.progressTotal.text = percentage.toString()
        binding.progressBar1.setProgress(percentage.toInt());



    }

    override fun onClick(p0: View?) {
        when (p0) {

            binding.ivAdd -> {


            }
        }

    }
}