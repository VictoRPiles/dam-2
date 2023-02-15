package org.example.util;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.example.entity.Empresa;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

/**
 * @author victor
 */
public interface MongoHelper {
	static CodecRegistry getPojoCodecRegistry() {
		System.out.println("[+] Creating PojoCodecRegistry....");
		return fromRegistries(
				MongoClientSettings.getDefaultCodecRegistry(),
				fromProviders(
						PojoCodecProvider.builder().automatic(true).build()
				)
		);
	}

	@SuppressWarnings("rawtypes")
	static void printPojoFindIterable(FindIterable iterable) {
		for (Object pojo : iterable) {
			System.out.println(pojo);
		}
	}

	static void dropAllEmpresas(MongoCollection<Empresa> collection) {
		/* https://stackoverflow.com/questions/31058439/how-to-delete-all-documents-in-mongodb-collection-in-java */
		BasicDBObject document = new BasicDBObject();
		collection.deleteMany(document);
		System.out.printf("[+] Drop all documents in %s%n", collection.getNamespace());
	}
}
