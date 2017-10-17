package ru.arink_group.deliveryapp.presentation.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import ru.arink_group.deliveryapp.R;
import ru.arink_group.deliveryapp.domain.Product;
import ru.arink_group.deliveryapp.presentation.adapters.OnIngredientClickListener;
import ru.arink_group.deliveryapp.presentation.adapters.OnItemClickListener;
import ru.arink_group.deliveryapp.presentation.adapters.ProductsListAdapter;
import ru.arink_group.deliveryapp.presentation.presenter.interfaces.ProductsPresenter;
import ru.arink_group.deliveryapp.presentation.presenter.ProductsPresenterImpl;
import ru.arink_group.deliveryapp.presentation.view.ProductsView;
import ru.arink_group.deliveryapp.presentation.view.activity.IngredientsActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsFragment extends Fragment implements ProductsView, OnItemClickListener<Product>, OnIngredientClickListener {

    private ProductsPresenter productsPresenter;
    private ProductsListAdapter productsListAdapter;

    public static final String PRODUCT_ID = "product_id";
    public static final String PRODUCT = "product";

    private int categoryId;
    private String categoryName;

    public ProductsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_products, container, false);

        RecyclerView mRecyclerView = rootView.findViewById(R.id.products_recycler_view);

        productsPresenter = new ProductsPresenterImpl(this);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setHasFixedSize(true);

        productsListAdapter = new ProductsListAdapter();
        mRecyclerView.setAdapter(productsListAdapter);


        categoryId = getArguments().getInt(CarteFragment.CATEGORY);
        categoryName = getArguments().getString(CarteFragment.CATEGORY_NAME);

        productsListAdapter.setListener(this);
        productsListAdapter.setIngredientListener(this);

        productsPresenter.getProducts(categoryId);

//        Toast.makeText(getActivity(), String.valueOf(categoryId), Toast.LENGTH_LONG).show();
        return rootView;
    }


    @Override
    public void setProductsList(List<Product> products) {
        productsListAdapter.setProducts(products);
    }

    @Override
    public void updateProductList(List<Product> selectedProducts) {
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

    @Override
    public void onIngredientClicked(Product product) {
        if (product.getCount() < 1) {
            showErrorMessage("Для начала добавьте товар в корзину!");
            return;
        }
        Intent intent = new Intent(getActivity(), IngredientsActivity.class);
        intent.putExtra(PRODUCT, product);
        intent.putExtra(CarteFragment.CATEGORY, categoryId);
        intent.putExtra(CarteFragment.CATEGORY_NAME, categoryName);
        getActivity().startActivity(intent);
    }
}
