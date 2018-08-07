package ru.arink_group.deliveryapp.presentation.view;

import java.util.List;

import ru.arink_group.deliveryapp.domain.dao.News;


public interface NewsView extends ProgressView {
    void setNewsList(List<News> news);
    void updateNewsList(News news);
    void showErrorMessage(String message);
    void showPlaceholder();
}
