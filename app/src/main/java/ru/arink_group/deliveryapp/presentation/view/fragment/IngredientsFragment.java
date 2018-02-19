package ru.arink_group.deliveryapp.presentation.view.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ru.arink_group.deliveryapp.R;
import ru.arink_group.deliveryapp.domain.dao.Ingredient;
import ru.arink_group.deliveryapp.domain.dao.Product;
import ru.arink_group.deliveryapp.presentation.adapters.IngredientsListAdapter;
import ru.arink_group.deliveryapp.presentation.adapters.interfaces.OnIngredientButtonClickListener;
import ru.arink_group.deliveryapp.presentation.presenter.IngredientsPresenterImpl;
import ru.arink_group.deliveryapp.presentation.presenter.interfaces.IngredientsPresenter;
import ru.arink_group.deliveryapp.presentation.view.FabView;
import ru.arink_group.deliveryapp.presentation.view.IngredientsView;

/**
 * A simple {@link Fragment} subclass.
 */
public class IngredientsFragment extends Fragment implements IngredientsView, OnIngredientButtonClickListener {


    public IngredientsFragment() {
        // Required empty public constructor
    }

    IngredientsPresenter ingredientsPresenter;
    Product product;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_ingredients, container, false);

        product = (Product) getArguments().get(ProductsFragment.PRODUCT);

        ingredientsPresenter = new IngredientsPresenterImpl(this);

        RecyclerView recyclerView = rootView.findViewById(R.id.ingredients_recycler_view);

        IngredientsListAdapter ingredientsListAdapter = new IngredientsListAdapter(product.getIngredients());
        ingredientsListAdapter.setListener(this);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(ingredientsListAdapter);

        final FabView fabView = (FabView) getActivity();
        fabView.showOrderFab();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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

        return rootView;
    }

    @Override
    public void setIngredientsList(Product product) {

    }

    @Override
    public void updateIngredientsList(Product product) {

    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onIngredientClicked(Ingredient ingredient) {
        ingredientsPresenter.addIngredientToBasket(product.getId(), ingredient);
    }
}
