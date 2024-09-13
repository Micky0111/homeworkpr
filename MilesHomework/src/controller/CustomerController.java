package controller;

import model.Customer;
import service.CustomerService;

public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // 客戶登入
    public Customer login(String cardNumber, String password) {
        return customerService.login(cardNumber, password);
    }

    // 客戶里程兌換
    public boolean redeemMiles(Customer customer, int miles) {
        return customerService.redeemMiles(customer, miles);
    }
}