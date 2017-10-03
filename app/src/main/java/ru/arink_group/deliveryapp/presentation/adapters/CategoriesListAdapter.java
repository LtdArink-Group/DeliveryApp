package ru.arink_group.deliveryapp.presentation.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.arink_group.deliveryapp.R;
import ru.arink_group.deliveryapp.domain.Category;

/**
 * Created by kirillvs on 02.10.17.
 */

public class CategoriesListAdapter extends RecyclerView.Adapter<CategoriesListAdapter.ViewHolder> {

    private List<Category> categories = new ArrayList<>();

    public void setCategories(List<Category> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView tv = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.category_view, parent, false);
        return new ViewHolder(tv);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(categories.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public ViewHolder(TextView itemView) {
            super(itemView);
            textView = itemView;
        }
    }
}
