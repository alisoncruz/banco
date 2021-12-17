package models;

import Util.UI;

import java.time.LocalDate;

public class ContaPoupanca extends Conta {

  private LocalDate diaAniversario;
  private double taxaJuros;

  public ContaPoupanca(
      int agencia,
      int numero,
      String banco,
      double saldo,
      LocalDate diaAniversario,
      double taxaJuros,
      TipoConta tipoConta,
      Cliente cliente) {
    super(agencia, numero, banco, saldo, tipoConta, cliente);
    this.diaAniversario = diaAniversario;
    this.taxaJuros = taxaJuros;
  }

  public ContaPoupanca(int agencia, int numero, String banco, double saldo, Cliente cliente) {
    super(agencia, numero, banco, saldo, cliente);
  }

  @Override
  public double getSaldo() {
    if (ehDiaAniversario()) {
      return super.saldo + taxaJuros * super.saldo;
    } else {
      return super.saldo;
    }
  }

  public LocalDate getDiaAniversario() {
    return this.diaAniversario;
  }

  @Override
  public void saca(double valor) {
    if (saldoInsuficiente(valor)) {
      System.out.println("Saldo insuficinte");
    } else if (ehDiaAniversario() && taxaJuros <= 0.02) {
      saldo -= valor * 1.02;
      System.out.println("Saque realizado");
    } else {
      saldo -= valor;
      System.out.println("Saque realizado");
    }
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

  private boolean ehDiaAniversario() {
    int dayOfMonth = getDiaAniversario(diaAniversario);
    int monthValue = getMesAniversario(diaAniversario);

    LocalDate now = LocalDate.now();
    int dayOfMonthNow = getDiaAniversario(now);
    int monthValueNow = getMesAniversario(now);

    return dayOfMonth == dayOfMonthNow && monthValue == monthValueNow;
  }

  private int getDiaAniversario(LocalDate date) {
    return date.getDayOfMonth();
  }

  private int getMesAniversario(LocalDate date) {
    return date.getMonth().getValue();
  }

  public void setTaxaJuros(double taxaJuros) {
    this.taxaJuros = taxaJuros;
  }

  public void setDiaAniversario(LocalDate diaAniversario) {
    this.diaAniversario = diaAniversario;
  }

  @Override
  public String toString() {
    return super.toString()
        + ", taxaJuros="
        + this.taxaJuros
        + ", diaAniversario="
        + getDiaAniversario(diaAniversario)
        + "/"
        + getMesAniversario(diaAniversario)
        + ", saldo="
        + this.getSaldo();
  }
}
