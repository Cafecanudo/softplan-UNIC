package com.softplan.unic.core.documents;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Document("calculo_transporte")
public class CalculoTransporteDocument {

    @Id
    private String id;

    @NotNull
    private VeiculoDocument veiculo;

    @NotNull
    private List<RotaDocument> rotas;

    @NotNull
    private List<ItemDocument> carga;

    @NotNull
    private CalculoDocument calculo;

}
