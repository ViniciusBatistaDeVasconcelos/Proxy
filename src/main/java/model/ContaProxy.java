package model;

import java.util.List;

public class ContaProxy implements IConta {

    private Conta conta;

    public ContaProxy(String numero, String senha) throws Exception {
        this.conta = new Conta(numero, senha);
    }

    @Override
    public boolean depositar(float valor) {
        return this.conta.depositar(valor);
    }

    @Override
    public boolean sacar(float valor) {
        return this.conta.sacar(valor);
    }

    @Override
    public boolean transferir(String numeroDestino, float valor) {
        return this.conta.transferir(numeroDestino, valor);
    }

    @Override
    public List<String> getSaldo() {
        return this.conta.getSaldo();
    }
}
