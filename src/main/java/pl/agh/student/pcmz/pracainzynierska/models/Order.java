package pl.agh.student.pcmz.pracainzynierska.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderID", unique = true, nullable = false, length = 11)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CustomerID")
    private Customer customer;

    @CreationTimestamp
    private LocalDateTime createDate;

    @UpdateTimestamp
    private LocalDateTime updateDate;

//    @Temporal(TemporalType.DATE)
//    @Column(name = "OrderDate")
//    private Date orderDate;
//
//    @Temporal(TemporalType.DATE)
//    @Column(name = "RequiredDate")
//    private Date requiredDate;
//
//    @Temporal(TemporalType.DATE)
//    @Column(name = "ShippedDate")
//    private Date shippedDate;
//
//    @Column(name = "Freight", precision = 1)
//    private Float freight;
//
//    @Column(name = "ShipName", length = 40)
//    private String shipName;
//
//    @Column(name = "ShipAddress", length = 60)
//    private String shipAddress;
//
//    @Column(name = "ShipCity", length = 15)
//    private String shipCity;
//
//    @Column(name = "ShipRegion", length = 15)
//    private String shipRegion;
//
//    @Column(name = "ShipPostalCode", length = 10)
//    private String shipPostalCode;
//
//    @Column(name = "ShipCountry", length = 15)
//    private String shipCountry;
}
