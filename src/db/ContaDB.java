package db;

import models.Conta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContaDB {

  private Map<Integer, Conta> contasMap = new HashMap<>();

  public void addNovaConta(Conta conta) {
    this.contasMap.put(conta.getNumero(), conta);
  }

  public List<Conta> getContasList() {
    List<Conta> contas = new ArrayList<>();
    for (Map.Entry<Integer, Conta> contaEntry : contasMap.entrySet()) {
      Conta conta = contaEntry.getValue();
      contas.add(conta);
    }
    return contas;
  }

  public Conta getConta(Integer numeroConta) {
    Conta conta = this.contasMap.get(numeroConta);
    return conta;
  }
}
