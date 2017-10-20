package ru.arink_group.deliveryapp.presentation.model;

import ru.arink_group.deliveryapp.R;

import static ru.arink_group.deliveryapp.R.drawable.pizza;

/**
 * Created by kirillvs on 04.10.17.
 */

public class IconTransformer {
    public static int iconNameToId(String iconName) {
        switch (iconName.toLowerCase()) {
            case "icon_burger":
                return R.drawable.icon_burger;
            case "icon_pizza":
                return R.drawable.icon_pizza;
            case "icon_wing":
                return R.drawable.icon_wing;
            case "icon_leg":
                return R.drawable.icon_leg;
            case "icon_chips":
                return R.drawable.icon_chips;
            case "icon_salat":
                return R.drawable.icon_salat;
            case "icon_sauce":
                return R.drawable.icon_sauce;
            case "icon_soup":
                return R.drawable.icon_soup;
            case "icon_drinks":
                return R.drawable.icon_drinks;
            case "icon_set":
                return R.drawable.icon_set;
            default:
                return R.drawable.ic_menu_gallery;
        }
    }
    
}
