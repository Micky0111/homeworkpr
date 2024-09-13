package model;

public class Employee {
    private int employeeId;
    private String employeeName;
    private String employeeContact;
    private String department;
    private String password;

    public Employee(int employeeId, String employeeName, String employeeContact, String department, String password) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeContact = employeeContact;
        this.department = department;
        this.password = password;
    }

    public int getEmployeeId() { return employeeId; }
    public String getEmployeeName() { return employeeName; }
    public String getEmployeeContact() { return employeeContact; }
    public String getDepartment() { return department; }
    public String getPassword() { return password; }
}