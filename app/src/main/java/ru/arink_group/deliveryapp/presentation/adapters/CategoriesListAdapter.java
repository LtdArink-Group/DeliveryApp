package ru.arink_group.deliveryapp.presentation.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.arink_group.deliveryapp.R;
import ru.arink_group.deliveryapp.domain.dao.Category;
import ru.arink_group.deliveryapp.presentation.adapters.interfaces.OnCategoryClickListener;
import ru.arink_group.deliveryapp.presentation.shared.IconTransformer;

/**
 * Created by kirillvs on 02.10.17.
 */

public class CategoriesListAdapter extends RecyclerView.Adapter<CategoriesListAdapter.ViewHolder> {

    private List<Category> categories = new ArrayList<>();
    private OnCategoryClickListener<Category> listener;

    public void setCategories(List<Category> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }

    public void setListener(OnCategoryClickListener<Category> listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Category category = categories.get(position);
        TextView tv = holder.viewView.findViewById(R.id.category_name);
        tv.setText(category.getName());

        if (category.getIcon() != null) {
            ImageView iv = holder.viewView.findViewById(R.id.category_icon);
            int drawableIcon = IconTransformer.iconNameToId(category.getIcon());
            iv.setImageResource(drawableIcon);
        }

        holder.viewView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CategoriesListAdapter.this.listener.onItemClicked(category);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public View viewView;

        public ViewHolder(View itemView) {
            super(itemView);
            viewView = itemView;
        }
    }
}
