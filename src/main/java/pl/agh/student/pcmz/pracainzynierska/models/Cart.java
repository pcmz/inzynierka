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
@Table(name = "carts")
public class Cart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "odID", unique = true, nullable = false, length = 11)
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "OrderID")
//    private Order order;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ProductID")
    private Product product;

//    @Column(name = "UnitPrice", precision = 1)
//    private Float unitPrice;

    @Column(name = "Quantity", length = 6, columnDefinition = "SMALLINT")
    private Short quantity;

    @Column(name = "Subtotal", precision = 1)
    private Float subtotal;

//    @Column(name = "Discount", precision = 1)
//    private Float discount;
}
