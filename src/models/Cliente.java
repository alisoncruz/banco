package models;

public class Cliente {

  private Integer id;
  private String nome;
  private String cpf;
  private Conta conta;

  public Cliente() {}

  public Cliente(String nome, String cpf) {
    this.nome = nome;
    this.cpf = cpf;
  }

  public String getNome() {
    return nome;
  }

  public String getCpf() {
    return cpf;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Conta getConta() {
    return conta;
  }

  public void setConta(Conta conta) {
    this.conta = conta;
  }

  @Override
  public String toString() {
    return "Cliente{" + "id=" + id + ", nome='" + nome + '\'' + ", cpf='" + cpf + '\'' + '}';
  }
}
