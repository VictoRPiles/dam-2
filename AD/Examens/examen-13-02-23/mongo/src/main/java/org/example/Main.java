package org.example;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.example.entity.Contabilidad;
import org.example.entity.Empresa;
import org.example.util.MongoHelper;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

public class Main {
	public static final String DATABASE_NAME = "abastos";
	private static final String EMPRESAS_COLLECTION = "empresas";

	private static MongoClient client;
	private static MongoDatabase db;

	public static void init() {
		String uri = "mongodb://localhost:27017/";

		System.out.println("[+] Connecting to Mongo database " + uri + " ...");

		client = new MongoClient(
				new MongoClientURI(
						uri
				)
		);
		db = client
				.getDatabase(DATABASE_NAME)
				.withCodecRegistry(MongoHelper.getPojoCodecRegistry());
	}

	public static void close() {
		System.out.println("[+] Closing database connection...");
		client.close();
	}

	protected static MongoCollection<Empresa> getEmpresasCollection(String collectionName) {
		return db.getCollection(collectionName, Empresa.class);
	}

	public static void insertManyEmpresas(MongoCollection<Empresa> collection, List<Empresa> empresas) {
		System.out.println("[+] Inserting " + empresas + " ...");
		collection.insertMany(empresas);
	}

	private static List<Empresa> createEmpresas() {
		List<Empresa> empresas = new ArrayList<>();
		empresas.add(new Empresa(
				"B111",
				"Software SA",
				"www.software.sa",
				List.of("aplicaciones", "web"),
				new Contabilidad(3, 10000.0, 10000.0)
		));
		empresas.add(new Empresa(
				"B222",
				"Apps SL",
				"www.apps.com",
				List.of("aplicaciones", "móviles", "web"),
				new Contabilidad(135, 520000.0, 450000.0)
		));
		empresas.add(new Empresa(
				"B333",
				"Startup SL",
				"www.startup.com",
				List.of("aplicaciones", "loquefaçafalta"),
				new Contabilidad(10, 110000.0, 250000.0)
		));
		empresas.add(new Empresa(
				"B444",
				"Currantes SA",
				"www.currantes.org",
				List.of("móviles", "procesos", "hilos"),
				new Contabilidad(22, 58000.0, 45000.0)
		));
		return empresas;
	}

	public static FindIterable<Empresa> findEmpresasByServicioAndDomain(String servicio, String domain) {
		MongoCollection<Empresa> collection = db.getCollection(EMPRESAS_COLLECTION, Empresa.class);
		return collection.find(and(eq("servicios", servicio), regex("web", domain)));
	}

	public static FindIterable<Empresa> findEmpresasByEmpleadosMinimum(int minimum) {
		MongoCollection<Empresa> collection = db.getCollection(EMPRESAS_COLLECTION, Empresa.class);
		return collection.find(gt("contabilidad.numeroEmpleados", minimum));
	}

	public static FindIterable<Empresa> findEmpresaByMaxFacturacion() {
		MongoCollection<Empresa> collection = db.getCollection(EMPRESAS_COLLECTION, Empresa.class);
		return collection.find().sort(new Document("contabilidad.facturacion", -1)).limit(1);
	}

	public static void decreaseSpentByService(double quantity, String service) {
		MongoCollection<Empresa> collection = db.getCollection(EMPRESAS_COLLECTION, Empresa.class);
		collection.updateMany(eq("servicios", service), inc("contabilidad.gastos", -quantity));
	}

	public static void removeFieldByFacturacion(String field, double facturacion) {
		MongoCollection<Empresa> collection = db.getCollection(EMPRESAS_COLLECTION, Empresa.class);
		collection.updateMany(lt("contabilidad.facturacion", facturacion), unset("web"));
	}

	public static void addServiceByEmployeeNumber(String service, int employeeNumber) {
		MongoCollection<Empresa> collection = db.getCollection(EMPRESAS_COLLECTION, Empresa.class);
		collection.updateMany(gt("contabilidad.numeroEmpleados", employeeNumber), push("servicios", service));

	}

	public static void dropByUnsetField(String field) {
		MongoCollection<Empresa> collection = db.getCollection(EMPRESAS_COLLECTION, Empresa.class);
		collection.deleteMany(eq(field, null));
	}

	public static void main(String[] args) {
		/* ########## Exercici 2 ########## */
		init();

		/* ########## Exercici 3 ########## */
		MongoCollection<Empresa> empresasCollection = getEmpresasCollection(EMPRESAS_COLLECTION);
		List<Empresa> empresas = createEmpresas();

		insertManyEmpresas(empresasCollection, empresas);

		/* ########## Exercici 4 ########## */
		System.out.println("########## empresasByServicioAndDomain ##########");
		FindIterable<Empresa> empresasByServicioAndDomain = findEmpresasByServicioAndDomain("aplicaciones", ".com");
		MongoHelper.printPojoFindIterable(empresasByServicioAndDomain);
		System.out.println("########## empresasByEmpleadosMinimum ##########");
		FindIterable<Empresa> empresasByEmpleadosMinimum = findEmpresasByEmpleadosMinimum(5);
		MongoHelper.printPojoFindIterable(empresasByEmpleadosMinimum);
		System.out.println("########## findEmpresaByMaxFacturacion ##########");
		FindIterable<Empresa> empresaByMaxFacturacion = findEmpresaByMaxFacturacion();
		MongoHelper.printPojoFindIterable(empresaByMaxFacturacion);

		/* ########## Exercici 5 ########## */
		decreaseSpentByService(10000, "web");
		removeFieldByFacturacion("web", 60000);
		addServiceByEmployeeNumber("team building", 100);
		dropByUnsetField("web");

		/* Borrar los registros anteriores y cerrar BDD */
		MongoHelper.dropAllEmpresas(empresasCollection);
		close();
	}
}