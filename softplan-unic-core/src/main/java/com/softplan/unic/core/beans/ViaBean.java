package com.softplan.unic.core.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "via")
public class ViaBean {

    @ApiModelProperty(notes = "ID da VIA")
    private String id;

    @ApiModelProperty(notes = "Nome da VIA")
    @NotBlank
    @Size(max = 20, message = "Max 20 characters")
    @Indexed(unique = true)
    private String nome;

    @ApiModelProperty(notes = "Valor por KM da VIA")
    @NotNull
    private Double valor;

}
