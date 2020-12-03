package com.example.desafiowebservicesdigitalhouse.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiowebservicesdigitalhouse.R
import com.example.desafiowebservicesdigitalhouse.data.ComicsResponseWrapper
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_comic.view.*

class ComicListAdapter(
    var comicResponse: ComicsResponseWrapper?,
    private var clickListener: OnItemClickListener
) : RecyclerView.Adapter<ComicListAdapter.ComicViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    class ComicViewHolder(itemView: View, listener: OnItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        val comicCover: ImageView = itemView.comic_cover
        val comicIssueNumber: TextView = itemView.comic_issue_number

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(position)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_comic, parent, false)

        return ComicViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        val currentItem = comicResponse?.data?.results?.get(position)
        val imgUrl = "${currentItem?.thumbnail?.path}.${currentItem?.thumbnail?.extension}"
        val issueNumber = "#${currentItem?.issueNumber?.toInt()}"

        holder.comicIssueNumber.text = issueNumber
        Picasso.get().load(imgUrl).fit().centerInside().into(holder.comicCover)
    }

    override fun getItemCount() = comicResponse?.data?.results?.size ?: 0
}
