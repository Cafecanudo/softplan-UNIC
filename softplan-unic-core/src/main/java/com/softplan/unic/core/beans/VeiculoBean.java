package com.softplan.unic.core.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "veiculo")
public class VeiculoBean {

    @ApiModelProperty(notes = "ID do Veículo")
    private String id;

    @ApiModelProperty(notes = "Nome do veículo")
    @NotBlank
    @Size(max = 20, message = "Max 20 characters")
    @EqualsAndHashCode.Exclude
    private String nome;

    @ApiModelProperty(notes = "Uma descrição completa do veículo")
    @Size(max = 100, message = "Max 100 characters")
    @EqualsAndHashCode.Exclude
    private String descricao;

    @ApiModelProperty(notes = "Data de cadastro")
    @JsonProperty(value = "dataCadastro")
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @EqualsAndHashCode.Exclude
    private LocalDate dataCadastro;

}
