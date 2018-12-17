package com.softplan.unic.core.documents;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Document("veiculo")
public class VeiculoDocument {

    @Id
    private String id;

    @NotBlank
    @Size(max = 20)
    @Indexed(unique = true)
    @EqualsAndHashCode.Exclude
    private String nome;

    @NotNull
    @EqualsAndHashCode.Exclude
    private float fatorMultiplicador;

    @EqualsAndHashCode.Exclude
    private LocalDate dataCadastro;

}