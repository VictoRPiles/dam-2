package org.example;

import java.io.Serializable;

/**
 * @author victor
 */
public record Vehicle(String matricula,
                      String model,
                      @SuppressWarnings("SpellCheckingInspection") int anyMatriculacio,
                      String tipusCombustible,
                      String dniClient)
        implements Serializable {
    private static final Long serialVersionUID = 1907L;

    @SuppressWarnings("SpellCheckingInspection")
    public Vehicle(String matricula, String model, int anyMatriculacio, String tipusCombustible, String dniClient) {
        this.matricula = matricula;
        this.model = model;
        this.anyMatriculacio = anyMatriculacio;
        this.tipusCombustible = tipusCombustible;
        this.dniClient = dniClient;
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Override
    public String toString() {
        return "Vehicle{" +
                "matricula='" + matricula + '\'' +
                ", model='" + model + '\'' +
                ", anyMatriculacio=" + anyMatriculacio +
                ", tipusCombustible='" + tipusCombustible + '\'' +
                ", dniClient='" + dniClient + '\'' +
                '}';
    }
}
