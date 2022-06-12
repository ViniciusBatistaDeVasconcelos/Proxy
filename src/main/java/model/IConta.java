package model;

import java.util.List;

public interface IConta {

    boolean depositar(float valor);

    boolean sacar(float valor);

    boolean transferir(String numeroDestino, float valor);

    List<String> getSaldo();
}
