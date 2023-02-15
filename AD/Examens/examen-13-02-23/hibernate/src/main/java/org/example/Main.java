package org.example;

import org.example.entity.FabricantesEntity;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Scanner;

public class Main {
	private static final Session session;
	private static final Scanner scanner;

	static {
		System.out.println("[+] Opening session...");
		session = HibernateUtil.getSessionFactory().openSession();

		scanner = new Scanner(System.in);
	}

	/**
	 * Closes the {@link #session session}.
	 */
	public static void close() {
		if (session.isOpen()) {
			System.out.println("[+] Closing session...");
			session.close();
		}
	}

	public static void main(String[] args) {
		/* ########## Exercici 1 ########## */
		FabricantesEntity[] fabricantes = getAllFabricantes();
		for (FabricantesEntity fabricante : fabricantes) {
			System.out.println(fabricante);
		}

		/* ########## Exercici 2 ########## */
		if (fabricantes.length == 0) {
			/*
			 Si no hay fabricantes, no tiene sentido buscar por CIF, para que el programa no se quede infinitamente en
			 el bucle while, lanzo IllegalStateException .
			 */
			System.out.println("[-] No hay fabricantes");
			throw new IllegalStateException("[-] No hay fabricantes");
		}

		FabricantesEntity fabricanteByCif = null;
		while (fabricanteByCif == null) {
			System.out.println("Introduce el CIF:");
			String cif = scanner.nextLine();
			fabricanteByCif = getFabricanteByCif(cif);

			if (fabricanteByCif == null) {
				System.out.println("[-] No existe el fabricante con cif " + cif);
				continue;
			}

			System.out.println(fabricanteByCif);
		}

		/* ########## Exercici 3 ########## */
		deleteFabricante(fabricanteByCif);

		close();
	}

	private static FabricantesEntity[] getAllFabricantes() {
		String hql = "from FabricantesEntity";

		Query<FabricantesEntity> query = session.createQuery(hql, FabricantesEntity.class);
		System.out.println("[@] HQL: " + query.getQueryString());

		return query
				.list()
				.toArray(new FabricantesEntity[0]);
	}

	private static FabricantesEntity getFabricanteByCif(String cif) {
		String hql = "from FabricantesEntity where cif like :cif";

		Query<FabricantesEntity> query = session
				.createQuery(hql, FabricantesEntity.class)
				.setParameter("cif", cif);
		System.out.println("[@] HQL: " + query.getQueryString());

		return query.uniqueResult();
	}

	public static void deleteFabricante(FabricantesEntity fabricante) {
		Transaction tx = session.beginTransaction();

		String hql = "delete ProductosEntity where fabricantesByCifFabric = :fabricante";
		session.remove(fabricante);

		@Deprecated
		@SuppressWarnings("rawtypes")
		Query query = session
				.createQuery(hql)
				.setParameter("fabricante", fabricante);
		System.out.println("[@] HQL: " + query.getQueryString());

		int rows = query.executeUpdate();

		System.out.println("¿Seguro que quieres borrar el fabricante? (s/n)");
		String confirm = scanner.nextLine();
		if (confirm.equalsIgnoreCase("s")) {
			System.out.println("[@] HQL: Remove fabricante " + fabricante);
			tx.commit();
			System.out.println("[@] HQL: " + rows + " rows affected");
		} else {
			tx.rollback();
		}
	}
}