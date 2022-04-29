package com.example.f1app_v1.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.f1app_v1.core.BaseViewHolder
import com.example.f1app_v1.data.model.DriverBaseInfo
import com.example.f1app_v1.data.model.TeamBaseInfo
import com.example.f1app_v1.databinding.DriverItemBinding
import com.example.f1app_v1.databinding.TeamItemBinding

class TeamAdapter(
    private val teamList: TeamBaseInfo,
    private val itemClickListener: OnTeamClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnTeamClickListener {
        fun onTeamClick(team: TeamBaseInfo.Stage.Team)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            TeamItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = TeamsViewHolder(itemBinding, parent.context)

        itemBinding.root.setOnClickListener {
            val position =
                holder.bindingAdapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                    ?: return@setOnClickListener
            itemClickListener.onTeamClick(teamList.stage.teams[position])
        }
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is TeamAdapter.TeamsViewHolder -> holder.bind(teamList.stage.teams[position])
        }
    }

    override fun getItemCount(): Int = teamList.stage.teams.size


    private inner class TeamsViewHolder(val binding: TeamItemBinding, val context: Context) :
        BaseViewHolder<TeamBaseInfo.Stage.Team>(binding.root) {
        override fun bind(item: TeamBaseInfo.Stage.Team) {
            binding.txtPosition.text =
                "${item.result.position} - ${item.result.points} ${if (item.result.points > 1) "points" else "point"}"
            binding.txtName.text = item.name
            binding.txtNationality.text = item.nationality
        }
    }
}