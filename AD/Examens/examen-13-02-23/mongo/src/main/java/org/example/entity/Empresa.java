package org.example.entity;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * @author victor
 */
public class Empresa {
	@BsonId
	private final ObjectId id;
	private String cif;
	private String nombre;
	private String web;
	private List<String> servicios;
	private Contabilidad contabilidad;

	public Empresa() {
		id = new ObjectId();
	}

	public Empresa(String cif, String nombre, String web, List<String> servicios, Contabilidad contabilidad) {
		this.id = new ObjectId();
		this.cif = cif;
		this.nombre = nombre;
		this.web = web;
		this.servicios = servicios;
		this.contabilidad = contabilidad;
	}

	public ObjectId getId() {
		return id;
	}

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

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public List<String> getServicios() {
		return servicios;
	}

	public void setServicios(List<String> servicios) {
		this.servicios = servicios;
	}

	public Contabilidad getContabilidad() {
		return contabilidad;
	}

	public void setContabilidad(Contabilidad contabilidad) {
		this.contabilidad = contabilidad;
	}

	@Override
	public String toString() {
		return "Empresa{" +
				"id=" + id +
				", cif='" + cif + '\'' +
				", nombre='" + nombre + '\'' +
				", web='" + web + '\'' +
				", servicios=" + servicios +
				", contabilidad=" + contabilidad +
				'}';
	}
}
