package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ContaProxyTest {

    @BeforeEach
    void setUp() {
        Titular titular1 = new Titular("Renato Marcelo da Rocha", "044.377.022-04");
        Conta conta1 = new Conta(titular1, "4362.104.00180074425-8", "625ZPHmRkE", 300.50f);
        BD.addConta(conta1);

        Titular titular2 = new Titular("Eduarda Daiane Santos", "204.490.255-94");
        Conta conta2 = new Conta(titular2, "2023.104.18680953-1", "F4AOTouT3T", 50.0f);
        BD.addConta(conta2);
    }

    @Test
    void deveRetornarExcecaoNumeroDaContaInvalida() {
        try {
            ContaProxy conta = new ContaProxy("0000.000.00000000000-0", "625ZPHmRkE");
            fail();
        } catch (Exception e) {
            assertEquals("Verifique se o número da conta está correto.", e.getMessage());
        }
    }

    @Test
    void deveRetornarExcecaoSenhaInvalida() {
        try {
            ContaProxy conta = new ContaProxy("4362.104.00180074425-8", "0000000000");
            fail();
        } catch (Exception e) {
            assertEquals("Não foi possível acessar sua conta.", e.getMessage());
        }
    }

    @Test
    void deveRetornarSaque() throws Exception {
        ContaProxy conta = new ContaProxy("4362.104.00180074425-8", "625ZPHmRkE");
        conta.sacar(50.0f);

        assertEquals(Arrays.asList("Renato Marcelo da Rocha", "250,5"), conta.getSaldo());
    }

    @Test
    void deveRetornarDeposito() throws Exception {
        ContaProxy conta = new ContaProxy("2023.104.18680953-1", "F4AOTouT3T");
        conta.depositar(150.99f);

        assertEquals(Arrays.asList("Eduarda Daiane Santos", "200,99"), conta.getSaldo());
    }

    @Test
    void deveRetornarTransferencia() throws Exception {

        ContaProxy conta1 = new ContaProxy("4362.104.00180074425-8", "625ZPHmRkE");
        conta1.transferir("2023.104.18680953-1", 80.0f);

        ContaProxy conta2 = new ContaProxy("2023.104.18680953-1", "F4AOTouT3T");

        assertEquals(Arrays.asList("Renato Marcelo da Rocha", "220,5"), conta1.getSaldo());
        assertEquals(Arrays.asList("Eduarda Daiane Santos", "130"), conta2.getSaldo());
    }
}