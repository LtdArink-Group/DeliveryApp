package ru.arink_group.deliveryapp.presentation.view.fragment


import android.os.Bundle
import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import ru.arink_group.deliveryapp.R


/**
 * A simple [Fragment] subclass.
 */
class EmptyListFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_empty_list, container, false)
    }

}// Required empty public constructor
