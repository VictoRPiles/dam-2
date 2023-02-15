package org.example.entity;

import jakarta.persistence.*;

/**
 * @author victor
 */
@Entity
@Table(name = "productos", schema = "examen2hiber", catalog = "")
public class ProductosEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "ref", nullable = false)
	private int ref;
	@Basic
	@Column(name = "nombre", nullable = true, length = 30)
	private String nombre;
	@Basic
	@Column(name = "precio", nullable = true)
	private Integer precio;
	@ManyToOne
	@JoinColumn(name = "cif_fabric", referencedColumnName = "CIF")
	private FabricantesEntity fabricantesByCifFabric;

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public FabricantesEntity getFabricantesByCifFabric() {
		return fabricantesByCifFabric;
	}

	public void setFabricantesByCifFabric(FabricantesEntity fabricantesByCifFabric) {
		this.fabricantesByCifFabric = fabricantesByCifFabric;
	}

	@Override
	public String toString() {
		return "ProductosEntity{" +
				"ref=" + ref +
				", nombre='" + nombre + '\'' +
				", precio=" + precio +
				'}';
	}
}
