package com.android.demo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.demo.R
import com.android.demo.databinding.TaskListItemBinding
import com.android.demo.model.TaskTableModel

class MyRecyclerViewAdapter(private val clickListener: (TaskTableModel) -> Unit) :
        RecyclerView.Adapter<MyViewHolder>() {
    private val subscribersList = ArrayList<TaskTableModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: TaskListItemBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.task_list_item, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return subscribersList.size
    }



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
     //   holder.bind(subscribersList[position], clickListener)

        if (subscribersList[position].selected){

            holder.binding.chSelect.isChecked = true
        }else{
            holder.binding.chSelect.isChecked = false

        }


        holder.binding.nameTextView.text = subscribersList[position].name
        holder.binding.emailTextView.text = subscribersList[position].email
        holder.binding.listItemLayout.setOnClickListener {
            if (subscribersList[position].selected){
                subscribersList[position].selected = false
                holder.binding.chSelect.isChecked = false

            }else{
                subscribersList[position].selected = true
                holder.binding.chSelect.isChecked = true


            }
            clickListener(subscribersList[position])



        }

    }

    fun setList(taskTableModels: List<TaskTableModel>) {
        subscribersList.clear()
        subscribersList.addAll(taskTableModels)

    }



    public fun selectedCount():Int{
        var intCount = 0
        for (i in 0 until subscribersList.size) {

            if (subscribersList[i].selected) {
                intCount++
            }
        }

    return intCount
    }
}

class MyViewHolder(val binding: TaskListItemBinding) : RecyclerView.ViewHolder(binding.root) {

//    fun bind(taskTableModel: TaskTableModel, clickListener: (TaskTableModel) -> Unit) {
//
//    }
}