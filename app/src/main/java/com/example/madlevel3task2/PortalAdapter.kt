package com.example.madlevel3task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_portal.view.*

class PortalAdapter (
    private val portal: List<Portal>,
    private val onPortalClick: (Portal) -> Unit
    ) : RecyclerView.Adapter<PortalAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener { onPortalClick(portal[adapterPosition]) }
        }
        fun databind(portal: Portal) {
            itemView.tvprotaltitle.text = portal.title
            itemView.tvportalUrl.text = portal.url
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_portal, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return portal.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(portal[position])
    }
}