package org.example.entity;

/**
 * @author victor
 */
public class Contabilidad {
	private Integer numeroEmpleados;
	private Double facturacion;
	private Double gastos;

	public Contabilidad() {
	}

	public Contabilidad(Integer numeroEmpleados, Double facturacion, Double gastos) {
		this.numeroEmpleados = numeroEmpleados;
		this.facturacion = facturacion;
		this.gastos = gastos;
	}

	public Integer getNumeroEmpleados() {
		return numeroEmpleados;
	}

	public void setNumeroEmpleados(Integer numeroEmpleados) {
		this.numeroEmpleados = numeroEmpleados;
	}

	public Double getFacturacion() {
		return facturacion;
	}

	public void setFacturacion(Double facturacion) {
		this.facturacion = facturacion;
	}

	public Double getGastos() {
		return gastos;
	}

	public void setGastos(Double gastos) {
		this.gastos = gastos;
	}

	@Override
	public String toString() {
		return "Contabilidad{" +
				"nEmpleados=" + numeroEmpleados +
				", facturacion=" + facturacion +
				", gastos=" + gastos +
				'}';
	}
}
