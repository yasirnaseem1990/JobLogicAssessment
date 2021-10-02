package com.joblogic.assessment.view.fragment.buy

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.joblogic.assessment.R
import com.joblogic.assessment.adapter.BuyListAdapter
import com.joblogic.assessment.databinding.FragmentToBuyBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ToBuyFragment : Fragment(R.layout.fragment_to_buy) {

    private val binding: FragmentToBuyBinding by viewBinding()

    private val viewModel: BuyListViewModel by viewModel()

    private lateinit var buyListViewAdapter: BuyListAdapter

    private lateinit var buyListLinearLayoutManager: LinearLayoutManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycle.addObserver(viewModel)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        buyListViewAdapter = BuyListAdapter(
            viewLifecycleOwner)
        buyListLinearLayoutManager = LinearLayoutManager(requireContext())

        binding.buyListRv.apply {
            layoutManager = buyListLinearLayoutManager
            adapter = buyListViewAdapter
        }

        viewModel.buyList.observe(viewLifecycleOwner) {
            if (it.isNullOrEmpty()) {
                binding.tvNoDataFound.visibility = View.VISIBLE
                buyListViewAdapter.setResponseList(it)
            } else {
                binding.tvNoDataFound.visibility = View.GONE
                buyListViewAdapter.setResponseList(it)
            }
        }

        viewModel.getShowLoadingResult().observe(viewLifecycleOwner) {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }

        viewModel.getErrorResult().observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
    }
}