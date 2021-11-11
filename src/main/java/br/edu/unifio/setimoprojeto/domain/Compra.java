package br.edu.unifio.setimoprojeto.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"nome"})
})

@Data
public class Compra {
    @Id//define chave primaria
    @GeneratedValue (strategy = GenerationType.IDENTITY)       //auto incremento
    private Integer codCompra;

    //coluna nome
    @NotBlank(message = "O campo nome é obrigatório")
    @Size(min = 3, max= 50, message = "O tamanho do campo nome deve ser entre 3 e 50")
    private String nomeUsuario;

    @NotBlank(message = "O campo nome do jogo é obrigatório")
    @Size(min = 2, max = 50, message = "O tamanho do campo nome deve ser entre 3 e 50")
    private String nomeJogo;


    private LocalDateTime horarioCompra;

    @NotNull(message = "O campo preco é obrigatório")
    @DecimalMin(value = "0.01", message = "o valor minimo para o preço é 0.01 conto")
    @DecimalMax(value = "1000", message = "o valor max para o preço é 1000 dolar")
    private Double precoJogo;

}
