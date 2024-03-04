package clase;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Cliente {
    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    private Long numeroVentas;

    private String estado;


    public Cliente(String nombre) {
        this.nombre = nombre;
        this.numeroVentas = 0L; // Valor predeterminado para el número de ventas
        this.estado = "Activo"; // Valor predeterminado para el estado
    }


    public Cliente(String nombre, Long numeroVentas, String estado) {
        this.nombre = nombre;
        this.numeroVentas = numeroVentas;
        this.estado = estado;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNumeroVentas(Long numeroVentas) {
        this.numeroVentas = numeroVentas;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Long getNumeroVentas() {
        return numeroVentas;
    }

    public String getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", numeroVentas=" + numeroVentas +
                ", estado='" + estado + '\'' +
                '}';
    }
}
