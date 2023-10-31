package com.khalil.wdcar.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.StringJoiner;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarRentalBean {
    private String name;
    private String brande;
    private String style;
    private String address;
    private String cin;
    private String city;
    private String country;
    private String firstName;
    private String lastName;
    private String licence;
    private String userName;
    public String toQuery(String cases){
        final var joiner = new StringJoiner(" or ",  "", "");
        if (cases == "car") {
            if (name != null) joiner.add("name like '" + name.trim() + "%'");
            if (brande != null) joiner.add("brande like '" + brande.trim() + "%'");
            if (style != null) joiner.add("style like '" + style.trim() + "%'");
        }
        if (cases == "client") {
            if (address != null) joiner.add("adress like '" + address.trim() + "%'");
            if (cin != null) joiner.add("cin like '" + cin.trim() + "%'");
            if (city != null) joiner.add("city like '" + city.trim() + "%'");
            if (country != null) joiner.add("country like '" + country.trim() + "%'");
            if (firstName != null) joiner.add("first_name like '" + firstName.trim() + "%'");
            if (lastName != null) joiner.add("last_name like '" + lastName.trim() + "%'");
            if (licence != null) joiner.add("licence like '" + licence.trim() + "%'");
            if (userName != null) joiner.add("user_name like '" + userName.trim() + "%'");
        }
        return joiner.toString();
    }
    public CarRentalBean setSearch(String label){
        CarRentalBean rentalBean = new CarRentalBean();
        rentalBean.setName(label);
        rentalBean.setBrande(label);
        rentalBean.setStyle(label);

        rentalBean.setAddress(label);
        rentalBean.setCin(label);
        rentalBean.setCity(label);
        rentalBean.setCountry(label);
        rentalBean.setFirstName(label);
        rentalBean.setLastName(label);
        rentalBean.setLicence(label);
        rentalBean.setUserName(label);
        return rentalBean;
    }
}
