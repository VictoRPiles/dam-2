package org.example.entity;

import jakarta.persistence.*;

/**
 * @author victor
 */
@Entity
@Table(name = "fabricantes", schema = "examen2hiber", catalog = "")
public class FabricantesEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "CIF", nullable = false, length = 10)
	private String cif;
	@Basic
	@Column(name = "nombre", nullable = true, length = 30)
	private String nombre;
	@Basic
	@Column(name = "descrip", nullable = true, length = 50)
	private String descrip;
	@Basic
	@Column(name = "domicilio", nullable = true, length = 30)
	private String domicilio;

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescrip() {
		return descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}


	@Override
	public String toString() {
		return "FabricantesEntity{" +
				"cif='" + cif + '\'' +
				", nombre='" + nombre + '\'' +
				", descrip='" + descrip + '\'' +
				", domicilio='" + domicilio + '\'' +
				'}';
	}
}
