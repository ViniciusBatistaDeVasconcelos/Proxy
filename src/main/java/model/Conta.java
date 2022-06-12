package model;


import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class Conta implements IConta {

    private Titular titular;
    private String numero;
    private String senha;
    private float saldo;

    public Conta(Titular titular, String numero, String senha, float saldo) {
        this.titular = titular;
        this.numero = numero;
        this.senha = senha;
        this.saldo = saldo;
    }

    public Conta(String numero, String senha) throws Exception {
        Conta objeto = BD.getConta(numero);

        if (objeto == null)
            throw new Exception("Verifique se o número da conta está correto.");
        if (!objeto.senha.equals(senha))
            throw new Exception("Não foi possível acessar sua conta.");

        this.titular = objeto.titular;
        this.numero = objeto.numero;
        this.saldo = objeto.saldo;
    }

    public String getNumero() {
        return numero;
    }

    @Override
    public boolean depositar(float valor) {
        if (valor > 0) {
            this.saldo = this.saldo + valor;
            return true;
        }
        return false;
    }

    @Override
    public boolean sacar(float valor) {
        if (valor > 0 && valor <= this.saldo) {
            this.saldo = this.saldo - valor;
            return true;
        }
        return false;
    }

    @Override
    public boolean transferir(String numeroDestino, float valor) {
        Conta contaDestino = BD.getConta(numeroDestino);

        if (contaDestino == null) return false;
        return this.sacar(valor) && contaDestino.depositar(valor);
    }

    @Override
    public List<String> getSaldo() {
        DecimalFormat df = new DecimalFormat("0.##");
        String dx = df.format(this.saldo);
        return Arrays.asList(this.titular.getNome(), dx);
    }
}

