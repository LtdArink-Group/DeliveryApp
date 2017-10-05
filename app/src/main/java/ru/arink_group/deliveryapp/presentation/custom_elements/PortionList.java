package ru.arink_group.deliveryapp.presentation.custom_elements;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import ru.arink_group.deliveryapp.R;
import ru.arink_group.deliveryapp.domain.Portion;

/**
 * Created by kirillvs on 05.10.17.
 */

public class PortionList extends LinearLayout {

    public PortionList(Context context) {
        super(context);
    }

    public PortionList(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PortionList(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setAdapter(PortionList.Adapter adapter) {
        for(int i = 0; i < adapter.getElementSize(); i++) {
            this.addView(adapter.getElement(i));
        }
    }

    public static class Adapter {

        Context context;
        Portion[] items;
        int layoutId;

        public Adapter(Context context, Portion[] items, int layoutId) {
            this.context = context;
            this.items = items;
            this.layoutId = layoutId;
        }

        public int getElementSize() {
            return items.length;
        }

        public View getElement(int position) {
            View layout = LayoutInflater.from(context).inflate(layoutId, null);

            TextView name = layout.findViewById(R.id.item1);
            TextView price = layout.findViewById(R.id.item2);

            name.setText(items[position].getName());
            price.setText(items[position].getPrice());

            return layout;
        }

    }

}
