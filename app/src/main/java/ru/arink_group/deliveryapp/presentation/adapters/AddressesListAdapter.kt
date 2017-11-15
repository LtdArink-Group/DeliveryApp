package ru.arink_group.deliveryapp.presentation.adapters

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import ru.arink_group.deliveryapp.R
import ru.arink_group.deliveryapp.domain.dao.Address
import ru.arink_group.deliveryapp.presentation.adapters.interfaces.OnAddressListener

/**
 * Created by kirillvs on 01.11.17.
 */
class AddressesListAdapter: RecyclerView.Adapter<AddressesListAdapter.ViewHolder>() {

    var addresses: MutableList<Address> = ArrayList<Address>()
    lateinit var listener: OnAddressListener

    fun updateAddresses(newAddresses: List<Address>) {
        if (addresses.size > 0) return
        addresses = newAddresses.toMutableList()
        addresses.sortBy { it.id }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val v = holder.view
        val context = holder.context
        val address = addresses[position]

        val addressTitleView = v.findViewById<TextView>(R.id.address_title)
        addressTitleView.text = "${address.title} - ${address.street}, ${address.house}"

        val cardItem = v.findViewById<CardView>(R.id.item_address_card)
        cardItem.setOnClickListener { listener.onAddressEdit(address) }

        val deleteButton = v.findViewById<ImageButton>(R.id.item_address_delete)
        deleteButton.setOnClickListener {
            if (addresses.size <= 1) {
                listener.onAddressRemove(address.id!!, addresses.size)
            } else {
                listener.onAddressRemove(address.id!!, addresses.size)
                addresses.removeAt(position)
                notifyItemRemoved(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_address, parent, false)
        return ViewHolder(v, parent.context)
    }

    override fun getItemCount(): Int {
        return addresses.size
    }


    class ViewHolder(val view: View, val context: Context): RecyclerView.ViewHolder(view)

}