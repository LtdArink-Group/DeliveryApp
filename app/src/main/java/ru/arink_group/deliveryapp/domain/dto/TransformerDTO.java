package ru.arink_group.deliveryapp.domain.dto;

import java.util.ArrayList;
import java.util.List;

import ru.arink_group.deliveryapp.App;
import ru.arink_group.deliveryapp.domain.Account;
import ru.arink_group.deliveryapp.domain.Address;
import ru.arink_group.deliveryapp.domain.Category;
import ru.arink_group.deliveryapp.domain.Ingredient;
import ru.arink_group.deliveryapp.domain.Portion;
import ru.arink_group.deliveryapp.domain.Product;

/**
 * Created by kirillvs on 20.10.17.
 */

public class TransformerDTO {
    public static Category transformCategory(CategoryDTO categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setIcon(categoryDto.getIconType());
        category.setDescription(categoryDto.getDescription());

        return category;
    }

    public static List<Category> transformListCategories(List<CategoryDTO> categoriesDTO) {
        List<Category> categories = new ArrayList<>();

        for(CategoryDTO categoryDTO : categoriesDTO) {
            categories.add(transformCategory(categoryDTO));
        }

        return categories;
    }

    public static Product transformProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getTitle());
        product.setDescription(productDTO.getDescription());
        product.setCount(0);
        product.setImageUrl(productDTO.getPhoto());
        product.setPortions(transformPortions(productDTO.getMainOptions()));
        product.setIngredients(transformIngredients(productDTO.getAdditionalInfo()));
        return product;
    }

    public static List<Product> transformProducts(List<ProductDTO> productDTOs) {
        List<Product> products = new ArrayList<>();
        for (ProductDTO productDTO : productDTOs) {
            products.add(transformProduct(productDTO));
        }
        return products;
    }

    public static Portion transformPortion(MainOptionDTO mainOption) {
        Portion portion = new Portion();
        portion.setName(mainOption.getName());
        portion.setPrice(mainOption.getCost());
        return portion;
    }

    public static Portion[] transformPortions(List<MainOptionDTO> mainOptions) {
        Portion[] portions = new Portion[mainOptions.size()];
        for (int i = 0; i < portions.length; i++) {
            portions[i] = transformPortion(mainOptions.get(i));
        }
        return portions;
    }

    public static Ingredient transformIngredient(AdditionalInfoDTO additionalInfo) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(additionalInfo.getName());
        ingredient.setCount(0);
        ingredient.setPrice(additionalInfo.getCost());
        return ingredient;
    }

    public static Ingredient[] transformIngredients(List<AdditionalInfoDTO> additionalInfoDTOs) {
        Ingredient[] ingredients = new Ingredient[additionalInfoDTOs.size()];
        for (int i = 0; i < ingredients.length; i++) {
            ingredients[i] = transformIngredient(additionalInfoDTOs.get(i));
        }
        return ingredients;
    }

    public static Account transformAccount(AccountDTO accountDTO) {
        return new Account(
                App.getUUID(),
                accountDTO.getName(),
                accountDTO.getEmail(),
                accountDTO.getPhone(),
                transformListAddresses(accountDTO.getAddresses())
        );
    }

    public static List<Address> transformListAddresses(List<AddressDTO> addressesDTO) {
        List<Address> addressList = new ArrayList<>();
        for(AddressDTO addressDTO : addressesDTO) {
            addressList.add(transformAddress(addressDTO));
        }
        return addressList;
    }

    public static Address transformAddress(AddressDTO addressDTO) {
        return new Address(
                addressDTO.getId(),
                addressDTO.getTitle(),
                addressDTO.getCity(),
                addressDTO.getStreet(),
                addressDTO.getHouse(),
                addressDTO.getOffice(),
                addressDTO.getFloor(),
                addressDTO.getEntrance(),
                addressDTO.getCode()
        );
    }

    public static AccountDTO transformAccountDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setEmail(account.getEmail());
        accountDTO.setId(account.getId());
        accountDTO.setName(account.getName());
        accountDTO.setPhone(account.getPhone());
        if (account.getAddresses() != null)
            accountDTO.setAddresses(transformListAddressesDTO(account.getAddresses()));
        return accountDTO;
    }

    public static List<AddressDTO> transformListAddressesDTO(List<Address> addresses) {
        List<AddressDTO> addressesDTO = new ArrayList<>();
        for (Address address : addresses) {
            addressesDTO.add(transformAddressDTO(address));
        }
        return addressesDTO;
    }

    public static AddressDTO transformAddressDTO(Address address) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(address.getId());
        addressDTO.setCity(address.getCity());
        addressDTO.setCode(address.getCode());
        addressDTO.setEntrance(address.getEntrance());
        addressDTO.setFloor(address.getFloor());
        addressDTO.setHouse(address.getHouse());
        addressDTO.setOffice(address.getOffice());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setTitle(address.getTitle());
        return addressDTO;
    }
}
