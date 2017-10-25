package ru.arink_group.deliveryapp.presentation.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.arink_group.deliveryapp.R
import ru.arink_group.deliveryapp.domain.Ingredient

/**
 * Created by kirillvs on 25.10.17.
 */
class OrderIngredientsListAdapter(val ingredientsList: Array<Ingredient>): RecyclerView.Adapter<OrderIngredientsListAdapter.ViewHolder>() {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val orderIngredient = ingredientsList[position]
        val view = holder.view

        val nameView = view.findViewById<TextView>(R.id.order_ingredient_item_name)
        val summaryView = view.findViewById<TextView>(R.id.order_ingredient_item_summary)

        nameView.setText(orderIngredient.name)
        summaryView.setText("${orderIngredient.count} x ${orderIngredient.price} ${holder.context.resources.getString(R.string.currency)} = ${orderIngredient.price * orderIngredient.count.toDouble()} ${holder.context.resources.getString(R.string.currency)}")

    }

    override fun getItemCount(): Int {
        return ingredientsList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_order_ingredient, parent, false)
        return ViewHolder(v, parent.context)
    }

    class ViewHolder(val view: View, val context: Context): RecyclerView.ViewHolder(view)
}