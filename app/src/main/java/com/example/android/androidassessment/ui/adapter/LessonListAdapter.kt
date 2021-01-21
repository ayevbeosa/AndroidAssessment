package com.example.android.androidassessment.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.android.androidassessment.R
import com.example.android.androidassessment.data.local.LessonDb

class LessonListAdapter(
    private val chapterName: String,
    private val lessons: List<LessonDb>,
    private val listItemClickListener: ListItemClickListener
) :
    RecyclerView.Adapter<LessonListAdapter.LessonListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.lesson_list_item, parent, false)
        return LessonListViewHolder(view)
    }

    override fun onBindViewHolder(holder: LessonListViewHolder, position: Int) {
        holder.bind(lessons[position], listItemClickListener)
    }

    override fun getItemCount(): Int = if (lessons.isEmpty()) 0 else lessons.size

    inner class LessonListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val lessonName: TextView = itemView.findViewById(R.id.lesson_name)
        private val lessonThumbnail: ImageView = itemView.findViewById(R.id.lesson_thumbnail)

        fun bind(lessonDb: LessonDb, listItemClickListener: ListItemClickListener) {
            lessonName.text = lessonDb.name
            lessonThumbnail.load(lessonDb.icon)

            itemView.setOnClickListener {
                listItemClickListener.onClick(
                    chapterName,
                    lessonDb.name,
                    lessonDb.mediaUrl
                )
            }
        }
    }
}
