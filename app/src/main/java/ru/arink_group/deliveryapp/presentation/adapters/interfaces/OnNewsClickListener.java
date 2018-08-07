package ru.arink_group.deliveryapp.presentation.adapters.interfaces;
import ru.arink_group.deliveryapp.domain.dao.News;

public interface OnNewsClickListener<T> {
    void onNewsClick(T news);
}
