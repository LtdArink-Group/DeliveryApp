package ru.arink_group.deliveryapp.presentation.adapters

import android.content.Context
import android.support.design.widget.TextInputEditText
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import ru.arink_group.deliveryapp.R
import ru.arink_group.deliveryapp.domain.Address
import ru.arink_group.deliveryapp.presentation.adapters.interfaces.OnAddressRemoveListener
import ru.arink_group.deliveryapp.presentation.model.CoolAnimation

/**
 * Created by kirillvs on 01.11.17.
 */
class AddressesListAdapter(private val errorCantBeBlankString: String): RecyclerView.Adapter<AddressesListAdapter.ViewHolder>() {

    private var addresses: MutableList<Address> = ArrayList<Address>()
    private var addressesViews: MutableList<View> = ArrayList<View>()
    lateinit var listener: OnAddressRemoveListener

    fun updateAddresses(newAddresses: List<Address>) {
        if (addresses.size > 0) return
        addresses = newAddresses.toMutableList()
        addresses.sortBy { it.id }
        notifyDataSetChanged()
    }

    fun updateAddress(newAddress: Address) {
        val address = addresses.find { it.id == newAddress.id }
        if (address != null) {
            val index = addresses.indexOf(address)

            address.house = newAddress.house
            address.floor = newAddress.floor
            address.entrance = newAddress.entrance
            address.code = newAddress.code
            address.title = newAddress.title
            address.street = newAddress.street
            address.office = newAddress.office
            address.city = newAddress.city

            notifyItemChanged(index)
//            notifyDataSetChanged()
        }
    }

    fun addNewAddress() {
        val address = Address(null)
        addresses.add(address)
        notifyDataSetChanged()
    }

    fun verifyAddressesList(): Boolean {
        if (addresses.size == 0) return false
        var flag = true

        addressesViews.forEach {
            if (!verifyAddressViews(it)) flag = false
        }

        return flag
    }

    private fun verifyAddressViews(addressView: View): Boolean {
        var flag = true

        val titleView = addressView.findViewById<TextInputEditText>(R.id.address_title)
        val cityView = addressView.findViewById<TextInputEditText>(R.id.address_city)
        val streetView = addressView.findViewById<TextInputEditText>(R.id.address_street)
        val houseView = addressView.findViewById<TextInputEditText>(R.id.address_house)

//        -- Not required! --
//        val codeView = addressView.findViewById<TextInputEditText>(R.id.address_code)
//        val entranceView = addressView.findViewById<TextInputEditText>(R.id.address_entrance)
//        val floorView = addressView.findViewById<TextInputEditText>(R.id.address_floor)
//        val officeView = addressView.findViewById<TextInputEditText>(R.id.address_office)

        if (!verifyViewItem(titleView)) flag = false
        if (!verifyViewItem(cityView)) flag = false
        if (!verifyViewItem(streetView)) flag = false
        if (!verifyViewItem(houseView)) flag = false

//                -- Not required! --
//        if (!verifyViewItem(codeView)) flag = false
//        if (!verifyViewItem(entranceView)) flag = false
//        if (!verifyViewItem(floorView)) flag = false
//        if (!verifyViewItem(officeView)) flag = false

        return flag
    }

    private fun verifyViewItem(v: TextInputEditText): Boolean {
        if (v.text.toString().isEmpty()) {
            v.error = errorCantBeBlankString
            return false
        }

        return true
    }

    private fun collapseAllExcept(v: View) {
        addressesViews.forEach {
            if (it == v) return
            val collapseView = it.findViewById<ImageButton>(R.id.collapse_address_button)
            val expandButton = it.findViewById<ImageButton>(R.id.expand_address_button)
            val collapseButton = it.findViewById<ImageButton>(R.id.collapse_address_button)
            CoolAnimation.collapse(collapseView)
            collapseButton.visibility = ImageButton.GONE
            expandButton.visibility = ImageButton.VISIBLE

        }
    }

    fun getUpdatedList(): MutableList<Address> {
        addresses.forEachIndexed { index, address ->
            val v = addressesViews[index]

            val titleView = v.findViewById<TextInputEditText>(R.id.address_title)
            val cityView = v.findViewById<TextInputEditText>(R.id.address_city)
            val codeView = v.findViewById<TextInputEditText>(R.id.address_code)
            val entranceView = v.findViewById<TextInputEditText>(R.id.address_entrance)
            val floorView = v.findViewById<TextInputEditText>(R.id.address_floor)
            val houseView = v.findViewById<TextInputEditText>(R.id.address_house)
            val officeView = v.findViewById<TextInputEditText>(R.id.address_office)
            val streetView = v.findViewById<TextInputEditText>(R.id.address_street)

            address.title = titleView.text.toString()
            address.city = cityView.text.toString()
            address.code = codeView.text.toString()
            address.entrance = entranceView.text.toString()
            address.floor = floorView.text.toString()
            address.house = houseView.text.toString()
            address.office = officeView.text.toString()
            address.street = streetView.text.toString()
        }

        return addresses
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val v = holder.view
        val context = holder.context
        val address = addresses[position]

        val addrString = context.getString(R.string.account_address_title)

        val deleteAddressButton = v.findViewById<ImageButton>(R.id.delete_address_button)

        val titleTextView = v.findViewById<TextView>(R.id.address_title_text)
        val titleView = v.findViewById<TextInputEditText>(R.id.address_title)
        val cityView = v.findViewById<TextInputEditText>(R.id.address_city)
        val codeView = v.findViewById<TextInputEditText>(R.id.address_code)
        val entranceView = v.findViewById<TextInputEditText>(R.id.address_entrance)
        val floorView = v.findViewById<TextInputEditText>(R.id.address_floor)
        val houseView = v.findViewById<TextInputEditText>(R.id.address_house)
        val officeView = v.findViewById<TextInputEditText>(R.id.address_office)
        val streetView = v.findViewById<TextInputEditText>(R.id.address_street)

        titleView.setText(address.title)
        cityView.setText(address.city)
        codeView.setText(address.code)
        entranceView.setText(address.entrance)
        floorView.setText(address.floor)
        houseView.setText(address.house)
        officeView.setText(address.office)
        streetView.setText(address.street)

        if (titleView.text.isNotEmpty()) titleTextView.text = "$addrString (${titleView.text})"

        titleView.setOnFocusChangeListener { v, hasFocus -> if (titleView.text.isNotEmpty()) titleTextView.text = "$addrString (${titleView.text})" }

        val expandableLayout = v.findViewById<LinearLayout>(R.id.expandable_address)

        val expandButton = v.findViewById<ImageButton>(R.id.expand_address_button)
        val collapseButton = v.findViewById<ImageButton>(R.id.collapse_address_button)

        expandButton.setOnClickListener {
            CoolAnimation.expand(expandableLayout)
            collapseButton.visibility = ImageButton.VISIBLE
            expandButton.visibility = ImageButton.GONE
//            collapseAllExcept(v)
        }

        collapseButton.setOnClickListener {
            CoolAnimation.collapse(expandableLayout)
            collapseButton.visibility = ImageButton.GONE
            expandButton.visibility = ImageButton.VISIBLE
        }

        if (position == 0) {
            deleteAddressButton.visibility = ImageButton.GONE
        } else {
            deleteAddressButton.setOnClickListener {
                CoolAnimation.collapse(expandableLayout)
                addresses.removeAt(position)
                addressesViews.removeAt(position)
                if (address.id != null)
                    listener.onAddressRemove(address.id!!)
                notifyDataSetChanged()
            }
        }


        if (address.id != null) {
            collapseButton.visibility = ImageButton.GONE
            expandButton.visibility = ImageButton.VISIBLE
            CoolAnimation.collapse(expandableLayout)
        } else {
//            collapseAllExcept(v)
            collapseButton.visibility = ImageButton.VISIBLE
            expandButton.visibility = ImageButton.GONE
            CoolAnimation.expand(expandableLayout)
        }


        addressesViews.add(position, v)
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