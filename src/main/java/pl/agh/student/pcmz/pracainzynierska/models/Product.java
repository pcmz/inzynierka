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
@Table(name = "products", schema = "northwind")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductID", unique = true, nullable = false, length = 11)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "SupplierID")
    private Supplier supplier;

//    @ManyToOne(fetch = FetchType.EAGER )
//    @JoinColumn(name = "CategoryID")
//    private Category category;

    @Column(name = "ProductName", length = 40)
    private String productName;

    @Column(name = "Unit", length = 40)
    private String unit;

    @Column(name = "QuantityPerUnit", length = 20)
    private String quantityPerUnit;

    @Column(name = "UnitPrice", precision = 1)
    private Float unitPrice;

    @Column(name = "Ipath", length = 250)
    private String ipath;

    @Column(name = "AvailableColours", length = 40)
    private String availableColours;

    @Column(name = "Quantity", length = 11)
    private Integer quantity;

    @Column(name = "VAT", length = 3)
    private Integer vat;

//    @Column(name = "UnitsInStock", length = 6, columnDefinition = "SMALLINT")
//    private Short unitsInStock;
//
//    @Column(name = "UnitsOnOrder", length = 6, columnDefinition = "SMALLINT")
//    private Short unitsOnOrder;
//
//    @Column(name = "ReorderLevel", length = 6, columnDefinition = "SMALLINT")
//    private Short reorderLevel;
//
//    @Column(name = "Discontinued", length = 1, columnDefinition = "TINYINT")
//    @Type(type = "org.hibernate.type.NumericBooleanType")
//    private Boolean discontinued;
}
