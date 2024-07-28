package com.example.searchTypeAheadFinal;

import com.example.searchTypeAheadFinal.db.FrequencyCount;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;
import org.springframework.boot.ApplicationRunner;

@SpringBootApplication
public class SearchTypeAheadFinalApplication implements ApplicationRunner{

//	@Autowired
//	private EntityManagerFactory emf;
	//Doubt: We are using EntityManagerFactory here. Is it correct?
	//We are using Spring Data JPA, so see Spring Data JPA

	public static void main(String[] args) {
		SpringApplication.run(SearchTypeAheadFinalApplication.class, args);
	}

	public void run(ApplicationArguments args) throws Exception {
//		populateDB();
	}

	public void populateDB() {
//		EntityManager em = emf.createEntityManager();
//		em.getTransaction().begin();
//		List<FrequencyCount> data = generateData();
//		for (FrequencyCount frequencyCount : data) {
//			em.persist(frequencyCount);
//		}
//		em.getTransaction().commit();
//		em.close();
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
			String queryStr = query.toString();
			freqMap.compute(queryStr, (key, val) -> (val == null) ? 1 : val + 1);
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
