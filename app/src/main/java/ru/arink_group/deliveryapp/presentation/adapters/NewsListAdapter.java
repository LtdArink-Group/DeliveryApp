package ru.arink_group.deliveryapp.presentation.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import ru.arink_group.deliveryapp.R;
import ru.arink_group.deliveryapp.domain.dao.News;
import ru.arink_group.deliveryapp.presentation.adapters.interfaces.OnNewsClickListener;
import ru.arink_group.deliveryapp.presentation.shared.IconTransformer;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder> {

    private List<News> newsList = new ArrayList<>();
    private OnNewsClickListener<News> listener;

    @BindString(R.string.news)
    String newsTitle;

    public void setNews(List<News> newsList) {
        this.newsList = newsList;
        notifyDataSetChanged();
    }


    public void setListener(OnNewsClickListener<News> listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new ViewHolder(v, parent.getContext());
    }

    @Override
    public void onViewRecycled(NewsListAdapter.ViewHolder holder) {
        super.onViewRecycled(holder);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final News news = newsList.get(position);
        TextView tv = holder.viewView.findViewById(R.id.news_name);
        tv.setText(news.getName());


        holder.viewView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewsListAdapter.this.listener.onNewsClick(news);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public View viewView;
        public Context context;

        public ViewHolder(View itemView, Context context) {
            super(itemView);
            viewView = itemView;
            this.context = context;
        }
    }
}

