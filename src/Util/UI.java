package Util;

import java.util.Scanner;

public class UI {

  public static void exibirMenuBanco() {
    UI.pularLinha();
    UI.exibirTexto("----BANCO MENTORAMA----");
    UI.exibirTexto("----Menu de opções:");
    UI.exibirTexto("----1 - Criar conta");
    UI.exibirTexto("----2 - Sacar");
    UI.exibirTexto("----3 - Depositar");
    UI.exibirTexto("----4 - Criar cliente");
    UI.exibirTexto("----5 - Transferir valores");
    UI.exibirTexto("----6 - Mostrar montante disponível nas contas");
    UI.exibirTexto("----7 - Sair");
    UI.exibirTexto("Escolha a opção desejada: ");
  }

  public static void exibirMenuCadastroConta() {
    UI.exibirTexto("--------------------------");
    UI.exibirTexto("       CRIAR CONTA        ");
    UI.exibirTexto("--------------------------");

    UI.exibirTexto("Escolha o tipo de conta: ");
    UI.exibirTexto("----1 Conta Corrente ");
    UI.exibirTexto("----2 Conta Salário ");
    UI.exibirTexto("----3 Conta Poupança ");
    UI.exibirTexto("Digite a opção desejada:");
  }

  public static String lerTexto() {
    Scanner scanner = new Scanner(System.in);
    String texto = scanner.nextLine();
    return texto;
  }

  public static Integer lerInteiro() {
    Scanner scanner = new Scanner(System.in);
    Integer numero = scanner.nextInt();
    return numero;
  }

  public static Double lerDecimal() {
    Scanner scanner = new Scanner(System.in);
    Double numero = scanner.nextDouble();
    return numero;
  }

  public static void exibirTexto(String texto) {
    System.out.println(texto);
  }

  public static void pularLinha() {
    System.out.println();
  }
}
