package com.marhasoft.demo.model;

<<<<<<< HEAD
=======
import com.fasterxml.jackson.annotation.JsonFormat;
>>>>>>> 8b560a138af68f332d3323a6648ba384af82dfc3
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
<<<<<<< HEAD
=======
import java.time.LocalDate;
>>>>>>> 8b560a138af68f332d3323a6648ba384af82dfc3

@Entity
@Data
@Table(name = "servico_prestado")
public class ServicoPrestado {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column
    private BigDecimal valor;
<<<<<<< HEAD
=======

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;
>>>>>>> 8b560a138af68f332d3323a6648ba384af82dfc3
}
