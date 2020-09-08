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
@Table(name = "delivery_addresses", schema = "northwind")
public class DeliveryAddress implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false, length = 11)
    private Long id;

    @Column(name = "City", length = 40)
    private String city;

    @Column(name = "Code", length = 40)
    private String code;

    @Column(name = "Country", length = 40)
    private String country;

    @Column(name = "House_number", length = 40)
    private String house_no;

    @Column(name = "Post_office", length = 40)
    private String post_office;

    @Column(name = "Street", length = 40)
    private String street;
}
