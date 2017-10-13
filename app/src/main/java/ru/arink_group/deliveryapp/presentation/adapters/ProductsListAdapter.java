package ru.arink_group.deliveryapp.presentation.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
    private List<PortionList> portionLists = new ArrayList<>();
    private List<TextView> productCounts = new ArrayList<>();

    public void setProducts(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    public void updateProductsFromBasket(List<Product> selectedProducts) {
        searchProductsForUpdate(selectedProducts);
    }

    private void searchProductsForUpdate(List<Product> sps) {
        for (int i = 0; i < products.size(); i++) {
            for (Product selectedProduct : sps) {
                if (products.get(i).getId() == selectedProduct.getId()) {
                    this.updateProduct(products.get(i), selectedProduct, i);
                }
            }
        }
    }

    private void updateProduct(Product product, Product selectedProduct, int pos) {
        product.setCount(selectedProduct.getCount());
        product.setSelectedPortion(selectedProduct.getSelectedPortion());
        product.setSelectedIngredients(selectedProduct.getSelectedIngredients());
//        productCounts.get(pos).setText(String.valueOf(product.getCount()));
//        portionLists.get(pos).updateElementChecked(product.getSelectedOrDefaultPortionPosition());
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
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Product product = products.get(position);

        TextView nameView = holder.view.findViewById(R.id.product_name);
        nameView.setText(product.getName());

        TextView descriptionView = holder.view.findViewById(R.id.product_description);
        descriptionView.setText(product.getDescription());

        final PortionList pl = holder.view.findViewById(R.id.portion_list);
        PortionList.Adapter adapter = new PortionList.Adapter(holder.context, R.layout.item_portion);
        pl.setState(product, adapter);
        portionLists.add(position, pl);

        TextView priceView = holder.view.findViewById(R.id.product_price);
        priceView.setText(String.valueOf(100500.0) + " \u20BD");

        final TextView countPortion = holder.view.findViewById(R.id.count_portion);
        countPortion.setText(String.valueOf(product.getCount()));
        productCounts.add(position, countPortion);

        CircleButton minus = holder.view.findViewById(R.id.button_minus);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (product.getCount() == 0) return;
                product.setCount(product.getCount() - 1);
                product.setPortions(pl.getChecked());
                countPortion.setText(String.valueOf(product.getCount()));
                ProductsListAdapter.this.listener.onItemClicked(product);
            }
        });
        CircleButton plus = holder.view.findViewById(R.id.button_plus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product.setCount(product.getCount() + 1);
                product.setPortions(pl.getChecked());
                countPortion.setText(String.valueOf(product.getCount()));
                ProductsListAdapter.this.listener.onItemClicked(product);
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
