package com.softplan.unic.core.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "calculo")
public class CalculoBean {

    private Double pesoTransportado;
    private Double valorTransporte;
    private Double valorEmMercadoria;
    private Double distanciaPercorrido;

}
