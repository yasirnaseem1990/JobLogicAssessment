package com.joblogic.assessment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.joblogic.assessment.api.models.JobLogicModelResponse
import com.joblogic.assessment.databinding.ItemCallListLayoutBinding
import com.joblogic.assessment.databinding.ItemListLayoutBinding
import com.joblogic.assessment.view.fragment.buy.BuyListItemViewModel
import com.joblogic.assessment.view.fragment.call.CallListItemViewModel


class BuyListAdapter(
    private val lifecycleOwner: LifecycleOwner
) :  RecyclerView.Adapter<BuyListAdapter.BuyItemViewHolder>() {

    private var buyList: List<JobLogicModelResponse> = listOf()

    fun setResponseList(buyList: List<JobLogicModelResponse>) {
        this.buyList = buyList
        notifyDataSetChanged()
    }

    init {
        setHasStableIds(true)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyItemViewHolder {
        val viewModel = BuyListItemViewModel()

        val binding = ItemListLayoutBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return BuyItemViewHolder(binding, viewModel)
    }

    override fun onBindViewHolder(holder: BuyItemViewHolder, position: Int) {
        holder.onBind(buyList[position])
    }

    override fun getItemCount(): Int = buyList.size

    inner class BuyItemViewHolder(
        private val binding: ItemListLayoutBinding,
        private val viewModel: BuyListItemViewModel
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