package com.joblogic.assessment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.joblogic.assessment.api.models.JobLogicModelResponse
import com.joblogic.assessment.databinding.ItemCallListLayoutBinding
import com.joblogic.assessment.view.fragment.call.CallListItemViewModel


class CallListAdapter(
    private val lifecycleOwner: LifecycleOwner
) :  RecyclerView.Adapter<CallListAdapter.CallItemViewHolder>() {

    private var callList: List<JobLogicModelResponse> = listOf()

    fun setCallList(callList: List<JobLogicModelResponse>) {
        this.callList = callList
        notifyDataSetChanged()
    }

    init {
        setHasStableIds(true)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallItemViewHolder {
        val viewModel = CallListItemViewModel()

        val binding = ItemCallListLayoutBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return CallItemViewHolder(binding, viewModel)
    }

    override fun onBindViewHolder(holder: CallItemViewHolder, position: Int) {
        holder.onBind(callList[position])
    }

    override fun getItemCount(): Int = callList.size

    inner class CallItemViewHolder(
        private val binding: ItemCallListLayoutBinding,
        private val viewModel: CallListItemViewModel
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.viewModel = viewModel
            binding.lifecycleOwner = lifecycleOwner
        }

        fun onBind(item: JobLogicModelResponse) {
            viewModel.bind(item)
            binding.executePendingBindings()
        }
    }

}