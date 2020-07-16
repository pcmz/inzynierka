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
@Table(name = "categories")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryID", unique = true, nullable = false, length = 11)
    private Long id;

    @Column(name = "CategoryName", length = 15)
    private String categoryName;

    @Column(name = "Description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "Picture", length = 40)
    private String picture;
}
