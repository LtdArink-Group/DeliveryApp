package ru.arink_group.deliveryapp.presentation.adapters

import android.content.Context
import android.content.res.ColorStateList
import android.support.text.emoji.EmojiCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

import com.squareup.picasso.Picasso

import java.util.ArrayList
import java.util.HashMap

import at.markushi.ui.CircleButton
import ru.arink_group.deliveryapp.R
import ru.arink_group.deliveryapp.domain.dao.Product
import ru.arink_group.deliveryapp.presentation.adapters.interfaces.OnIngredientClickListener
import ru.arink_group.deliveryapp.presentation.adapters.interfaces.OnItemClickListener

/**
 * Created by kirillvs on 03.10.17.
 */

class ProductsListAdapter : RecyclerView.Adapter<ProductsListAdapter.ViewHolder>() {

    private var products: List<Product> = ArrayList()
    private var listener: OnItemClickListener<Product>? = null
    private var ingredientListener: OnIngredientClickListener? = null
    private val productCounts = ArrayList<TextView>()
    private val radioButtons = ArrayList<Map<String, RadioButton>>()

    fun setProducts(products: List<Product>) {
        this.products = products
        notifyDataSetChanged()
    }

    fun updateProductsFromBasket(selectedProducts: List<Product>) {
        searchProductsForUpdate(selectedProducts)
    }

    private fun searchProductsForUpdate(sps: List<Product>) {
        for (i in products.indices) {
            for (selectedProduct in sps) {
                if (products[i].id == selectedProduct.id) {
                    this.updateProduct(products[i], selectedProduct, i)
                }
            }
        }
    }

    private fun updateProduct(product: Product, selectedProduct: Product, pos: Int) {
        product.count = selectedProduct.count
        product.selectedPortion = selectedProduct.selectedPortion
        product.selectedIngredients = selectedProduct.selectedIngredients
        if (productCounts.size - 1 < pos) return
        productCounts[pos].text = product.count.toString()
        radioButtons[pos][product.selectedPortion.name]!!.toggle()
    }

    fun setListener(listener: OnItemClickListener<Product>) {
        this.listener = listener
    }

    fun setIngredientListener(listener: OnIngredientClickListener) {
        this.ingredientListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ViewHolder(v, parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        val view = holder.view
        val rg = view.findViewById<RadioGroup>(R.id.portion_list_group)

        val productImage = view.findViewById<ImageView>(R.id.product_Image)
        Picasso.with(holder.context).load(product.imageUrl).into(productImage)

        val nameView = view.findViewById<TextView>(R.id.product_name)
        nameView.text = product.name

        val descriptionView = view.findViewById<TextView>(R.id.product_description)
        descriptionView.text = product.brief

        val priceView = view.findViewById<TextView>(R.id.product_price)
        priceView.text = EmojiCompat.get().process("\u20BD ${product.selectedPortion.price}")

        val states = arrayOf(intArrayOf(android.R.attr.state_checked), // checked
                intArrayOf(-android.R.attr.state_checked))// unchecked

        val colors = intArrayOf(ContextCompat.getColor(holder.context, R.color.colorCheckedText), ContextCompat.getColor(holder.context, R.color.colorPrimaryText))

        if (rg.childCount > 0) return  // TODO костыль, тк дублируются радиобатоны, если оставить так,тогда надо передалать реренддеринг элемента при обновлении
        val bthGroup = HashMap<String, RadioButton>()
        radioButtons.add(position, bthGroup)
        val selectedPortionIndex = product.selectedPortionIndex
        for (i in 0 until product.portions.size) {
            val rb = RadioButton(holder.context)
            rb.width = holder.context.resources.getDimensionPixelSize(R.dimen.ingredient_radiobutton_witdh)
            rb.setButtonDrawable(android.R.color.transparent)
            rb.text = product.portions[i].name
            rb.gravity = Gravity.CENTER
            if (product.portions.size == 1) {
                rb.background = ContextCompat.getDrawable(holder.context, R.drawable.bg_item_ingredient_single)
            } else if (i == 0) {
                rb.background = ContextCompat.getDrawable(holder.context, R.drawable.bg_item_ingredient_left)
            } else if (i == product.portions.size - 1) {
                rb.background = ContextCompat.getDrawable(holder.context, R.drawable.bg_item_ingredient_right)
            } else {
                rb.background = ContextCompat.getDrawable(holder.context, R.drawable.bg_item_ingredient)
            }
            val csl = ColorStateList(states, colors)
            rb.setTextColor(csl)
            rg.addView(rb)
            rb.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    product.setSelectedPortionByName(buttonView.text.toString())
                    priceView.text = holder.context.resources.getString(R.string.currency) + product.selectedPortion.price.toString()
                }
            }
            if (i == selectedPortionIndex) {
                rg.check(rb.id)
            }
            bthGroup.put(product.portions[i].name, rb)
        }

        val ib = view.findViewById<ImageButton>(R.id.ingredient_button)
        ib.setOnClickListener { ingredientListener!!.onIngredientClicked(product) }

        if (product.ingredients != null && product.ingredients.size == 0) ib.visibility = View.GONE


        val countPortion = view.findViewById<TextView>(R.id.count_portion)
        countPortion.text = product.count.toString()
        productCounts.add(position, countPortion)

        val minus = view.findViewById<CircleButton>(R.id.button_minus)
        minus.setOnClickListener(View.OnClickListener {
            if (product.count == 0) return@OnClickListener
            product.count = product.count - 1
            val rb = view.findViewById<RadioButton>(rg.checkedRadioButtonId)
            product.setSelectedPortionByIndex(rg.indexOfChild(rb))
            countPortion.text = product.count.toString()
            this@ProductsListAdapter.listener!!.onItemClicked(product)
        })
        val plus = view.findViewById<CircleButton>(R.id.button_plus)
        plus.setOnClickListener {
            product.count = product.count + 1
            val rb = view.findViewById<RadioButton>(rg.checkedRadioButtonId)
            product.setSelectedPortionByIndex(rg.indexOfChild(rb))
            countPortion.text = product.count.toString()
            this@ProductsListAdapter.listener!!.onItemClicked(product)
        }

    }

    override fun getItemCount(): Int {
        return products.size
    }

    class ViewHolder(var view: View, var context: Context) : RecyclerView.ViewHolder(view)
}
