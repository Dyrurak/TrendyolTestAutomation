package entities;

import com.opencsv.bean.CsvBindByName;


public class UserInformation {
    @CsvBindByName(column = "Customer")
    public String customerType;
    @CsvBindByName(column = "Email")
    public String email;
    @CsvBindByName(column = "Password")
    public String password;
}
