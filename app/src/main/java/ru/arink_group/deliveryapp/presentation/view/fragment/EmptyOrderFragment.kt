package ru.arink_group.deliveryapp.presentation.view.fragment


import android.os.Bundle
import android.app.Fragment
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import ru.arink_group.deliveryapp.R
import ru.arink_group.deliveryapp.presentation.view.activity.MenuActivity


/**
 * A simple [Fragment] subclass.
 */
class EmptyOrderFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater!!.inflate(R.layout.fragment_empty_order, container, false)
        val buttonToCategories = root.findViewById<Button>(R.id.empty_order_button_to_categories)
        buttonToCategories.setOnClickListener { goToCategories() }
        return root
    }

    private fun goToCategories() {
        val intent = Intent(activity, MenuActivity::class.java)
        activity.startActivity(intent)
    }

}// Required empty public constructor
