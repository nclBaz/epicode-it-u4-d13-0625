package riccardogulin.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import riccardogulin.entities.Category;
import riccardogulin.exceptions.NotFoundException;

import java.util.UUID;

public class CategoriesDAO {

	private final EntityManager em;

	public CategoriesDAO(EntityManager em) {
		this.em = em;
	}

	public void saveCategory(Category newCategory) {
		// TODO: Controlli di validazione...
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		em.persist(newCategory);

		transaction.commit();

		System.out.println("La categoria " + newCategory.getCategoryId() + " è stata correttamente salvato!");
	}

	public Category findById(String categoryId) {
		Category found = em.find(Category.class, UUID.fromString(categoryId)); // Convertiamo la Stringa in UUID
		if (found == null) throw new NotFoundException(categoryId);
		return found;
	}
}
