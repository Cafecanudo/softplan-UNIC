package com.softplan.unic.user.documents;

import com.softplan.unic.core.beans.enums.ProfileType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Document("user")
public class UserDocument {

    @Id
    private String id;

    @NotBlank
    @Size(max = 100, message = "Max 100 characters")
    private String nome;

    @Email
    @NotBlank
    @Indexed(unique = true)
    private String email;

    @NotBlank
    @Size(min = 6, max = 15)
    private String senha;

    @NotNull
    private ProfileType perfil;

}
