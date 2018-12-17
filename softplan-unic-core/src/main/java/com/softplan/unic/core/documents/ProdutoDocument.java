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
    @Size(max = 20)
    @Indexed(unique = true)
    @EqualsAndHashCode.Exclude
    private String nome;

    @NotBlank
    @EqualsAndHashCode.Exclude
    private float peso;

}
