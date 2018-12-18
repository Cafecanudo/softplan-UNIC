package com.softplan.unic.core.documents;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Document("produto")
public class ProdutoDocument {

    @Id
    private String id;

    @NotBlank
    @Size(max = 200)
    @Indexed(unique = true)
    private String nome;

    @Size(max = 1000)
    @EqualsAndHashCode.Exclude
    private String image;

    @NotBlank
    private Double peso;

    @NotBlank
    private Double valor;

}
