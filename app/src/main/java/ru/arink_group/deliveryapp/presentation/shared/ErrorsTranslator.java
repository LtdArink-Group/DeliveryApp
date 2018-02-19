package ru.arink_group.deliveryapp.presentation.shared;

/**
 * Created by kirillvs on 22.11.17.
 */

public class ErrorsTranslator {

    private ErrorsTranslator(){}

    public static String translate(String error) {
        switch (error) {
            case "delivery_time":
                return "Время заказа";
            case "company_id":
                return "Компания";
            case "address_id":
                return "Адрес";
            case "account_id":
                return "Необходимо заполнить профиль";
            case "name":
                return "Имя";
            case "phone":
                return "Номер телефона";
            case "email":
                return "e-mail";
            case "title":
                return "Наименование адреса";
            case "city":
                return "Город";
            case "street":
                return "Улица";
            case "house":
                return "Номер дома";
            case "connection":
                return "Проверьте соединение с интернетом";
            case "internal":
                return "Внутренняя ошибка";
            default:
                return "Неизвестная ошибка";
        }
    }
}
