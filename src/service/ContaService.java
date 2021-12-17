package service;

import Util.UI;
import db.ClienteDB;
import db.ContaDB;
import models.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ContaService {

  private ContaDB contaDB = new ContaDB();
  private ClienteDB clienteDB;

  public ContaService(ClienteDB clienteDB) {
    this.clienteDB = clienteDB;
  }

  public void criarConta() {

    UI.exibirMenuCadastroConta();

    int opcaoConta = UI.lerInteiro();

    if (opcaoConta == 1) {
      this.criarContaCorrente();
    } else if (opcaoConta == 2) {
      this.criarContaSalario();
    } else if (opcaoConta == 3) {
      this.criarContaPoupanca();
    } else {
      throw new RuntimeException("Opção inválida");
    }
  }

  private Conta novaConta(TipoConta tipoConta) {

    UI.exibirTexto("Digie o número da agência: ");
    Integer numeroAgencia = UI.lerInteiro();

    UI.exibirTexto("Digite o número da conta: ");
    Integer numeroConta = UI.lerInteiro();

    UI.exibirTexto("Digite o nome do banco: ");
    String nomeBanco = UI.lerTexto();

    UI.exibirTexto("Digite o valor do saldo: ");
    Double saldo = UI.lerDecimal();

    UI.exibirTexto("Digite o id do Cliente: ");
    Integer idCliente = UI.lerInteiro();

    Cliente cliente = clienteDB.getCliente(idCliente);

    if (cliente == null) throw new RuntimeException("Cliente inexistente");

    if (tipoConta.equals(TipoConta.CORRENTE)) {
      return new ContaCorrente(numeroAgencia, numeroConta, nomeBanco, saldo, cliente);
    } else if (tipoConta.equals(TipoConta.SALARIO)) {
      return new ContaSalario(numeroAgencia, numeroConta, nomeBanco, saldo, cliente);
    } else {
      return new ContaPoupanca(numeroAgencia, numeroConta, nomeBanco, saldo, cliente);
    }
  }

  private void criarContaCorrente() {

    ContaCorrente contaCorrente = (ContaCorrente) novaConta(TipoConta.CORRENTE);

    UI.exibirTexto("Digite o valor do cheque especial: ");
    Double valorChequeEspecial = UI.lerDecimal();

    contaCorrente.setChequeEspecial(valorChequeEspecial);

    contaDB.addNovaConta(contaCorrente);
  }

  private void criarContaPoupanca() {

    ContaPoupanca contaPoupanca = (ContaPoupanca) novaConta(TipoConta.POUPANCA);

    UI.exibirTexto("Digite a data de aniversário");
    String dataString = UI.lerTexto();
    LocalDate dataAniversario =
        LocalDate.parse(dataString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    UI.exibirTexto("Digite o valor da taxa de juros");
    Double valorJuros = UI.lerDecimal();

    contaPoupanca.setTaxaJuros(valorJuros);
    contaPoupanca.setDiaAniversario(dataAniversario);

    contaDB.addNovaConta(contaPoupanca);
  }

  private void criarContaSalario() {
    ContaSalario contaSalario = (ContaSalario) novaConta(TipoConta.SALARIO);
    contaDB.addNovaConta(contaSalario);
  }

  public void listarContas() {
    List<Conta> contasList = contaDB.getContasList();

    for (Conta conta : contasList) {
      UI.exibirTexto(conta.toString());
    }
  }

  public Double mostrarMontanteContas() {
    List<Conta> contasList = contaDB.getContasList();
    Double total = 0.0;
    for (Conta conta : contasList) {
      total = total + conta.getSaldo();
    }
    return total;
  }

  public void sacar() {
    UI.exibirTexto("Digite o número da conta: ");
    Integer numeroConta = UI.lerInteiro();

    Conta conta = contaDB.getConta(numeroConta);

    if (conta == null) throw new RuntimeException("Conta não encontrada");

    exibirDadosConta(conta);

    UI.exibirTexto("Digite o valor do saque: ");
    Double valorSaque = UI.lerDecimal();

    conta.saca(valorSaque);
  }

  public void exibirDadosConta(Conta conta) {
    UI.exibirTexto("------------DADOS DA CONTA---------------");
    UI.exibirTexto("----NÚMERO AGÊNCIA: " + conta.getAgencia());
    UI.exibirTexto("----NÚMERO CONTA: " + conta.getNumero());
    UI.exibirTexto("----NOME BANCO: " + conta.getBanco());
    UI.exibirTexto("----SALDO: " + conta.getSaldo());
    UI.exibirTexto("----CLIENTE: " + conta.getCliente().getNome());
  }

  public void depositar() {
    UI.exibirTexto("Informe o número da conta: ");
    Integer numeroConta = UI.lerInteiro();

    Conta conta = contaDB.getConta(numeroConta);

    if (conta == null) throw new RuntimeException("Conta não encontrada");

    exibirDadosConta(conta);

    UI.exibirTexto("Informe o valor do depósito: ");
    Double valor = UI.lerDecimal();

    conta.deposita(valor);
  }

  public void realizarTransferencia() {
    UI.exibirTexto("Informe o número da conta de origem: ");
    Integer numeroConta = UI.lerInteiro();

    Conta contaOrigem = contaDB.getConta(numeroConta);

    if (contaOrigem == null) throw new RuntimeException("Conta não encontrada");

    exibirDadosConta(contaOrigem);

    UI.exibirTexto("Informe o número da conta destino: ");
    Integer numeroContaDestino = UI.lerInteiro();

    Conta contaDestino = contaDB.getConta(numeroContaDestino);

    if (contaDestino == null) throw new RuntimeException("Conta não encontrada");

    exibirDadosConta(contaDestino);

    UI.exibirTexto("Informe o valor da transferência: ");
    Double valorTransferencia = UI.lerDecimal();

    contaOrigem.transferir(valorTransferencia, contaDestino);
  }
}
