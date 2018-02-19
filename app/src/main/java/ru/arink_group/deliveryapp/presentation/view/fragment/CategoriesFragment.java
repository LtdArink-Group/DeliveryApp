package ru.arink_group.deliveryapp.presentation.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.arink_group.deliveryapp.R;
import ru.arink_group.deliveryapp.domain.dao.Category;
import ru.arink_group.deliveryapp.domain.dao.Delivery;
import ru.arink_group.deliveryapp.domain.dao.Product;
import ru.arink_group.deliveryapp.domain.interactors.GetCompanyFromShared;
import ru.arink_group.deliveryapp.presentation.adapters.CategoriesListAdapter;
import ru.arink_group.deliveryapp.presentation.adapters.interfaces.OnCategoryClickListener;
import ru.arink_group.deliveryapp.presentation.presenter.interfaces.CategoriesPresenter;
import ru.arink_group.deliveryapp.presentation.presenter.CategoriesPresenterImpl;
import ru.arink_group.deliveryapp.presentation.view.CategoriesView;
import ru.arink_group.deliveryapp.presentation.view.FabView;
import ru.arink_group.deliveryapp.presentation.view.MenuView;
import ru.arink_group.deliveryapp.presentation.view.activity.MenuActivity;
import ru.arink_group.deliveryapp.presentation.view.activity.ProductsActivity;

public class CategoriesFragment extends Fragment implements CategoriesView, OnCategoryClickListener<Category> {

    public static final String CATEGORY = "categoryId";
    public static final String CATEGORY_NAME = "categoryName";

    private Unbinder unbinder;

    private List<Category> categories;
    private CategoriesListAdapter categoriesAdapter;
    private CategoriesPresenter categoriesPresenter;
    private MenuView mv;

    private TextView dashSummaryCost;
    private TextView dashDeliveryCost;

    private RelativeLayout callButton;


    @BindString(R.string.categories)
    String categoriesTitle;

    @BindString(R.string.currency)
    String currency;

    public CategoriesFragment() {
        // Required empty public constructor
        categoriesPresenter = new CategoriesPresenterImpl(this);
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
        mv.loadingFinish();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_categories, container, false);

        unbinder = ButterKnife.bind(this, rootView);

        RecyclerView mRecyclerView = rootView.findViewById(R.id.categories_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        categoriesAdapter = new CategoriesListAdapter();
        mRecyclerView.setAdapter(categoriesAdapter);

        categoriesPresenter.getCategoriesList();
        categoriesAdapter.setListener(this);

        mv = (MenuActivity) getActivity();
        mv.loadingStart();

        dashSummaryCost = rootView.findViewById(R.id.dash_order_cost);
        dashDeliveryCost = rootView.findViewById(R.id.dash_del_cost);
        callButton = rootView.findViewById(R.id.dash_button_call_us);

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phoneNumb = GetCompanyFromShared.INSTANCE.getCompanyOrDefault().getContactInfo().getPhone();
                try {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumb));
                    startActivity(intent);
                } catch (SecurityException e) {
                    Toast.makeText(getActivity(), R.string.error_cant_call, Toast.LENGTH_SHORT).show();
                }
            }
        });

        final FabView fabView = (FabView) getActivity();
        fabView.showOrderFab();

        fabView.getFab().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderFragment orderFragment = new OrderFragment();
                fabView.changeFragment(orderFragment);
            }
        });


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

        AppCompatActivity titleActivity = (AppCompatActivity) getActivity();
        titleActivity.getSupportActionBar().setTitle(R.string.categories);

        categoriesPresenter.updateTotals();

        return rootView;
    }

    @Override
    public void calculateAndUpdateTotals(List<Product> products) {

        double summary = 0.0;

        Delivery delivery = GetCompanyFromShared.INSTANCE.getCompanyOrDefault().getDelivery();

        for (Product product : products) {
            summary += product.getTotalSelectedSum();
        }

        String summaryCostForm = getString(R.string.form_rubles, String.valueOf(summary));
        dashSummaryCost.setText(summaryCostForm);

        if (summary < delivery.getFreeShipping()) {
            String summaryDeliveryForm = getString(R.string.form_rubles, String.valueOf(delivery.getCost()));
            dashDeliveryCost.setText(summaryDeliveryForm);
        } else {
            dashDeliveryCost.setText(R.string.free);
        }

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
        categoriesPresenter.destroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onItemClicked(Category model) {
        categoriesPresenter.onItemSelected(model.getId(), model.getName());
    }
}
