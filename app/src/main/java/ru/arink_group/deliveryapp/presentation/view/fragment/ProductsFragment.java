package ru.arink_group.deliveryapp.presentation.view.fragment;


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
import ru.arink_group.deliveryapp.presentation.adapters.OnItemClickListener;
import ru.arink_group.deliveryapp.presentation.adapters.ProductsListAdapter;
import ru.arink_group.deliveryapp.presentation.presenter.ProductsPresenter;
import ru.arink_group.deliveryapp.presentation.presenter.ProductsPresenterImpl;
import ru.arink_group.deliveryapp.presentation.view.ProductsView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsFragment extends Fragment implements ProductsView, OnItemClickListener<Product> {

    private ProductsPresenter productsPresenter;
    private ProductsListAdapter productsListAdapter;

    public ProductsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_products, container, false);
        RecyclerView mRecyclerView = rootView.findViewById(R.id.products_recycler_view);

        productsPresenter = new ProductsPresenterImpl(this);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
//        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        productsListAdapter = new ProductsListAdapter();
        mRecyclerView.setAdapter(productsListAdapter);

        final int categoryId = getArguments().getInt(CarteFragment.CATEGORY);

        productsListAdapter.setListener(this);

        productsPresenter.getProducts(categoryId);

        return rootView;
    }

    @Override
    public void startProduct(int productId) {
        // TODO start product details
    }

    @Override
    public void setProductsList(List<Product> products) {

        Toast.makeText(getActivity(), new Integer(products.size()).toString(), Toast.LENGTH_LONG).show();
        productsListAdapter.setProducts(products);
    }

    @Override
    public void showErrorMessage(String message) {
        Toast toast = Toast.makeText(getActivity(), message, Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void onItemClicked(Product model) {
        productsPresenter.onProductSelect(model.getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        productsPresenter.destroy();
    }
}
