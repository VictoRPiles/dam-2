/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;

public class Grupo
{
	ArrayList<Alumno> alumnos;

	public ArrayList<Alumno> getAlumnos()
	{
		return alumnos;
	}

	public void setAlumnos(ArrayList<Alumno> alumnos)
	{
		this.alumnos = alumnos;
	}

	public Grupo(ArrayList<Alumno> alumnos)
	{
		this.alumnos = alumnos;
	}

	@Override
	public String toString()
	{
		return "Grupo{" + "alumnos=" + alumnos + '}';
	}

	public static Grupo getFakeGroup()
	{
		ArrayList<Alumno> lista = new ArrayList<>();
		lista.add(new Alumno(1, "Raul", "Garcia"));
		lista.add(new Alumno(2, "Maria", "Lopez"));
		lista.add(new Alumno(3, "Pablo", "Morales"));
		lista.add(new Alumno(4, "Raul", "Leal"));
		return new Grupo(lista);
	}
}