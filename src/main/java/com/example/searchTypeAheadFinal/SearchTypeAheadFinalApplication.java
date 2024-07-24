//package com.example.searchTypeAheadFinal;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.Persistence;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//import java.util.*;
//
//@SpringBootApplication
//public class SearchTypeAheadFinalApplication {
//
//	public static void main(String[] args){
//		SpringApplication.run(SearchTypeAheadFinalApplication.class, args);
//		populateDB();
//	}
//
////	public  void run(ApplicationArguments args) throws Exception {
////		populateDB();
////	}
//
//	public  static void populateDB() {
//		EntityManagerFactory emf = Persistence
//				.createEntityManagerFactory("AccountPersistence");
//		EntityManager em = emf.createEntityManager();
//		em.getTransaction().begin();
//		List<FrequencyCount> data = generateData();
//		for (FrequencyCount frequencyCount : data) {
//			em.persist(frequencyCount);
//			// it goes into DB @Entity
//		}
//		em.getTransaction().commit();
//		em.close();
//	}
//
//	public static List<FrequencyCount> generateData() {
//		List<FrequencyCount> data = new ArrayList<>();
//		String alphabets = "abc";
//		Map<String, Integer> freqMap = new HashMap<>();
//		Random r = new Random();
//		for (int i = 0; i < 100000; i++) {
//			int length = r.nextInt(1, 7);//4
//			String query = "";
//			for (int j = 0; j < length; j++) {
//				query += alphabets.charAt(r.nextInt(0, 3));
//			}
//			if (freqMap.containsKey(query)) {
//				freqMap.put(query, freqMap.get(query)+1);
//			} else {
//				freqMap.put(query, 1);
//			}
//		}
//		for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
//			FrequencyCount frequencyCount = new FrequencyCount();
//			frequencyCount.setQuery(entry.getKey());
//			frequencyCount.setFrequency(entry.getValue());
//			data.add(frequencyCount);
//		}
//		return data;
//	}
//
//
//}
package com.example.searchTypeAheadFinal;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityManager;
import java.util.*;
import org.springframework.boot.ApplicationRunner;

@SpringBootApplication
public class SearchTypeAheadFinalApplication implements ApplicationRunner{

	@Autowired
	private EntityManagerFactory emf;

	public static void main(String[] args) {
		SpringApplication.run(SearchTypeAheadFinalApplication.class, args);
	}

	public void run(ApplicationArguments args) throws Exception {
		populateDB();
	}

	public void populateDB() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<FrequencyCount> data = generateData();
		for (FrequencyCount frequencyCount : data) {
			em.persist(frequencyCount);
		}
		em.getTransaction().commit();
		em.close();
	}

	public static List<FrequencyCount> generateData() {
		List<FrequencyCount> data = new ArrayList<>();
		String alphabets = "abc";
		Map<String, Integer> freqMap = new HashMap<>();
		Random r = new Random();
		for (int i = 0; i < 100000; i++) {
			int length = r.nextInt(1, 7);
			StringBuilder query = new StringBuilder();
			for (int j = 0; j < length; j++) {
				query.append(alphabets.charAt(r.nextInt(0, 3)));
			}
			freqMap.merge(query.toString(), 1, Integer::sum);
		}
		for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
			FrequencyCount frequencyCount = new FrequencyCount();
			frequencyCount.setQuery(entry.getKey());
			frequencyCount.setFrequency(entry.getValue());
			data.add(frequencyCount);
		}
		return data;
	}
}
