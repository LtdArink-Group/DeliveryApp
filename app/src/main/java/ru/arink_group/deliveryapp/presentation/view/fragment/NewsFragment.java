package ru.arink_group.deliveryapp.presentation.view.fragment;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import butterknife.Unbinder;
import ru.arink_group.deliveryapp.R;
import ru.arink_group.deliveryapp.domain.dao.News;
import ru.arink_group.deliveryapp.presentation.adapters.NewsListAdapter;
import ru.arink_group.deliveryapp.presentation.adapters.ProductsListAdapter;
import ru.arink_group.deliveryapp.presentation.adapters.interfaces.OnNewsClickListener;
import ru.arink_group.deliveryapp.presentation.shared.DetailDialog;
import ru.arink_group.deliveryapp.presentation.view.FabView;
import ru.arink_group.deliveryapp.presentation.view.NewsView;
import ru.arink_group.deliveryapp.presentation.view.PlaceholderView;
import ru.arink_group.deliveryapp.presentation.view.ProgressView;

public class NewsFragment extends Fragment implements NewsView, OnNewsClickListener<News> {


    private NewsListAdapter newsListAdapter;
    private List<News> newsList;
    private ProgressView progressView;

    public NewsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);

        RecyclerView mRecyclerView = rootView.findViewById(R.id.news_recycler_view);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setHasFixedSize(true);

        newsListAdapter = new NewsListAdapter();
        mRecyclerView.setAdapter(newsListAdapter);

        newsListAdapter.setListener(this);

        final FabView fabView = (FabView) getActivity();
        fabView.showOrderFab();

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    fabView.hideOrderFab();
                } else if (dy < 0) {
                    fabView.showOrderFab();
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        progressView = (ProgressView) getActivity();
        AppCompatActivity titleActivity = (AppCompatActivity) getActivity();
        titleActivity.getSupportActionBar().setTitle(R.string.news);
        loadingStart();
        return rootView;
    }
    @Override
    public void updateNewsList(News news){

    }
    @Override
    public void onNewsClick(News news){
        DetailDialog dialog = new DetailDialog(getActivity(), R.style.DetailDialog, news.getName(), news.getDescription(),news.getImgUrl());
        dialog.show();
    }
    @Override
    public void loadingStart() {
        progressView.loadingStart();
    }

    @Override
    public void loadingFinish() {
        progressView.loadingFinish();
    }

    @Override
    public void setNewsList(List<News> news) {
        newsListAdapter.setNews(news);
    }
    @Override
    public void showErrorMessage(String message) {
        Toast toast = Toast.makeText(getActivity(), message, Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void showPlaceholder() {
        PlaceholderView activity = (PlaceholderView) getActivity();
        activity.showPlaceHolder();
    }
}
