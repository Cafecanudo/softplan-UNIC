package com.softplan.unic.core.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

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
    @NotNull
    @EqualsAndHashCode.Exclude
    private VeiculoBean veiculo;

    @ApiModelProperty(notes = "vias")
    @NotNull
    @EqualsAndHashCode.Exclude
    private List<ViaBean> vias;

    @ApiModelProperty(notes = "Produtos que o veiculo irá transportar")
    @NotNull
    @EqualsAndHashCode.Exclude
    private List<ItemBean> itens;

    @ApiModelProperty(notes = "calculo")
    private CalculoBean calculo;

}
