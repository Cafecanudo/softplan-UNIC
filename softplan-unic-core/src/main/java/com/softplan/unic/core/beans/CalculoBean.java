package com.softplan.unic.core.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "calculo")
public class CalculoBean {

    @ApiModelProperty(notes = "Peso total do transporte")
    @NotNull
    private Double pesoTransportado;

    @ApiModelProperty(notes = "Valor Total do transporte")
    @NotNull
    private Double valorTransporte;

    @ApiModelProperty(notes = "Valor total da mercadoria transportada")
    @NotNull
    private Double valorEmMercadoria;

    @ApiModelProperty(notes = "Distancia à percorrer/percorrida")
    @NotNull
    private Double distanciaPercorrido;

}
