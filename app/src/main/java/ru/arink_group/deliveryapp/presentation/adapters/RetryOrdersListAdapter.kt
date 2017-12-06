package ru.arink_group.deliveryapp.presentation.adapters

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import at.markushi.ui.CircleButton
import com.squareup.picasso.Picasso
import ru.arink_group.deliveryapp.R
import ru.arink_group.deliveryapp.domain.dao.Ingredient
import ru.arink_group.deliveryapp.domain.dao.OrderIngredient
import ru.arink_group.deliveryapp.domain.dao.OrderProduct
import ru.arink_group.deliveryapp.domain.dao.Product

/**
 * Created by kirillvs on 25.10.17.
 */
class RetryOrdersListAdapter(val ordersList: List<OrderProduct>) : RecyclerView.Adapter<RetryOrdersListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return ViewHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val orderProduct = ordersList[position]
        val view = holder.view

        val currencyString = holder.context.getString(R.string.currency)
        val summaryString = holder.context.getString(R.string.summary)
        val numString = holder.context.getString(R.string.num)
        val costString = holder.context.getString(R.string.costSummary)

        val nameView = view.findViewById<TextView>(R.id.order_item_name)
        val summaryView = view.findViewById<TextView>(R.id.order_item_summary)
        val summaryAllView = view.findViewById<TextView>(R.id.all_summary)
        val productImage = view.findViewById<ImageView>(R.id.order_product_Image)
        val btnGroup = view.findViewById<LinearLayout>(R.id.order_button_group)

        productImage.visibility = View.GONE
        btnGroup.visibility = View.GONE

        val recyclerIngredient = view.findViewById<RecyclerView>(R.id.order_ingredient_recycler_view)
        val ingredientsAdapter = RetryOrderIngredientsListAdapter(orderProduct.ingredients)
        val llm = LinearLayoutManager(holder.context, LinearLayoutManager.VERTICAL, false)

        recyclerIngredient.adapter = ingredientsAdapter
        recyclerIngredient.layoutManager = llm

        val summaryProduct = orderProduct.totalCost
        val summaryIngredients = orderProduct.ingredients.fold(0.0) { acc: Double, ingredient: OrderIngredient -> acc + ingredient.totalCost }

        val summaryAll = summaryProduct + summaryIngredients

        nameView.setText("${orderProduct.productTitle} (${orderProduct.mainOption})")
        summaryView.setText("$numString: ${orderProduct.qty}, $costString: $summaryProduct $currencyString")
        summaryAllView.setText("${summaryString}: $summaryAll $currencyString")

        val buttonPlus = view.findViewById<CircleButton>(R.id.order_button_plus)
        val buttonMinus = view.findViewById<CircleButton>(R.id.order_button_minus)

        buttonMinus.visibility = View.GONE
        buttonPlus.visibility = View.GONE

    }

    override fun getItemCount(): Int = ordersList.size

    class ViewHolder(val view:View, val context:Context): RecyclerView.ViewHolder(view)
}