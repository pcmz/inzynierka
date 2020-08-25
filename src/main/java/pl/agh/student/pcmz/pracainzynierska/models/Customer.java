package pl.agh.student.pcmz.pracainzynierska.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "customers")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CustomerID", unique = true, nullable = false, length = 11)
    private Long id;

    @Column(name = "CompanyName", length = 40)
    private String companyName;

    @Column(name = "CustomerEmail", length = 40)
    private String customerEmail;

    @Column(name = "CustomerPhone", length = 40)
    private String customerPhone;

    @Column(name = "ShippingAddress", length = 40)
    private String shippingAddress;

//    @Column(name = "ContactName", length = 30)
//    private String contactName;
//
//    @Column(name = "ContactTitle", length = 30)
//    private String contactTitle;
//
//    @Column(name = "Address", length = 60)
//    private String address;
//
//    @Column(name = "City", length = 15)
//    private String city;
//
//    @Column(name = "Region", length = 15)
//    private String region;
//
//    @Column(name = "PostalCode", length = 10)
//    private String postalCode;
//
//    @Column(name = "Country", length = 15)
//    private String country;
//
//    @Column(name = "Phone", length = 24)
//    private String phone;
//
//    @Column(name = "Fax", length = 24)
//    private String fax;
}
