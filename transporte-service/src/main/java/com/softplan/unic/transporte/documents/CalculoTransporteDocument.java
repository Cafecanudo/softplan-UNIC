package com.softplan.unic.transporte.documents;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Document("calculo_transporte")
public class CalculoTransporteDocument {

    @Id
    private String id;

}
