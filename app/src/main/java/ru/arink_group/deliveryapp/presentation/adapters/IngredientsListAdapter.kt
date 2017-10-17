package ru.arink_group.deliveryapp.presentation.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import at.markushi.ui.CircleButton
import ru.arink_group.deliveryapp.R
import ru.arink_group.deliveryapp.domain.Ingredient

/**
 * Created by kirillvs on 17.10.17.
 */
class IngredientsListAdapter(internal val ingredientsList: Array<Ingredient>): RecyclerView.Adapter<IngredientsListAdapter.ViewHolder>() {

    lateinit var listener: OnIngredientButtonClickListener

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val v = holder.view

        val ingredientName = v.findViewById<TextView>(R.id.ingredient_name)
        ingredientName.text = ingredientsList.get(position).name

        val ingredientMinus = v.findViewById<CircleButton>(R.id.ingredient_minus)
        val ingredientPlus = v.findViewById<CircleButton>(R.id.ingredient_plus)

        val ingredientCount = v.findViewById<TextView>(R.id.ingredient_count)
        ingredientCount.text = ingredientsList[position].count.toString()

        ingredientMinus.setOnClickListener({
            if(ingredientsList[position].count == 0) return@setOnClickListener
            ingredientsList[position].count = ingredientsList[position].count - 1
            ingredientCount.text = ingredientsList[position].count.toString()
            listener.onIngredientClicked(ingredientsList[position])
        })

        ingredientPlus.setOnClickListener({
            ingredientsList[position].count = ingredientsList[position].count + 1
            ingredientCount.text = ingredientsList[position].count.toString()
            listener.onIngredientClicked(ingredientsList[position])
        })
    }

    override fun getItemCount(): Int {
        return ingredientsList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_ingredient, parent, false)
        return ViewHolder(v)
    }

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view)
}