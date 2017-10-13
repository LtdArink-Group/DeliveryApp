package ru.arink_group.deliveryapp.presentation.view.fragment;

import android.content.Context;
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
import ru.arink_group.deliveryapp.domain.Category;
import ru.arink_group.deliveryapp.presentation.adapters.CategoriesListAdapter;
import ru.arink_group.deliveryapp.presentation.adapters.OnCategoryClickListener;
import ru.arink_group.deliveryapp.presentation.adapters.OnItemClickListener;
import ru.arink_group.deliveryapp.presentation.presenter.CartePresenter;
import ru.arink_group.deliveryapp.presentation.presenter.CartePresenterImpl;
import ru.arink_group.deliveryapp.presentation.view.CarteView;
import ru.arink_group.deliveryapp.presentation.view.MenuView;
import ru.arink_group.deliveryapp.presentation.view.activity.MenuActivity;
import ru.arink_group.deliveryapp.presentation.view.activity.ProductsActivity;

public class CarteFragment extends Fragment implements CarteView, OnCategoryClickListener<Category> {

    public static final String CATEGORY = "categoryId";
    public static final String CATEGORY_NAME = "categoryName";

    private List<Category> categories;
    private CategoriesListAdapter categoriesAdapter;
    private CartePresenter cartePresenter;

    public CarteFragment() {
        // Required empty public constructor
        cartePresenter = new CartePresenterImpl(this);
    }

    @Override
    public void startCategory(int sectionId, String name) {
        Intent intent = new Intent(this.getActivity(), ProductsActivity.class);
        intent.putExtra(CATEGORY, sectionId);
        intent.putExtra(CATEGORY_NAME, name);
        getActivity().startActivity(intent);
    }

    @Override
    public void setCategoriesList(List<Category> categories) {
        this.categories = categories;
        categoriesAdapter.setCategories(categories);
    }

    @Override
    public void showErrorMessage(String message) {
        Toast toast = Toast.makeText(getActivity(), message, Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void loadCompleted() {
        MenuView mv = (MenuActivity) getActivity();
        mv.contentLoaded();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_carte, container, false);
        RecyclerView mRecyclerView = rootView.findViewById(R.id.categories_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        categoriesAdapter = new CategoriesListAdapter();
        mRecyclerView.setAdapter(categoriesAdapter);

        cartePresenter.getCategoriesList();
        categoriesAdapter.setListener(this);

        return rootView;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cartePresenter.destroy();
    }

    @Override
    public void onItemClicked(Category model) {
        cartePresenter.onItemSelected(model.getId(), model.getName());
    }
}
