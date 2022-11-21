/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class NewMain
{
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws FileNotFoundException
	{
		//No modifiques el código de la función main
		String filePath = "archivo.xml";
		Grupo grupo7J = Grupo.getFakeGroup();

		System.out.println("Grupo de alumnos para serializar:");
		System.out.println(grupo7J.toString() + "\n");

		SerializeGroup(grupo7J, filePath);

		Grupo new7J = DeserializeGroup(filePath);

		System.out.println("Grupo de alumnos deserializado:");
		System.out.println(new7J.toString() + "\n");

	}

	public static void SerializeGroup(Grupo grupo7J, String filePath) throws FileNotFoundException
	{
		//Completa el código de esta función
		if (!new File(filePath).exists()) throw new FileNotFoundException();

		XStream xstream = new XStream();
		xstream.allowTypes(new Class[]{Grupo.class, Alumno.class});

		try {
			System.out.printf("(INFO) Saving data in %s...\n", filePath);
			xstream.toXML(grupo7J, new FileOutputStream(filePath));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public static Grupo DeserializeGroup(String filePath) throws FileNotFoundException
	{
		//Completa el código de esta función
		if (!new File(filePath).exists()) throw new FileNotFoundException();

		XStream xstream = new XStream();
		xstream.allowTypes(new Class[]{Grupo.class, Alumno.class});

		Grupo deserializedGroup = (Grupo) xstream.fromXML(new File(filePath));

		System.out.printf("(INFO) Loading data from %s...\n", filePath);
		return deserializedGroup;
	}
}