package com.softplan.unic.core.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "rota")
public class RotaBean {

    @NotNull
    @Valid
    @ApiModelProperty(notes = "Via")
    private ViaBean via;

    @NotNull
    @ApiModelProperty(notes = "Quantidades de KL")
    private Double quilometros;
}
