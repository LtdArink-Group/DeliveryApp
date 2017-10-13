package ru.arink_group.deliveryapp.presentation.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import at.markushi.ui.CircleButton;
import ru.arink_group.deliveryapp.R;
import ru.arink_group.deliveryapp.domain.Ingredient;
import ru.arink_group.deliveryapp.domain.Portion;
import ru.arink_group.deliveryapp.domain.Product;
import ru.arink_group.deliveryapp.presentation.custom_elements.PortionList;

/**
 * Created by kirillvs on 03.10.17.
 */

public class ProductsListAdapter extends RecyclerView.Adapter<ProductsListAdapter.ViewHolder> {

    private List<Product> products = new ArrayList<>();
    private OnItemClickListener<Product> listener;

    public void setProducts(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    public void updateProductsFromBasket(List<Product> selectedProducts) {
        searchProductsForUpdate(selectedProducts);
//        this.notifyDataSetChanged(); // TODO bug with doubling^ maybe need to call another method
    }

    private void searchProductsForUpdate(List<Product> sps) {
        // TODO Rework
        for (Product product : this.products) {
            for (Product selectedProduct : sps) {
                if (product.getId() == selectedProduct.getId()) {
                    this.updateProduct(product, selectedProduct);
                }
            }
        }
    }

    private void updateProduct(Product product, Product selectedProduct) {
        // TODO Rework
//        product.setSelected(true);
//
//        for(Portion portion : product.getPortions()) {
//            if (portion.getId() == selectedProduct.getSelectedPortion().getId()) {
//                portion.setSelected(true);
//                portion.setCount(selectedProduct.getSelectedPortion().getCount());
//                break;
//            }
//        }
//
//        for (Ingredient ingredient : product.getIngredients()) {
//            for (Ingredient selectedIngredient : selectedProduct.getSelectedIngredients()) {
//                if (selectedIngredient.getId() == ingredient.getId()) {
//                    ingredient.setSelected(true);
//                    ingredient.setCount(selectedIngredient.getCount());
//                }
//            }
//        }

    }


    public void setListener(OnItemClickListener<Product> listener) {
        this.listener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(v, parent.getContext());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Product product = products.get(position);

        final View rootView = holder.view;

        TextView nameView = holder.view.findViewById(R.id.product_name);
        nameView.setText(product.getName());

        TextView descriptionView = holder.view.findViewById(R.id.product_description);
        descriptionView.setText(product.getDescription());

        PortionList pl = holder.view.findViewById(R.id.portion_list);
        PortionList.Adapter adapter = new PortionList.Adapter(holder.context, product, R.layout.item_portion);
        pl.setAdapter(adapter);


        TextView priceView = holder.view.findViewById(R.id.product_price);
        priceView.setText(String.valueOf(100500.0) + " \u20BD");

        final TextView countPortion = holder.view.findViewById(R.id.count_portion);
        countPortion.setText(String.valueOf(product.getCount()));

        CircleButton minus = holder.view.findViewById(R.id.button_minus);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductsListAdapter.this.listener.onItemClicked(false, product, rootView);
            }
        });
        CircleButton plus = holder.view.findViewById(R.id.button_plus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductsListAdapter.this.listener.onItemClicked(true, product, rootView);
            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public View view;
        public Context context;

        public ViewHolder(View itemView, Context context) {
            super(itemView);
            view = itemView;
            this.context = context;
        }
    }
}
