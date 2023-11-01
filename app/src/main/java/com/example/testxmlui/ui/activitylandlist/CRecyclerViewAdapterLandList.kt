package com.example.testxmlui.ui.activitylandlist

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import com.example.testxmlui.R
import com.example.testxmlui.model.CLand

//https://developer.android.com/develop/ui/views/layout/recyclerview
class CRecyclerViewAdapterLandList(
    private val items : List<CLand>
): RecyclerView.Adapter<CRecyclerViewAdapterLandList.ViewHolder>()
{
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val root : LinearLayoutCompat
                = view.findViewById(R.id.LinearLayoutRoot)
        val textViewHeader: TextView
                = view.findViewById(R.id.TextViewHeader)
        val textViewPrice: TextView
                = view.findViewById(R.id.TextViewPrice)
        val textViewSquare: TextView
                = view.findViewById(R.id.TextViewSquare)
        val textViewType: TextView
                = view.findViewById(R.id.TextViewType)
        fun setLand(land : CLand)
        {
            textViewHeader.text = land.header
            textViewPrice.text = "${land.price} руб."
            textViewSquare.text = "${land.square} кв.м."
            textViewType.text = land.type
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.land_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setLand(items[position])
    }

}