package com.joblogic.assessment.view.fragment.sell

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.joblogic.assessment.R
import com.joblogic.assessment.adapter.BuyListAdapter
import com.joblogic.assessment.databinding.FragmentToSellBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ToSellFragment : Fragment(R.layout.fragment_to_sell) {

    private val binding: FragmentToSellBinding by viewBinding()

    private val viewModel: SellListViewModel by viewModel()

    private lateinit var selListViewAdapter: BuyListAdapter

    private lateinit var buyListLinearLayoutManager: LinearLayoutManager


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycle.addObserver(viewModel)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        selListViewAdapter = BuyListAdapter(
            viewLifecycleOwner)
        buyListLinearLayoutManager = LinearLayoutManager(requireContext())

        binding.sellListRv.apply {
            layoutManager = buyListLinearLayoutManager
            adapter = selListViewAdapter
        }


        viewModel.sellList.observe(viewLifecycleOwner) {
            if (it.isNullOrEmpty()) {
                binding.tvNoDataFound.visibility = View.VISIBLE
                selListViewAdapter.setResponseList(it)
            } else {
                binding.tvNoDataFound.visibility = View.GONE
                selListViewAdapter.setResponseList(it)
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

    companion object {}
}