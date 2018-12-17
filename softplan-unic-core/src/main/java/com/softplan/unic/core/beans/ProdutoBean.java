package com.softplan.unic.core.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "produto")
public class ProdutoBean {

    @ApiModelProperty(notes = "ID do Produto")
    private String id;

    @ApiModelProperty(notes = "Nome do produto")
    @NotBlank
    @Size(max = 20, message = "Max 20 characters")
    @EqualsAndHashCode.Exclude
    private String nome;

    @ApiModelProperty(notes = "Peso do produto")
    @JsonProperty(value = "peso")
    @NotNull
    @EqualsAndHashCode.Exclude
    private float peso;

}