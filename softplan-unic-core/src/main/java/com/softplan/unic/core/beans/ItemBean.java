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
@ApiModel(value = "item")
public class ItemBean {

    @Valid
    @NotNull
    @ApiModelProperty(notes = "Produto")
    private ProdutoBean produto;

    @ApiModelProperty(notes = "Quantidade do produto")
    @NotNull
    private Double quantidade;

}
