package com.softplan.unic.core.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.softplan.unic.core.beans.converters.ProfileConverter;
import com.softplan.unic.core.beans.enums.ProfileType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "user")
public class UserBean {

    @ApiModelProperty(notes = "User ID")
    @Size(max = 60, message = "Max 60 characters")
    private String id;

    @ApiModelProperty(notes = "Name of user", required = true)
    @NotBlank(message = "Can not be empty")
    @Size(max = 100, message = "Max 100 characters")
    private String nome;

    @ApiModelProperty(notes = "E-mail of user", required = true)
    @NotBlank(message = "Can not be empty")
    @Size(max = 120, message = "Max 120 characters")
    @Email(message = "Invalid e-mail.")
    private String email;

    @ApiModelProperty(notes = "Password of user", required = true)
    @NotBlank(message = "Can not be empty")
    @Size(min = 6, max = 15, message = "Min 6 and max 15 characters")
    private String senha;

    @ApiModelProperty(notes = "Profile of user", required = true)
    @NotNull(message = "Can not be empty")
    @JsonDeserialize(using = ProfileConverter.class)
    private ProfileType perfil;
}
