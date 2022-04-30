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

class Adapter<T: Any>(
    private val List:T,//List<Driver>,
    @LayoutRes val layoutID: Int,
    private val bindingInterface: BindingInterface<T>,
    private val itemClickListener: OnDriverClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnDriverClickListener {
        fun onDriverClick(driver: DriverBaseInfo.Stage.Comp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = LayoutInflater.from(parent.context).inflate(layoutID,parent,false)
            //DriverItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = GenericViewHolder(itemBinding.rootView, parent.context)

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

    interface BindingInterface<T> {
        fun bindData(item: T,view:View)
    }

    private inner class GenericViewHolder(val binding:View, val context: Context) : BaseViewHolder<T>(binding) {


        /*override fun bind(item: DriverBaseInfo.Stage.Comp) {
            binding.txtName.text = item.name.let {
                val splitted = it.split(",")
                "${splitted[1]} ${splitted[0]} "
            }
            binding.txtNationality.text = item.nationality
            binding.txtPosition.text =
                "${item.result.position} - ${item.result.points} ${if (item.result.points > 1) "points" else "point"}"
        }*/
    }
}