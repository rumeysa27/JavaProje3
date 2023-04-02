package Proje3;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    String username;
    String password;
  public List<Account> accounts;

  public Customer(String username, String password){
      this.username=username;
      this.password=password;
  }

    public Customer(String username, String password, List<Account> accounts) {
        this.username = username;
        this.password = password;
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", accounts=" + accounts +
                '}';
    }


}
