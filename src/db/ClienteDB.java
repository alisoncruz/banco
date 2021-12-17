package db;

import models.Cliente;

import java.util.HashMap;
import java.util.Map;

public class ClienteDB {
  private Map<Integer, Cliente> clientesMap = new HashMap();

  public void addNovoCliente(Cliente cliente) {
    int id = clientesMap.size() + 1;
    cliente.setId(id);
    this.clientesMap.put(id, cliente);
  }

  public Cliente getCliente(Integer id) {
    Cliente cliente = clientesMap.get(id);
    return cliente;
  }
}
