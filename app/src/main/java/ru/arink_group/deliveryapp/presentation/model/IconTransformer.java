package ru.arink_group.deliveryapp.presentation.model;

import ru.arink_group.deliveryapp.R;

import static ru.arink_group.deliveryapp.R.drawable.pizza;

/**
 * Created by kirillvs on 04.10.17.
 */

public class IconTransformer {
    public static int iconNameToId(String iconName) {
        switch (iconName.toLowerCase()) {
            case "pizza":
                return R.drawable.pizza;
            case "drink":
                return R.drawable.food_fork_drink;
            case "hamburger":
                return R.drawable.hamburger;
            default:
                return R.drawable.ic_menu_gallery;
        }
    }
}
