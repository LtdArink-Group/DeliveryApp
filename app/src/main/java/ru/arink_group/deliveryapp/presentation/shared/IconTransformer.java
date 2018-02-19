package ru.arink_group.deliveryapp.presentation.shared;

import ru.arink_group.deliveryapp.R;

/**
 * Created by kirillvs on 04.10.17.
 */

public class IconTransformer {
    public static int iconNameToId(String iconName) {
        switch (iconName.toLowerCase()) {
            case "icon_burger":
                return R.drawable.icon_burger_3x;
            case "icon_pizza":
                return R.drawable.icon_pizza_3x;
            case "icon_wing":
                return R.drawable.icon_wing_3x;
            case "icon_leg":
                return R.drawable.icon_leg_3x;
            case "icon_chips":
                return R.drawable.icon_chips_3x;
            case "icon_salat":
                return R.drawable.icon_salat_3x;
            case "icon_sauce":
                return R.drawable.icon_sauce_3x;
            case "icon_soup":
                return R.drawable.icon_soup_3x;
            case "icon_drinks":
                return R.drawable.icon_drinks_3x;
            case "icon_set":
                return R.drawable.icon_set_3x;
            case "icon_kebab":
                return R.drawable.icon_kebab_3x;
            default:
                return R.drawable.ic_menu_gallery;
        }
    }
    
}
