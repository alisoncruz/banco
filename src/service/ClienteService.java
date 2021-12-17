package service;

import Util.UI;
import db.ClienteDB;
import models.Cliente;

public class ClienteService {

  private ClienteDB clienteDB = new ClienteDB();

  public void criarCliente() {
    UI.exibirTexto("Digite o nome do Cliente: ");
    String nome = UI.lerTexto();

    UI.exibirTexto("Digite o cpf do Cliente: ");
    String cpf = UI.lerTexto();

    Cliente cliente = new Cliente(nome, cpf);

    clienteDB.addNovoCliente(cliente);

    UI.exibirTexto("Cliente cadastrado com sucesso");
  }

  public ClienteDB getClienteDB() {
    return clienteDB;
  }
}
