package com.example.f1app_v1.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.f1app_v1.core.BaseViewHolder
import com.example.f1app_v1.data.model.Driver
import com.example.f1app_v1.databinding.DriverItemBinding

class DriverAdapter(
    private val driverList: List<Driver>,
    private val itemClickListener: OnDriverClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnDriverClickListener {
        fun onDriverClick(driver: Driver)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            DriverItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = DriversViewHolder(itemBinding, parent.context)

        itemBinding.root.setOnClickListener {
            val position =
                holder.bindingAdapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                    ?: return@setOnClickListener
            itemClickListener.onDriverClick(driverList[position])
        }

        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is DriversViewHolder -> holder.bind(driverList[position])
        }

    }

    override fun getItemCount(): Int = driverList.size

    private inner class DriversViewHolder(val binding: DriverItemBinding, val context: Context) :
        BaseViewHolder<Driver>(binding.root) {
        override fun bind(item: Driver) {
            binding.txtName.text = item.competitor.name
            binding.txtNationality.text = item.competitor.nationality
        }
    }
}