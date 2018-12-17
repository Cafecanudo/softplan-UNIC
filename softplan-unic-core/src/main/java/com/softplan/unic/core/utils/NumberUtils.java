package com.softplan.unic.core.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberUtils {

    public static Double arredondarParaCima(Double valor) {
        return arredondarParaCima(valor, 2);
    }

    public static Double arredondarParaCima(Double valor, int casaDecimais) {
        BigDecimal bd = new BigDecimal(valor);
        bd = bd.setScale(casaDecimais, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
