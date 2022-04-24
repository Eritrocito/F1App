package com.example.f1app_v1.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.f1app_v1.core.BaseViewHolder
import com.example.f1app_v1.data.model.Driver
import com.example.f1app_v1.data.model.RaceBaseInfo
import com.example.f1app_v1.databinding.DriverItemBinding
import com.example.f1app_v1.databinding.RaceItemBinding

class RaceAdapter(private val RaceList:RaceBaseInfo) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            RaceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = RacesViewHolder(itemBinding, parent.context)
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is RacesViewHolder -> holder.bind(RaceList.stage.stages[position])
        }
    }

    override fun getItemCount()= RaceList.stage.stages.size


    private inner class RacesViewHolder(val binding: RaceItemBinding, val context: Context) :
        BaseViewHolder<RaceBaseInfo.Stage.Race>(binding.root) {
        override fun bind(item: RaceBaseInfo.Stage.Race) {
            binding.txtGPname.text = item.description
            binding.txtStart.text ="Start ${item.scheduled}"
            binding.txtEnd.text="End ${item.scheduled_end}"
        }
    }
}