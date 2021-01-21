package com.example.android.androidassessment.ui.subject

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.androidassessment.databinding.SubjectFragmentBinding
import com.example.android.androidassessment.model.SubjectNavigation
import com.example.android.androidassessment.ui.adapter.ChapterListAdapter
import com.example.android.androidassessment.ui.adapter.ListItemClickListener

class SubjectFragment : Fragment() {

    private var _binding: SubjectFragmentBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: SubjectViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SubjectFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = SubjectFragmentArgs.fromBundle(requireArguments())
        val subjectWithChapters = args.subjectWithChapters

        val factory = SubjectViewModelFactory(subjectWithChapters)
        viewModel = ViewModelProvider(this, factory)[SubjectViewModel::class.java]

        val adapter =
            ChapterListAdapter(ListItemClickListener { chapterName, lessonName, lessonMediaUrl ->
                viewModel.displaySelectedLesson(
                    SubjectNavigation(
                        chapterName,
                        lessonName,
                        lessonMediaUrl
                    )
                )
            })

        viewModel.selectedSubjectWithChapters.observe(viewLifecycleOwner, {
            it?.let {
                adapter.setChapterWithLessonsList(subjectWithChapters.chapters)
            }
        })

        binding.chapterList.layoutManager =
            LinearLayoutManager(requireContext())
        binding.chapterList.adapter = adapter
        binding.chapterList.setHasFixedSize(true)

        viewModel.navigateToSelectedItem.observe(viewLifecycleOwner, {
            it?.let {
                this.findNavController().navigate(
                    SubjectFragmentDirections.actionSubjectFragmentToVideoPlayerFragment(it)
                )
                viewModel.displaySelectedLessonComplete()
            }
        })

        binding.chapterList.adapter = adapter

        // Set title to subject name
        binding.subjectTitle.text = subjectWithChapters.subjectDb.name
        binding.backImageView.setOnClickListener {
            this.findNavController().navigate(
                SubjectFragmentDirections.actionSubjectFragmentToDashboardFragment()
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }
}