package ru.arink_group.deliveryapp.presentation.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import at.markushi.ui.CircleButton
import ru.arink_group.deliveryapp.R
import ru.arink_group.deliveryapp.domain.dao.Ingredient
import ru.arink_group.deliveryapp.domain.dao.OrderIngredient

/**
 * Created by kirillvs on 25.10.17.
 */
class RetryOrderIngredientsListAdapter(val ingredientsList: List<OrderIngredient>): RecyclerView.Adapter<RetryOrderIngredientsListAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val orderIngredient = ingredientsList[position]
        val view = holder.view

        val nameView = view.findViewById<TextView>(R.id.order_ingredient_item_name)
        val summaryView = view.findViewById<TextView>(R.id.order_ingredient_item_summary)

        val buttonMinus = view.findViewById<CircleButton>(R.id.order_ingredient_button_minus)
        val buttonPlus = view.findViewById<CircleButton>(R.id.order_ingredient_button_plus)
        val btnGroup = view.findViewById<LinearLayout>(R.id.order_ingredients_button_group)

        buttonMinus.visibility = View.GONE
        buttonPlus.visibility = View.GONE
        btnGroup.visibility = View.GONE

        val numString = holder.context.getString(R.string.num)
        val costString = holder.context.getString(R.string.costSummary)

        nameView.setText(orderIngredient.name)
        summaryView.setText("$numString: ${orderIngredient.qty}, $costString: ${orderIngredient.totalCost} ${holder.context.resources.getString(R.string.currency)}")
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