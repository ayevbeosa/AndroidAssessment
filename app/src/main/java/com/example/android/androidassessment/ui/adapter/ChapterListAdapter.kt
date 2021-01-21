package com.example.android.androidassessment.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.androidassessment.R
import com.example.android.androidassessment.data.local.ChapterWithLessons

class ChapterListAdapter(private val listItemClickListener: ListItemClickListener) :
    RecyclerView.Adapter<ChapterListAdapter.ChapterListViewHolder>() {

    private val chapters = ArrayList<ChapterWithLessons>()

    fun setChapterWithLessonsList(newChapters: List<ChapterWithLessons>) {
        val diffUtilCallback = ChapterListDiffUtilCallback(chapters, newChapters)
        val result = DiffUtil.calculateDiff(diffUtilCallback)
        chapters.clear()
        chapters.addAll(newChapters)
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.chapter_list_item, parent, false)
        return ChapterListViewHolder(view)
    }

    override fun getItemCount() = chapters.size

    override fun onBindViewHolder(holder: ChapterListViewHolder, position: Int) {
        holder.bind(chapters[position], listItemClickListener)
    }

    inner class ChapterListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val recyclerView: RecyclerView = itemView.findViewById(R.id.lesson_list)
        private val titleTextView: TextView = itemView.findViewById(R.id.chapter_title)

        fun bind(
            chapterWithLessons: ChapterWithLessons,
            listItemClickListener: ListItemClickListener
        ) {
            titleTextView.text = chapterWithLessons.chapterDb.name
            val itemListLayoutManager =
                LinearLayoutManager(recyclerView.context, LinearLayoutManager.HORIZONTAL, false)
            itemListLayoutManager.initialPrefetchItemCount = 4
            recyclerView.apply {
                layoutManager = itemListLayoutManager
                adapter = LessonListAdapter(
                    chapterName = chapterWithLessons.chapterDb.name,
                    chapterWithLessons.lessons,
                    listItemClickListener
                )
            }
        }
    }

    inner class ChapterListDiffUtilCallback(
        private val oldList: List<ChapterWithLessons>,
        private val newList: List<ChapterWithLessons>
    ) : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition] === newList[newItemPosition]

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition].chapterDb.id == newList[newItemPosition].chapterDb.id
    }
}