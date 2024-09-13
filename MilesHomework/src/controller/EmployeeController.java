package controller;

import model.Customer;
import model.Employee;
import service.CustomerService;
import service.EmployeeService;

public class EmployeeController {
    private final EmployeeService employeeService;
    private final CustomerService customerService;

    public EmployeeController(EmployeeService employeeService, CustomerService customerService) {
        this.employeeService = employeeService;
        this.customerService = customerService;
    }

    // 員工登入
    public Employee login(int employeeId, String password) {
        return employeeService.login(employeeId, password);
    }

    // 員工查詢客戶里程
    public Customer searchCustomerMiles(String cardNumber) {
        return employeeService.searchCustomerMiles(customerService, cardNumber);
    }
}