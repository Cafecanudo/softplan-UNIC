package com.softplan.unic.core.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "item")
public class ItemBean {

    @ApiModelProperty(notes = "Produto")
    private ProdutoBean produto;

    @ApiModelProperty(notes = "Quantidade do produto")
    private Double quantidade;

}
