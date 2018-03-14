package ru.arink_group.deliveryapp.presentation.adapters

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import at.markushi.ui.CircleButton
import com.squareup.picasso.Picasso
import ru.arink_group.deliveryapp.R
import ru.arink_group.deliveryapp.domain.dao.Ingredient
import ru.arink_group.deliveryapp.domain.dao.Product

/**
 * Created by kirillvs on 25.10.17.
 */
class OrdersListAdapter: RecyclerView.Adapter<OrdersListAdapter.ViewHolder>() {

    var ordersList: MutableList<Product> = ArrayList<Product>()
    lateinit var productChangeListener: ProductChangeListener

    interface ProductChangeListener {
        fun onChangeProduct(product: Product)
        fun showPlaceholder()
        fun updateTotals()
    }

    fun updateOrder(product: Product) {
        val position = ordersList.indexOfFirst { it.id == product.id }
        if (product.count == 0) {
            ordersList.removeAt(position)
            if (ordersList.isEmpty()) {
                productChangeListener.showPlaceholder()
            } else {
                productChangeListener.updateTotals()
            }
            notifyDataSetChanged()
            return
        }
        ordersList[position] = product
        notifyItemChanged(position)
        productChangeListener.updateTotals()
    }

    fun setOrdersListAndNotifyAdapter(ordersList: MutableList<Product>) {
        this.ordersList = ordersList
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return ViewHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val orderProduct = ordersList[position]
        val view = holder.view

        val currencyString = holder.context.getString(R.string.currency)
        val summaryString = holder.context.getString(R.string.summary)

        val nameView = view.findViewById<TextView>(R.id.order_item_name)
        val summaryView = view.findViewById<TextView>(R.id.order_item_summary)
        val summaryAllView = view.findViewById<TextView>(R.id.all_summary)
        val productImage = view.findViewById<ImageView>(R.id.order_product_Image)

        Picasso.with(holder.context).load(orderProduct.imageUrl).placeholder(R.drawable.blur_image).into(productImage)

        val recyclerIngredient = view.findViewById<RecyclerView>(R.id.order_ingredient_recycler_view)
        val ingredientsAdapter = OrderIngredientsListAdapter(orderProduct.selectedIngredients)
        val llm = LinearLayoutManager(holder.context, LinearLayoutManager.VERTICAL, false)

        recyclerIngredient.adapter = ingredientsAdapter
        recyclerIngredient.layoutManager = llm

        val summaryProduct = orderProduct.count.toDouble() * orderProduct.selectedPortion.price
        val summaryIngredients = orderProduct.selectedIngredients.fold(0.0) { acc: Double, ingredient: Ingredient -> acc + ingredient.count.toDouble() * ingredient.price }

        val summaryAll = summaryProduct + summaryIngredients

        nameView.setText("${orderProduct.name} (${orderProduct.selectedPortion.name})")
        summaryView.setText("${orderProduct.count} x ${orderProduct.selectedPortion.price} $currencyString = $summaryProduct $currencyString")
        summaryAllView.setText("${summaryString}: $summaryAll $currencyString")

        val buttonPlus = view.findViewById<CircleButton>(R.id.order_button_plus)
        val buttonMinus = view.findViewById<CircleButton>(R.id.order_button_minus)

        buttonPlus.setOnClickListener {
            orderProduct.count ++
            productChangeListener.onChangeProduct(orderProduct)
        }
        buttonMinus.setOnClickListener {
            if (orderProduct.count < 1) return@setOnClickListener
            orderProduct.count --
            productChangeListener.onChangeProduct(orderProduct)
        }
        ingredientsAdapter.setOnIngredientChangeListener {
            productChangeListener.onChangeProduct(orderProduct)
        }
    }

    override fun getItemCount(): Int {
        return ordersList.size
    }

    class ViewHolder(val view:View, val context:Context): RecyclerView.ViewHolder(view)
}