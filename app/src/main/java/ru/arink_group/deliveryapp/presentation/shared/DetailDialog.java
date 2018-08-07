package ru.arink_group.deliveryapp.presentation.shared;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ru.arink_group.deliveryapp.R;

/**
 * Created by kirillvs on 24.11.17.
 */

public class DetailDialog extends Dialog implements
        android.view.View.OnClickListener {

    private Button ok;
    private ImageView img;
    private TextView tv;
    private TextView tvTitle;

    private String title;
    private String description;
    private String imgUrl;



    public DetailDialog(Activity a, int themeRes, String title, String description, String url) {
        super(a, themeRes);
        this.title = title;
        this.description = description;
        this.imgUrl = url;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_detail);
        ok = findViewById(R.id.ok_button);
        img = findViewById(R.id.img_product);
        tv = findViewById(R.id.textView);
        tvTitle = findViewById(R.id.textTitle);
        ok.setOnClickListener(this);
        Picasso.with(getContext()).load(imgUrl).into(img);
        tv.setText(description);
        tvTitle.setText(title);
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}
