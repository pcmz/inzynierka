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
@Table(name = "delivery", schema = "northwind")
public class Delivery implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false, length = 11)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "OrderID")
    private Order order;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DeliveryAddressID")
    private DeliveryAddress deliveryAddress;

    @Column(name = "ExternalID", length = 40)
    private String externalID;

    @Column(name = "State", length = 40)
    private String state;
}
