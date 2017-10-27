package lab.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.stereotype.Repository;

import lab.dao.CountryDao;
import lab.model.Country;

@Repository
public class CountryJpaDaoImpl extends AbstractJpaDao implements CountryDao {

	@Override
	public void save(Country country) {
//		TODO: Implement it
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();
		em.persist(country);
		transaction.commit();

		em.close();
	}

	@Override
	public List<Country> getAllCountries() {
//	TODO: Implement it
		EntityManager em = emf.createEntityManager();
		String hql = "FROM Country";
		List<Country> countries = em.createQuery(hql, Country.class).getResultList();

		em.close();

		return countries;
	}// getAllcountries()

	@Override
	public Country getCountryByName(String name) {
//		TODO: Implement it
		EntityManager em = emf.createEntityManager(); Country country = null;
		if (em != null) { country = em
				.createQuery("SELECT c FROM Country c WHERE c.name LIKE :name", Country.class).setParameter("name", name) .getSingleResult();
			em.close();
		}
		return country;


	}

}
