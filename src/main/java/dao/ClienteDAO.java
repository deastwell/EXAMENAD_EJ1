package dao;

import clase.Cliente;
import utils.ObjectDBUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public static void insertarCliente(Cliente cliente) {
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
            System.out.println("Cliente '" + cliente.getNombre() + "' insertado correctamente en la base de datos.");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Error al insertar el cliente en la base de datos: " + e.getMessage());
        } finally {
            em.close();
        }
    }



    public static void listarTodo(){
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();

        TypedQuery<Cliente> q=em.createQuery("select c from Cliente c", Cliente.class);
        ArrayList<Cliente> clientes= (ArrayList<Cliente>) q.getResultList();

        for(Cliente clientito:clientes){
            System.out.println(clientito);

        }
    }

    public static void getCliente(Long id) {
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();

        Cliente cliente = em.find(Cliente.class, id);
        if (cliente != null) {
            System.out.println("Cliente encontrado:");
            System.out.println(cliente);
        } else {
            System.out.println("No se encontró ningún cliente con el ID:"+ id);
        }

        em.close();
    }

    public static void listarMejoreClientes(long cantidadVentas) {
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();

        String jpql = "SELECT c FROM Cliente c WHERE c.estado = 'Activo' AND c.numeroVentas > :cantidadVentas";
        TypedQuery<Cliente> query = em.createQuery(jpql, Cliente.class);
        query.setParameter("cantidadVentas", cantidadVentas);

        List<Cliente> clientes = query.getResultList();

        if (!clientes.isEmpty()) {
            System.out.println("Clientes activos con ventas mayores a " + cantidadVentas + ":");
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        } else {
            System.out.println("No se encontraron clientes activos con ventas mayores a " + cantidadVentas);
        }
        em.close();
    }



    public static void estadisticas() {
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();

        // 1. Total de numeroVentas entre todos los clientes
        TypedQuery<Long> totalVentasQuery = em.createQuery("SELECT SUM(c.numeroVentas) FROM Cliente c", Long.class);
        Long totalVentas = totalVentasQuery.getSingleResult();
        System.out.println("Numero total de ventas entre todos los clientes: " + totalVentas);

        // 2. Promedio de ventas de los clientes cuyo estado sea "Activo"
        TypedQuery<Double> promedioVentasQuery = em.createQuery("SELECT AVG(c.numeroVentas) FROM Cliente c WHERE c.estado = 'Activo'", Double.class);
        Double promedioVentas = promedioVentasQuery.getSingleResult();
        System.out.println("Promedio de ventas de los clientes activos: " + promedioVentas);

        // 3. Cantidad de clientes inactivos que tienen numeroVentas mayor que 0
        TypedQuery<Long> clientesInactivosQuery = em.createQuery("SELECT COUNT(c) FROM Cliente c WHERE c.estado <> 'Activo' AND c.numeroVentas > 0", Long.class);
        Long cantidadClientesInactivos = clientesInactivosQuery.getSingleResult();
        System.out.println("Cantidad de clientes inactivos con numeroVentas mayor que 0: " + cantidadClientesInactivos);

        em.close();
    }


}
