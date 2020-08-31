package pl.agh.student.pcmz.pracainzynierska.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false, length = 11)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "OrderID")
    private Order order;

    @Column(name = "FakturaXlIDProforma", length = 40)
    private String fakturaXlIdProforma;

    @Column(name = "InvoiceNameProforma", length = 40)
    private String invoiceNameProforma;

    @Column(name = "FakturaXlIDVat", length = 40)
    private String fakturaXlIdVat;

    @Column(name = "InvoiceNameVat", length = 40)
    private String invoiceNameVat;
}
