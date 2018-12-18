package com.softplan.unic.core.documents;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class RotaDocument {

    @NotNull
    private ViaDocument via;

    @NotNull
    private Double kilometros;
}
