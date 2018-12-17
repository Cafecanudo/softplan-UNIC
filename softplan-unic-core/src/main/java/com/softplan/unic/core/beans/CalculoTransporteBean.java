package com.softplan.unic.core.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "calculo_transporte")
public class CalculoTransporteBean {

    @ApiModelProperty(notes = "ID do registro")
    private String id;

    @ApiModelProperty(notes = "Veiculo")
    @Valid
    @NotNull
    @EqualsAndHashCode.Exclude
    private VeiculoBean veiculo;

    @ApiModelProperty(notes = "Rotas")
    @NotNull
    @Valid
    @EqualsAndHashCode.Exclude
    private List<RotaBean> rotas;

    @ApiModelProperty(notes = "Produtos que o veiculo irá transportar")
    @NotNull
    @Valid
    @EqualsAndHashCode.Exclude
    private List<ItemBean> carga;

    @ApiModelProperty(notes = "calculo")
    private CalculoBean calculo;

}
