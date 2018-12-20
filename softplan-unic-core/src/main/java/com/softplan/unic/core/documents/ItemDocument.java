package com.softplan.unic.core.documents;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ItemDocument {

    @NotNull
    private ProdutoDocument produto;

    @NotNull
    private Double quantidade;
}
