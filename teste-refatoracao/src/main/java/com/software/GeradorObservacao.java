package com.software;

import java.util.List;
import java.util.stream.Collectors;

public class GeradorObservacao {

    public String geraObservacao(List lista) {
        return !lista.isEmpty() ? retornaCodigos(lista) + "." : "";
    }

    private String retornaCodigos(List lista) {
        String texto = String.format("Fatura %s de simples remessa: ",
                lista.size() >= 2 ? "das notas fiscais" : "da nota fiscal");

        return texto + lista.stream().map(String::valueOf)
                .collect(Collectors.joining(", ")).toString()
                .replaceAll("(.*)(, )([0-9]+)$", "$1 e $3");
    }
}