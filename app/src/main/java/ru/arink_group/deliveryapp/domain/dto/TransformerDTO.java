package ru.arink_group.deliveryapp.domain.dto;

import java.util.ArrayList;
import java.util.List;

import ru.arink_group.deliveryapp.App;
import ru.arink_group.deliveryapp.domain.dao.Account;
import ru.arink_group.deliveryapp.domain.dao.Address;
import ru.arink_group.deliveryapp.domain.dao.Category;
import ru.arink_group.deliveryapp.domain.dao.Company;
import ru.arink_group.deliveryapp.domain.dao.CompanyWorkingDay;
import ru.arink_group.deliveryapp.domain.dao.ContactInfo;
import ru.arink_group.deliveryapp.domain.dao.Delivery;
import ru.arink_group.deliveryapp.domain.dao.Ingredient;
import ru.arink_group.deliveryapp.domain.dao.Order;
import ru.arink_group.deliveryapp.domain.dao.OrderAddressInfo;
import ru.arink_group.deliveryapp.domain.dao.OrderIngredient;
import ru.arink_group.deliveryapp.domain.dao.OrderProduct;
import ru.arink_group.deliveryapp.domain.dao.Period;
import ru.arink_group.deliveryapp.domain.dao.Portion;
import ru.arink_group.deliveryapp.domain.dao.Product;
import ru.arink_group.deliveryapp.presentation.shared.DateTime;

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
        product.setBrief(productDTO.getBrief());
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

    public static ContactInfo transformContactInfo(ContactInfoDTO contactInfoDTO) {
        return new ContactInfo(
                contactInfoDTO.getEmail(),
                contactInfoDTO.getPhone(),
                contactInfoDTO.getWeb(),
                contactInfoDTO.getAddress(),
                contactInfoDTO.getGeotag(),
                contactInfoDTO.getGeotagCafe()
        );
    }

    public static Period transformPeriod(PeriodDTO periodDTO) {
        return new Period(periodDTO.getStart(), periodDTO.getEnd());
    }

    public static Delivery transformDelivery(DeliveryDTO deliveryDTO) {
        return new Delivery(
                deliveryDTO.getCost(),
                deliveryDTO.getFreeShipping(),
                deliveryDTO.getPickupDiscount(),
                transformPeriod(deliveryDTO.getPeriod())
        );
    }

    public static CompanyWorkingDay transformCompanyWorkingDay(CompanyWorkingDayDTO companyWorkingDayDTO) {
        return new CompanyWorkingDay(companyWorkingDayDTO.getDayOfWeek(), companyWorkingDayDTO.getStartWork(), companyWorkingDayDTO.getEndWork());
    }

    public static List<CompanyWorkingDay> transformListCompanyWorkingDayDTOS(List<CompanyWorkingDayDTO> companyWorkingDayDTOS) {
        List<CompanyWorkingDay> companyWorkingDays = new ArrayList<>();
        if(companyWorkingDayDTOS == null) return companyWorkingDays; //TODO delete after backend implementation
        for(CompanyWorkingDayDTO companyWorkingDayDTO : companyWorkingDayDTOS) {
            companyWorkingDays.add(transformCompanyWorkingDay(companyWorkingDayDTO));
        }
        return companyWorkingDays;
    }

    public static Company transformCompany(CompanyDTO companyDTO) {
        return new Company(
                companyDTO.getId(),
                companyDTO.getName(),
                companyDTO.getCategories(),
                transformListAddresses(companyDTO.getAddresses()),
                companyDTO.getDescription(),
                transformContactInfo(companyDTO.getContactInfo()),
                transformDelivery(companyDTO.getDelivery()),
                companyDTO.getUrl(),
                transformListCompanyWorkingDayDTOS(companyDTO.getWorkingDays())
        );
    }

    public static OrderIngredientDTO createOrderIngredientDTO(Ingredient ingredient) {
        OrderIngredientDTO orderIngredientDTO = new OrderIngredientDTO();
        orderIngredientDTO.setName(ingredient.getName());
        orderIngredientDTO.setQty(ingredient.getCount());
        return orderIngredientDTO;
    }

    public static List<OrderIngredientDTO> createListOrderIngredientDTO(Ingredient[] ingredients) {
        List<OrderIngredientDTO> orderIngredientsDTO = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            orderIngredientsDTO.add(createOrderIngredientDTO(ingredient));
        }
        return orderIngredientsDTO;
    }

    public static OrderProductDTO createOrderProductDTO(Product product) {
        OrderProductDTO orderProductDTO = new OrderProductDTO();
        orderProductDTO.setProductId(product.getId());
        orderProductDTO.setQty(product.getCount());
        orderProductDTO.setMainOption(product.getSelectedPortion().getName());
        orderProductDTO.setIngredients(createListOrderIngredientDTO(product.getSelectedIngredients()));
        return orderProductDTO;
    }

    public static List<OrderProductDTO> createListOrderProductDTO(List<Product> products) {
        List<OrderProductDTO> orderProductsDTO = new ArrayList<>();
        for(Product product : products) {
            orderProductsDTO.add(createOrderProductDTO(product));
        }
        return orderProductsDTO;
    }

    public static OrderDTO createOrderDTO(List<Product> products, Integer addressId, DateTime deliveryTime, Boolean selfPickup, String note) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setAccountId(App.getUUID());
        orderDTO.setCompanyId(Integer.valueOf(App.getCompanyId()));
        orderDTO.setAddressId(addressId);
        orderDTO.setPickup(selfPickup);
        orderDTO.setDevice(App.DEVICE_NAME);
        orderDTO.setOrderProducts(createListOrderProductDTO(products));
        orderDTO.setDeliveryTime(deliveryTime.toCurrentDateString());
        orderDTO.setNote(note);
        return orderDTO;
    }

    public static OrderIngredient transformOrderIngredient(OrderIngredientDTO orderIngredientDTO) {
        return new OrderIngredient(orderIngredientDTO.getQty(), orderIngredientDTO.getTotalCost(), orderIngredientDTO.getName());
    }

    public static List<OrderIngredient> transformListOrderIngredients(List<OrderIngredientDTO> orderIngredientsDTO) {
        List<OrderIngredient> orderIngredients = new ArrayList<>();
        for (OrderIngredientDTO orderIngredientDTO : orderIngredientsDTO) {
            orderIngredients.add(transformOrderIngredient(orderIngredientDTO));
        }
        return orderIngredients;
    }

    public static OrderProduct transformOrderProduct(OrderProductDTO orderProductDTO) {
        return new OrderProduct(
                orderProductDTO.getId(),
                orderProductDTO.getProductTitle(),
                orderProductDTO.getTotalCost(),
                orderProductDTO.getProductId(),
                orderProductDTO.getMainOption(),
                orderProductDTO.getQty(),
                transformListOrderIngredients(orderProductDTO.getIngredients())
        );
    }

    public static List<OrderProduct> transformListOrderProduct(List<OrderProductDTO> orderProductsDTO) {
        List<OrderProduct> orderProducts = new ArrayList<>();
        for (OrderProductDTO orderProductDTO : orderProductsDTO) {
            orderProducts.add(transformOrderProduct(orderProductDTO));
        }
        return orderProducts;
    }

    public static OrderAddressInfo transformOrderAddressInfo(OrderAddressInfoDTO orderAddressInfoDTO) {
        if (orderAddressInfoDTO.getId() == null) return null;
        return new OrderAddressInfo(
                orderAddressInfoDTO.getId(),
                orderAddressInfoDTO.getCity(),
                orderAddressInfoDTO.getCode(),
                orderAddressInfoDTO.getFloor(),
                orderAddressInfoDTO.getHouse(),
                orderAddressInfoDTO.getTitle(),
                orderAddressInfoDTO.getOffice(),
                orderAddressInfoDTO.getStreet(),
                orderAddressInfoDTO.getEntrance()
        );
    }

    public static Order transformOrder(OrderDTO orderDTO) {
        return new Order(
                orderDTO.getId(),
                orderDTO.getStatus(),
                orderDTO.getTotalCost(),
                orderDTO.getDeliveryCost(),
                orderDTO.getCompanyId(),
                orderDTO.getAccountId(),
                orderDTO.getDeliveryTime(),
                orderDTO.getNote(),
                orderDTO.getPickup(),
                transformListOrderProduct(orderDTO.getOrderProducts()),
                orderDTO.getNum(),
                transformOrderAddressInfo(orderDTO.getAddressInfo())
        );
    }

    public static List<Order> transformListOrder(List<OrderDTO> ordersDTO) {
        List<Order> orders = new ArrayList<>();
        for (OrderDTO orderDTO : ordersDTO) {
            orders.add(transformOrder(orderDTO));
        }
        return orders;
    }

    public static OrderIngredientDTO trahsformOrderIngredientDTO(OrderIngredient ingredient) {
        OrderIngredientDTO orderIngredientDTO = new OrderIngredientDTO();
        orderIngredientDTO.setName(ingredient.getName());
        orderIngredientDTO.setQty(ingredient.getQty());
        return orderIngredientDTO;
    }

    public static List<OrderIngredientDTO> trahsformListOrderIngredientDTO(List<OrderIngredient> ingredients) {
        List<OrderIngredientDTO> orderIngredientsDTO = new ArrayList<>();
        for (OrderIngredient ingredient : ingredients) {
            orderIngredientsDTO.add(trahsformOrderIngredientDTO(ingredient));
        }
        return orderIngredientsDTO;
    }

    public static OrderProductDTO trahsformOrderProductDTO(OrderProduct product) {
        OrderProductDTO orderProductDTO = new OrderProductDTO();
        orderProductDTO.setProductId(product.getProductId());
        orderProductDTO.setQty(product.getQty());
        orderProductDTO.setMainOption(product.getMainOption());
        orderProductDTO.setIngredients(trahsformListOrderIngredientDTO(product.getIngredients()));
        return orderProductDTO;
    }

    public static List<OrderProductDTO> trahsformListOrderProductDTO(List<OrderProduct> products) {
        List<OrderProductDTO> orderProductsDTO = new ArrayList<>();
        for(OrderProduct product : products) {
            orderProductsDTO.add(trahsformOrderProductDTO(product));
        }
        return orderProductsDTO;
    }

    public static OrderDTO trahsformOrderDTO(Order order, Integer addressId, DateTime deliveryTime, String note) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setAccountId(App.getUUID());
        orderDTO.setCompanyId(Integer.valueOf(App.getCompanyId()));
        orderDTO.setAddressId(addressId);
        orderDTO.setPickup(order.getPickup());
        orderDTO.setDevice(App.DEVICE_NAME);
        orderDTO.setOrderProducts(trahsformListOrderProductDTO(order.getProducts()));
        orderDTO.setDeliveryTime(deliveryTime.toCurrentDateString());
        orderDTO.setNote(note);
        return orderDTO;
    }

}
