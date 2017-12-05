package ru.arink_group.deliveryapp.presentation.adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import at.markushi.ui.CircleButton;
import ru.arink_group.deliveryapp.R;
import ru.arink_group.deliveryapp.domain.dao.Product;
import ru.arink_group.deliveryapp.presentation.adapters.interfaces.OnDescriptionClickListener;
import ru.arink_group.deliveryapp.presentation.adapters.interfaces.OnIngredientClickListener;
import ru.arink_group.deliveryapp.presentation.adapters.interfaces.OnItemClickListener;

/**
 * Created by kirillvs on 03.10.17.
 */

public class ProductsListAdapter extends RecyclerView.Adapter<ProductsListAdapter.ViewHolder> {

    private List<Product> products = new ArrayList<>();
    private OnItemClickListener<Product> listener;
    private OnDescriptionClickListener descriptionListener;
    private OnIngredientClickListener ingredientListener;

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
        if (sps.size() > 0) notifyDataSetChanged();
    }

    private void updateProduct(Product product, Product selectedProduct, int pos) {
        product.setCount(selectedProduct.getCount());
        product.setSelectedPortion(selectedProduct.getSelectedPortion());
        product.setSelectedIngredients(selectedProduct.getSelectedIngredients());
    }

    public void setListener(OnItemClickListener<Product> listener) {
        this.listener = listener;
    }
    public void setIngredientListener(OnIngredientClickListener listener) {
        this.ingredientListener = listener;
    }

    public void setOnDescriptionListener(OnDescriptionClickListener descriptionListener) {
        this.descriptionListener = descriptionListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(v, parent.getContext());
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);
        final RadioGroup rg = holder.view.findViewById(R.id.portion_list_group);
        clearRadioGroup(rg);
    }

    private void clearRadioGroup(RadioGroup rg) {
        int count = rg.getChildCount();
        if(count>0) {
            for (int i=count-1;i>=0;i--) {
                View o = rg.getChildAt(i);
                if (o instanceof RadioButton) {
                    rg.removeViewAt(i);
                }
            }
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Product product = products.get(position);

        TextView nameView = holder.view.findViewById(R.id.product_name);
        nameView.setText(product.getName());

        TextView descriptionView = holder.view.findViewById(R.id.product_description);
        descriptionView.setText(product.getBrief());

        final TextView priceView = holder.view.findViewById(R.id.product_price);
        priceView.setText(holder.context.getResources().getString(R.string.currency) + " " + String.valueOf(product.getSelectedPortion().getPrice()));

        int[][] states = new int[][] {
                new int[] {android.R.attr.state_checked}, // checked
                new int[] {-android.R.attr.state_checked}, // unchecked
        };

        int[] colors = new int[] {
                ContextCompat.getColor(holder.context, R.color.colorCheckedText),
                ContextCompat.getColor(holder.context, R.color.colorPrimaryText)
        };

        final RadioGroup rg = holder.view.findViewById(R.id.portion_list_group);
        int selectedPortionIndex = product.getSelectedPortionIndex();
        for(int i = 0; i < product.getPortions().length; i++) {
            RadioButton rb = new RadioButton(holder.context);
            rb.setWidth(holder.context.getResources().getDimensionPixelSize(R.dimen.ingredient_radiobutton_witdh));
            rb.setButtonDrawable(android.R.color.transparent);
            rb.setText(product.getPortions()[i].getName());
            rb.setMaxLines(1);
            rb.setEllipsize(TextUtils.TruncateAt.END);
            rb.setGravity(Gravity.CENTER);
            if(product.getPortions().length == 1){
                rb.setBackground(ContextCompat.getDrawable(holder.context, R.drawable.bg_item_ingredient_single));
            } else if(i == 0) {
                rb.setBackground(ContextCompat.getDrawable(holder.context, R.drawable.bg_item_ingredient_left));
            } else if( i == product.getPortions().length - 1) {
                rb.setBackground(ContextCompat.getDrawable(holder.context, R.drawable.bg_item_ingredient_right));
            } else {
                rb.setBackground(ContextCompat.getDrawable(holder.context, R.drawable.bg_item_ingredient));
            }
            ColorStateList csl = new ColorStateList(states, colors);
            rb.setTextColor(csl);
            rg.addView(rb);
            rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        product.setSelectedPortionByName(String.valueOf(buttonView.getText()));
                        priceView.setText(holder.context.getResources().getString(R.string.currency) + " " + String.valueOf(product.getSelectedPortion().getPrice()));
                    }
                }
            });
            if(i == selectedPortionIndex) {
                rg.check(rb.getId());
            }
        }

        ImageButton ib = holder.view.findViewById(R.id.ingredient_button);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingredientListener.onIngredientClicked(product);
            }
        });

        if (product.getIngredients() != null && product.getIngredients().length == 0) ib.setVisibility(View.GONE);

        ImageView productImage = holder.view.findViewById(R.id.product_Image);
        Picasso.with(holder.context).load(product.getImageUrl()).placeholder(R.drawable.blur_image).into(productImage);
        productImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                descriptionListener.onDescriptionClick(product.getName(), product.getDescription(), product.getImageUrl());
            }
        });

        final TextView countPortion = holder.view.findViewById(R.id.count_portion);
        countPortion.setText(String.valueOf(product.getCount()));

        CircleButton minus = holder.view.findViewById(R.id.button_minus);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (product.getCount() == 0) return;
                product.setCount(product.getCount() - 1);
                RadioButton rb = holder.view.findViewById(rg.getCheckedRadioButtonId());
                product.setSelectedPortionByIndex(rg.indexOfChild(rb));
                countPortion.setText(String.valueOf(product.getCount()));
                ProductsListAdapter.this.listener.onItemClicked(product);
            }
        });
        CircleButton plus = holder.view.findViewById(R.id.button_plus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product.setCount(product.getCount() + 1);
                RadioButton rb = holder.view.findViewById(rg.getCheckedRadioButtonId());
                product.setSelectedPortionByIndex(rg.indexOfChild(rb));
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
