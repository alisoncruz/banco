package db;

import Util.UI;
import models.Cliente;
import service.ClienteService;
import service.ContaService;

import java.util.Locale;

public class Main {

  private static ClienteService clienteService = new ClienteService();
  private static ContaService contaService = new ContaService(clienteService.getClienteDB());

  public static void main(String[] args) {

    Locale.setDefault(Locale.US);

    int opcao = 0;

    do {
      UI.exibirMenuBanco();
      opcao = UI.lerInteiro();
      processarOpcao(opcao);
    } while (opcao != 7);
  }

  public static void processarOpcao(int opcao) {
    switch (opcao) {
      case 1:
        cadastrarNovaConta();
        break;
      case 2:
        sacar();
        break;
      case 3:
        depositar();
        break;
      case 4:
        cadastrarCliente();
        break;
      case 5:
        transferirValor();
        break;
      case 6:
        mostrarMontanteContas();
        break;
      case 7:
        UI.exibirTexto("Encerrando...");
        break;
      default:
        UI.exibirTexto("Opção inválida.");
    }
  }

  public static void cadastrarNovaConta() {
    try {
      contaService.criarConta();
      UI.exibirTexto("Conta cadastrada com sucesso.");
    } catch (Exception e) {
      UI.exibirTexto(e.getMessage());
    }
  }

  public static void sacar() {
    try {
      contaService.sacar();
    } catch (Exception e) {
      UI.exibirTexto(e.getMessage());
    }
  }

  public static void depositar() {
    try {
      contaService.depositar();
    } catch (Exception e) {
      UI.exibirTexto(e.getMessage());
    }
  }

  public static void cadastrarCliente() {
    try {
      clienteService.criarCliente();
    } catch (Exception e) {
      UI.exibirTexto(e.getMessage());
    }
  }

  public static void transferirValor() {
    try {
      contaService.realizarTransferencia();
    } catch (Exception e) {
      UI.exibirTexto(e.getMessage());
    }
  }

  public static void mostrarMontanteContas() {
    Double montante = contaService.mostrarMontanteContas();
    UI.exibirTexto("Montante das Contas: " + montante);
  }
}
