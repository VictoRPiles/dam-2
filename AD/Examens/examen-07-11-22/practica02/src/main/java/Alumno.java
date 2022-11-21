/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class Alumno
{
	private String nombre, apellidos;
	private int NIA;

	public Alumno(int NIA, String nombre, String apellidos)
	{
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.NIA = NIA;
	}

	public String getNombre()
	{
		return nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public String getApellidos()
	{
		return apellidos;
	}

	public void setApellidos(String apellidos)
	{
		this.apellidos = apellidos;
	}

	public int getNIA()
	{
		return NIA;
	}

	public void setNIA(int NIA)
	{
		this.NIA = NIA;
	}

	@Override
	public String toString()
	{
		return "\n\tAlumno{" + "nombre=" + nombre + ", apellidos=" + apellidos + ", NIA=" + NIA + "}";
	}
}