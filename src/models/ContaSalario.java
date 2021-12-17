package models;

import Util.UI;
import interfaces.Tributavel;

public class ContaSalario extends Conta implements Tributavel {

  private final int limiteSaques = 3;
  private int saquesRealizados;

  public ContaSalario(int agencia, int numero, String banco, double saldo, TipoConta tipoConta,Cliente cliente) {
    super(agencia, numero, banco, saldo, tipoConta, cliente);
  }

  public ContaSalario(int agencia, int numero, String banco, double saldo, Cliente cliente){
    super(agencia,numero,banco,saldo,cliente);
  }

  @Override
  public double getSaldo() {
    return super.saldo;
  }

  @Override
  public void saca(double valor) {
    if (atingiuNumeroLimiteSaques()) {
      UI.exibirTexto("Limite de saques atingido.");
    } else if (saldoInsuficiente(valor)) {
      UI.exibirTexto("Saldo insuficiente.");
    } else {
      super.saldo -= super.saldo * getValorImposto();
      super.saldo -= valor;
      this.saquesRealizados++;
      UI.exibirTexto("Saque realizado.");
    }
  }

  private boolean atingiuNumeroLimiteSaques() {
    return !(this.saquesRealizados < this.limiteSaques);
  }

  private boolean saldoInsuficiente(double valor) {
    return super.saldo == 0.0 || valor > super.saldo;
  }

  @Override
  public void deposita(double valor) {
    super.saldo += valor;
  }

  @Override
  public void transferir(double valor, Conta contaDestino) {
    if (saldoInsuficiente(valor)) {
      UI.exibirTexto("Saldo insuficiente");
    } else {
      super.saldo -= valor;
      contaDestino.deposita(valor);
      UI.exibirTexto("Transferência realizada com sucesso.");
    }
  }

  @Override
  public String toString() {
    return super.toString() + ", saldo=" + getSaldo();
  }

  @Override
  public Double getValorImposto() {
    return 0.05;
  }
}
