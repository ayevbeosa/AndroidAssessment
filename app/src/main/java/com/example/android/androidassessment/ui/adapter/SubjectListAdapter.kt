package com.example.android.androidassessment.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.androidassessment.R
import com.example.android.androidassessment.data.local.SubjectWithChapters
import com.example.android.androidassessment.databinding.SubjectListItemBinding

class SubjectListAdapter(private val onClickListener: OnClickListener) :
    RecyclerView.Adapter<SubjectListAdapter.SubjectListViewHolder>() {

    private val subjects = ArrayList<SubjectWithChapters>()

    fun setSubjectsList(newSubjectList: List<SubjectWithChapters>) {
        val diffUtilCallback = SubjectListDiffUtilCallback(subjects, newSubjectList)
        val result = DiffUtil.calculateDiff(diffUtilCallback)
        subjects.clear()
        subjects.addAll(newSubjectList)
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectListViewHolder {
        val subjectListItemBinding: SubjectListItemBinding = SubjectListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return SubjectListViewHolder(subjectListItemBinding)
    }

    override fun onBindViewHolder(holder: SubjectListViewHolder, position: Int) {
        holder.bind(subjects[position], onClickListener)
    }

    override fun getItemCount() = subjects.size

    inner class SubjectListViewHolder(private val binding: SubjectListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(subjectWithChapters: SubjectWithChapters, onClickListener: OnClickListener) {
            binding.subjectName.text = subjectWithChapters.subjectDb.name
            binding.subjectContainer.setBackgroundColor(setBackgroundColor(subjectWithChapters.subjectDb.name))
            binding.subjectIcon.setBackgroundResource(setBackgroundResource(subjectWithChapters.subjectDb.name))
            binding.root.setOnClickListener { onClickListener.onClick(subjectWithChapters) }
        }

        private fun setBackgroundColor(subjectName: String): Int {
            return when (subjectName) {
                "Mathematics" -> 0xFFEA7052.toInt()
                "English" -> 0xFF7B7FDA.toInt()
                "Chemistry" -> 0xFFF9AD6D.toInt()
                "Biology" -> 0xFF68BC98.toInt()
                "Physics" -> 0xFF7B7FDA.toInt()
                else -> 0xFFEA7052.toInt()
            }
        }

        private fun setBackgroundResource(subjectName: String): Int {
            return when (subjectName) {
                "Mathematics" -> R.drawable.ic_math
                "English" -> R.drawable.ic_eng
                "Chemistry" -> R.drawable.ic_che
                "Biology" -> R.drawable.ic_bio
                "Physics" -> R.drawable.ic_phy
                else -> R.drawable.ic_eng
            }
        }
    }

    class OnClickListener(val clickListener: (subjectWithChapters: SubjectWithChapters) -> Unit) {
        fun onClick(subjectWithChapters: SubjectWithChapters) = clickListener(subjectWithChapters)
    }

    inner class SubjectListDiffUtilCallback(
        private val oldList: List<SubjectWithChapters>,
        private val newList: List<SubjectWithChapters>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition].subjectDb.id == newList[newItemPosition].subjectDb.id
    }
}