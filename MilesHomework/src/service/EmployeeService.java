package service;

import dao.EmployeeDAO;
import model.Customer;
import model.Employee;

public class EmployeeService {
    private final EmployeeDAO employeeDAO;

    public EmployeeService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    // 員工登入
    public Employee login(int employeeId, String password) {
        return employeeDAO.login(employeeId, password);
    }

    // 查詢客戶里程數
    public Customer searchCustomerMiles(CustomerService customerService, String cardNumber) {
        return customerService.findCustomerByCardNumber(cardNumber);
    }
}