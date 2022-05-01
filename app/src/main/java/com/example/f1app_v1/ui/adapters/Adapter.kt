package com.example.f1app_v1.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.f1app_v1.core.BaseViewHolder
import com.example.f1app_v1.data.model.Driver
import com.example.f1app_v1.data.model.DriverBaseInfo
import com.example.f1app_v1.databinding.DriverItemBinding

class Adapter<T : Any>(
    private val dataSet: List<T>,
    @LayoutRes val layoutID: Int,
    private val bindingInterface: RecyclerBindingInterface<T>,
    private val itemClickListener: OnClickListener<T>
) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    interface OnClickListener<T> {
        fun onClick(item: T)//DriverBaseInfo.Stage.Comp)
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun <T : Any> bind(item: T, bindingInterface: RecyclerBindingInterface<T>) =
            bindingInterface.bindData(item, view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = LayoutInflater.from(parent.context).inflate(layoutID, parent, false)
        val holder = ViewHolder(itemBinding)

        itemBinding.rootView.setOnClickListener {
            val position =
                holder.bindingAdapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                    ?: return@setOnClickListener
            itemClickListener.onClick(dataSet[position])
        }

        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position], bindingInterface)
    }

    override fun getItemCount(): Int = dataSet.size
}

interface RecyclerBindingInterface<T : Any> {
    fun bindData(item: T, view: View)
}