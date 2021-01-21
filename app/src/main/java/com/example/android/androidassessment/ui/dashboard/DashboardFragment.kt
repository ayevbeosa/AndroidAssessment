package com.example.android.androidassessment.ui.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android.androidassessment.data.utils.ResultState
import com.example.android.androidassessment.databinding.DashboardFragmentBinding
import com.example.android.androidassessment.ui.adapter.SubjectListAdapter
import com.example.android.androidassessment.utils.gone
import com.example.android.androidassessment.utils.observeNotNull
import com.example.android.androidassessment.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: DashboardFragmentBinding? = null

    private val binding get() = _binding!!

    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DashboardFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = SubjectListAdapter(SubjectListAdapter.OnClickListener {
            viewModel.displaySelectedSubject(it)
        })
        binding.subjectList.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.subjectList.adapter = adapter

        viewModel.subjects.observeNotNull(viewLifecycleOwner) { state ->
            when (state) {
                is ResultState.Loading -> {
                    binding.progressbar.visible()
                }
                is ResultState.Success -> {
                    adapter.setSubjectsList(state.data)
                    binding.progressbar.gone()
                }
            }
        }

        viewModel.navigateToSelectedItem.observe(viewLifecycleOwner, {
            it?.let {
                this.findNavController().navigate(
                    DashboardFragmentDirections.actionDashboardFragmentToSubjectFragment(it)
                )
                viewModel.displaySelectedSubjectComplete()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}