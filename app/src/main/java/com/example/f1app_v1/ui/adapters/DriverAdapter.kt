package com.example.f1app_v1.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.f1app_v1.core.BaseViewHolder
import com.example.f1app_v1.data.model.Driver
import com.example.f1app_v1.data.model.DriverBaseInfo
import com.example.f1app_v1.databinding.DriverItemBinding

class DriverAdapter(
    private val driverList: DriverBaseInfo,//List<Driver>,
    private val itemClickListener: OnDriverClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnDriverClickListener {
        fun onDriverClick(driver: DriverBaseInfo.Stage.Comp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            DriverItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = DriversViewHolder(itemBinding, parent.context)

        itemBinding.root.setOnClickListener {
            val position =
                holder.bindingAdapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                    ?: return@setOnClickListener
            itemClickListener.onDriverClick(driverList.stage.competitors[position])
        }

        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is DriversViewHolder -> holder.bind(driverList.stage.competitors[position])
        }

    }

    override fun getItemCount(): Int = driverList.stage.competitors.size

    private inner class DriversViewHolder(val binding: DriverItemBinding, val context: Context) :
        BaseViewHolder<DriverBaseInfo.Stage.Comp>(binding.root) {
        override fun bind(item: DriverBaseInfo.Stage.Comp) {
            binding.txtName.text = item.name
            binding.txtNationality.text = item.nationality
        }
    }
}