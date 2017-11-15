package ru.arink_group.deliveryapp.presentation.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import ru.arink_group.deliveryapp.R

/**
 * Created by kirillvs on 15.11.17.
 */
class OrderAddressesListAdapter(val context: Activity, val layoutId: Int, val list: ArrayList<String>): ArrayAdapter<String>(context, layoutId, list) {
    private val flater: LayoutInflater = context.layoutInflater
    private val substrDimen = context.resources.getInteger(R.integer.item_address_spinner_truncate)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var title = getItem(position)

        if (title.length >= substrDimen) title = "${title.substring(0, substrDimen)}..."

        val view = flater.inflate(layoutId, parent, false)

        val titleView = view.findViewById<TextView>(R.id.text1)

        titleView.text = title

        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val title = getItem(position)

        val view = flater.inflate(layoutId, parent, false)

        val titleView = view.findViewById<TextView>(R.id.text1)

        titleView.text = title

        return view
    }
}