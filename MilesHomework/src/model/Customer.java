package model;

public class Customer {
    private int id;
    private String name;
    private String phone;
    private String cardNumber;
    private int miles;
    private String password;

    public Customer(int id, String name, String phone, String cardNumber, int miles, String password) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.cardNumber = cardNumber;
        this.miles = miles;
        this.password = password;
    }

 
    public int getId() { return id; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getCardNumber() { return cardNumber; }
    public int getMiles() { return miles; }
    public String getPassword() { return password; }
    
 
    public void setMiles(int miles) { this.miles = miles; }
}