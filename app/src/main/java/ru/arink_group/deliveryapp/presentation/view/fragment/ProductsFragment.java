package ru.arink_group.deliveryapp.presentation.view.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import java.util.List;

import ru.arink_group.deliveryapp.R;
import ru.arink_group.deliveryapp.domain.Product;
import ru.arink_group.deliveryapp.presentation.adapters.OnItemClickListener;
import ru.arink_group.deliveryapp.presentation.adapters.ProductsListAdapter;
import ru.arink_group.deliveryapp.presentation.custom_elements.PortionList;
import ru.arink_group.deliveryapp.presentation.presenter.ProductsPresenter;
import ru.arink_group.deliveryapp.presentation.presenter.ProductsPresenterImpl;
import ru.arink_group.deliveryapp.presentation.view.ProductsView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsFragment extends Fragment implements ProductsView, OnItemClickListener<Product> {

    private ProductsPresenter productsPresenter;
    private ProductsListAdapter productsListAdapter;

    public interface RecyclerViewReadyCallback {
        void onLayoutReady();
    }

    private RecyclerViewReadyCallback recyclerViewReadyCallback;

    public ProductsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_products, container, false);

        RecyclerView mRecyclerView = rootView.findViewById(R.id.products_recycler_view);

        productsPresenter = new ProductsPresenterImpl(this);

        recyclerViewReadyCallback = new RecyclerViewReadyCallback() {
            @Override
            public void onLayoutReady() {
                productsPresenter.updateProductsFromBasket();
            }
        };


        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setHasFixedSize(true);

        productsListAdapter = new ProductsListAdapter();
        mRecyclerView.setAdapter(productsListAdapter);

        mRecyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (recyclerViewReadyCallback != null) {
                    recyclerViewReadyCallback.onLayoutReady();
                }

                recyclerViewReadyCallback = null;
            }
        });

        final int categoryId = getArguments().getInt(CarteFragment.CATEGORY);

        productsListAdapter.setListener(this);

        productsPresenter.getProducts(categoryId);

        return rootView;
    }


    @Override
    public void setProductsList(List<Product> products) {
        productsListAdapter.setProducts(products);
    }

    @Override
    public void updateProductList(List<Product> selectedProducts) {
        Toast.makeText(getActivity(), String.valueOf(selectedProducts.size()), Toast.LENGTH_LONG).show();
        productsListAdapter.updateProductsFromBasket(selectedProducts);
    }

    @Override
    public void showErrorMessage(String message) {
        Toast toast = Toast.makeText(getActivity(), message, Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void onItemClicked(Product model) {
        productsPresenter.addItemToBasket(model);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        productsPresenter.destroy();
    }
}
