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

    public void setAdapter(PortionList.Adapter adapter) {
        items = new ArrayList<>();
        elementsIds = adapter.getElementsIds();
        boolean selectedFlag = false;
        for(int i = 0; i < adapter.getElementSize(); i++) {
            View item = adapter.getElement(i);
            boolean checked = adapter.isCheckedElement(i);
            this.addView(item);
            item.setOnClickListener(this);
            items.add(item);
            if(checked && !selectedFlag) {
                checkView(item);
                selectedFlag = true;
            }
        }
        if (!selectedFlag) this.checkView(items.get(0));
    }

    public String getChecked() {
        CheckedTextView title = this.checkedItem.findViewById(R.id.item1);
        return String.valueOf(title.getText());
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
        Portion[] items;
        int layoutId;

        public Adapter(Context context, Product product, int layoutId) {
            this.context = context;
            this.items = product.getPortions();
            this.layoutId = layoutId;
        }

        public int getElementSize() {
            return items.length;
        }

        public View getElement(int position) {
            View layout = LayoutInflater.from(context).inflate(layoutId, null);

            CheckedTextView name = layout.findViewById(R.id.item1);
            CheckedTextView price = layout.findViewById(R.id.item2);

            name.setText(items[position].getName());
            price.setText(String.valueOf(items[position].getPrice()));

            return layout;
        }

        public boolean isCheckedElement(int position) {
            return items[position].isChecked();
        }

        public ArrayList<Integer> getElementsIds() {
            ArrayList<Integer> elementIds = new ArrayList<>();
            elementIds.add(R.id.item1);
            elementIds.add(R.id.item2);
            return elementIds;
        }

    }

}
