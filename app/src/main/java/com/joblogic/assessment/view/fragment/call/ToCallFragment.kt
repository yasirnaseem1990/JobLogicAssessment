package com.joblogic.assessment.view.fragment.call

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.joblogic.assessment.R
import com.joblogic.assessment.adapter.CallListAdapter
import com.joblogic.assessment.databinding.FragmentToCallBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class ToCallFragment : Fragment(R.layout.fragment_to_call) {

    private val binding: FragmentToCallBinding by viewBinding()

    private val viewModel: CallListViewModel by viewModel()

    private lateinit var callListViewAdapter: CallListAdapter

    private lateinit var callListLinearLayoutManager: LinearLayoutManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycle.addObserver(viewModel)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        callListViewAdapter = CallListAdapter(
            viewLifecycleOwner)
        callListLinearLayoutManager = LinearLayoutManager(requireContext())

        binding.callListRv.apply {
            layoutManager = callListLinearLayoutManager
            adapter = callListViewAdapter
        }

        viewModel.guyList.observe(viewLifecycleOwner) {
            if (it.isNullOrEmpty()) {
                binding.tvNoDataFound.visibility = View.VISIBLE
                callListViewAdapter.setCallList(it)
            } else {
                binding.tvNoDataFound.visibility = View.GONE
                callListViewAdapter.setCallList(it)
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