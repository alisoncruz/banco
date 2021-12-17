package models;

public abstract class Conta {

  private int agencia;
  private int numero;
  private String banco;
  protected double saldo;
  protected TipoConta tipoConta;
  private Cliente cliente;

  public Conta(int agencia, int numero, String banco, double saldo, Cliente cliente) {
    this.agencia = agencia;
    this.numero = numero;
    this.banco = banco;
    this.saldo = saldo;
    this.cliente = cliente;
  }

  public Conta(int agencia, int numero, String banco, double saldo, TipoConta tipoConta, Cliente cliente) {
    this.agencia = agencia;
    this.numero = numero;
    this.banco = banco;
    this.saldo = saldo;
    this.tipoConta = tipoConta;
    this.setCliente(cliente);
  }

  public int getAgencia() {
    return agencia;
  }

  public int getNumero() {
    return numero;
  }

  public String getBanco() {
    return banco;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
    cliente.setConta(this);
  }

  public abstract double getSaldo();

  public abstract void saca(double valor);

  public abstract void deposita(double valor);

  public abstract void transferir(double valor,Conta contaDestino);


  @Override
  public String toString() {
    return this.tipoConta.toString()
        + " agencia="
        + agencia
        + ", numero="
        + numero
        + ", banco="
        + banco;
  }
}
