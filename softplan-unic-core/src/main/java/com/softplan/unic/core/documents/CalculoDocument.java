package com.softplan.unic.core.documents;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CalculoDocument {

    @NotNull
    private Double pesoTransportado;

    @NotNull
    private Double valorTransporte;

    @NotNull
    private Double valorEmMercadoria;

    @NotNull
    private Double distanciaPercorrido;
}
