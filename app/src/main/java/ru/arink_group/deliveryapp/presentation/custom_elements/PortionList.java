package ru.arink_group.deliveryapp.presentation.custom_elements;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import ru.arink_group.deliveryapp.R;
import ru.arink_group.deliveryapp.domain.Portion;
import ru.arink_group.deliveryapp.domain.Product;

/**
 * Created by kirillvs on 05.10.17.
 */

public class PortionList extends LinearLayout implements View.OnClickListener {

    public PortionList(Context context) {
        super(context);
    }

    public PortionList(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PortionList(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    ArrayList<Integer> elementsIds;
    ArrayList<View> items;
    View checkedItem;
    Portion[] portions;
    Product product;
    public boolean hasAdapter;

    private void addElementsToView(Adapter adapter) {
        items = new ArrayList<>();
        elementsIds = adapter.getElementsIds();
        for(int i = 0; i < portions.length; i++) {
            View item = adapter.getView();
            CheckedTextView name = item.findViewById(R.id.item1);
            CheckedTextView price = item.findViewById(R.id.item2);

            name.setText(portions[i].getName());
            price.setText(String.valueOf(portions[i].getPrice()));

            this.addView(item);

            final int pos = i;
            item.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    PortionList.this.checkPortion(pos);
                    PortionList.this.onClick(v);
                }
            });
            items.add(item);
        }
        checkView(items.get(product.getSelectedOrDefaultPortionPosition()));
    }

    private void checkPortion(int i) {
        for(Portion portion : portions) {
            portion.setChecked(false);
        }
        portions[i].setChecked(true);
    }

    public void updateElementChecked(int positionChecked) {
        checkPortion(positionChecked);
        uncheckAll();
        checkView(items.get(positionChecked));
    }

    public void setState(Product product, Adapter adapter) {
        this.product = product;
        this.portions = product.getPortions();
        if(hasAdapter) return;
        hasAdapter = true;
        addElementsToView(adapter);
    }

    public Portion[] getChecked() {
        return portions;
    }

    @Override
    public void onClick(View v) {
        uncheckAll();
        checkView(v);
    }

    private void checkView(View v) {
        checkedItem = v;
        for(int elementId : elementsIds) {
            CheckedTextView ctv = v.findViewById(elementId);
            ctv.setChecked(true);
        }
        Drawable color = ContextCompat.getDrawable(this.getContext(), R.drawable.rounder_border_checked);
        v.setBackground(color);
    }

    private void uncheckView(View v) {
        for(int elementId : elementsIds) {
            CheckedTextView ctv = v.findViewById(elementId);
            ctv.setChecked(false);
        }
        Drawable color = ContextCompat.getDrawable(this.getContext(), R.drawable.rounder_border);
        v.setBackground(color);
    }

    private void uncheckAll() {
        for(View item : items) {
            uncheckView(item);
        }
    }


    public static class Adapter {

        Context context;
        int layoutId;

        public Adapter(Context context, int layoutId) {
            this.context = context;
            this.layoutId = layoutId;
        }

        public View getView() {
            View layout = LayoutInflater.from(context).inflate(layoutId, null);

            return layout;
        }

        public ArrayList<Integer> getElementsIds() {
            ArrayList<Integer> elementIds = new ArrayList<>();
            elementIds.add(R.id.item1);
            elementIds.add(R.id.item2);
            return elementIds;
        }

    }

}
