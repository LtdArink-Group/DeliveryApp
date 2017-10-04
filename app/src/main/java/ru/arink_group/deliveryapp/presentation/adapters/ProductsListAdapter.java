package ru.arink_group.deliveryapp.presentation.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.arink_group.deliveryapp.R;
import ru.arink_group.deliveryapp.domain.Product;

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

        TextView nameView = holder.view.findViewById(R.id.product_name);
        nameView.setText(product.getName());

        TextView descriptionView = holder.view.findViewById(R.id.product_description);
        descriptionView.setText(product.getDescription());

        Spinner sizeSpinner = (Spinner) holder.view.findViewById(R.id.product_size_spinner);
        ArrayAdapter<String> sizes = new ArrayAdapter<>(holder.context, R.layout.support_simple_spinner_dropdown_item, product.getSize());
        sizeSpinner.setAdapter(sizes);

        TextView priceView = holder.view.findViewById(R.id.product_price);
        priceView.setText(product.getPrice() + " \u20BD");

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
