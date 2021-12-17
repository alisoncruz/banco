package models;

import Util.UI;
import interfaces.Tributavel;

public class ContaCorrente extends Conta implements Tributavel {

  private double chequeEspecial;

  public ContaCorrente(
      int agencia,
      int numero,
      String banco,
      double saldo,
      double chequeEspecial,
      TipoConta tipoConta,
      Cliente cliente) {
    super(agencia, numero, banco, saldo, tipoConta, cliente);
    this.chequeEspecial = chequeEspecial;
  }

  public ContaCorrente(int agencia, int numero, String banco, double saldo, Cliente cliente){
    super(agencia,numero,banco,saldo,cliente);
  }

  public double getChequeEspecial() {
    return chequeEspecial;
  }

  @Override
  public double getSaldo() {
    return super.saldo + chequeEspecial;
  }

  @Override
  public void saca(double valor) {
    if (saldoInsuficiente(valor)) {
      System.out.println("Saldo insuficiente.");
    } else {
      super.saldo -= getValorImposto() * super.saldo;
      super.saldo -= valor;
      System.out.println("Saque realizado.");
    }
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

  private boolean saldoInsuficiente(double valor) {
    return super.saldo == 0.0 || valor > getSaldo();
  }

  @Override
  public String toString() {
    return "Conta Corrrente "
        + super.toString()
        + ", cheque especial="
        + this.chequeEspecial
        + ", saldo="
        + getSaldo();
  }

  public void setChequeEspecial(double chequeEspecial) {
    this.chequeEspecial = chequeEspecial;
  }

  @Override
  public Double getValorImposto() {
    return 0.1;
  }
}
