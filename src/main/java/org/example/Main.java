package org.example;

import clase.Cliente;
import dao.ClienteDAO;

public class Main {
    public static void main(String[] args) {

//        Cliente cliente1 = new Cliente("Pepe Cliente1");
//        ClienteDAO.insertarCliente(cliente1);
//        Cliente cliente2 = new Cliente("Juan", 8L, "Inactivo");
//        ClienteDAO.insertarCliente(cliente2);
//        Cliente cliente3 = new Cliente("Antonio", 20L, "Activo");
//        ClienteDAO.insertarCliente(cliente3);
//        Cliente cliente4 = new Cliente("Maria", 15L, "Activo");
//        ClienteDAO.insertarCliente(cliente4);
//        Cliente cliente5 = new Cliente("Jorge", 1L, "Activo");
//        ClienteDAO.insertarCliente(cliente5);

        ClienteDAO.listarMejoreClientes(5L);

    }
}