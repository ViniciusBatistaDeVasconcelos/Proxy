package model;

import java.util.HashMap;
import java.util.Map;

public class BD {

    private static Map<String, Conta> contas = new HashMap<>();

    public static Conta getConta(String numero) {
        return contas.get(numero);
    }

    public static void addConta(Conta conta) {
        contas.put(conta.getNumero(), conta);
    }
}
