package service;

import dao.CustomerDAO;
import model.Customer;

public class CustomerService {
    private final CustomerDAO customerDAO;

    public CustomerService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    // 客戶登入
    public Customer login(String cardNumber, String password) {
        return customerDAO.login(cardNumber, password);
    }

    // 卡號查找客戶
    public Customer findCustomerByCardNumber(String cardNumber) {
        return customerDAO.findCustomerByCardNumber(cardNumber);
    }

    // 里程兌換
    public boolean redeemMiles(Customer customer, int miles) {
        if (customer.getMiles() >= miles) {
            customer.setMiles(customer.getMiles() - miles);
            customerDAO.updateCustomerMiles(customer);
            return true;
        }
        return false;  // 里程不足
    }
}