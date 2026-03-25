package riccardogulin.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import riccardogulin.entities.User;
import riccardogulin.exceptions.NotFoundException;

import java.util.UUID;

public class UsersDAO {
	private final EntityManager em;

	public UsersDAO(EntityManager em) {
		this.em = em;
	}

	public void saveUser(User newUser) {
		// TODO: Controlli di validazione...
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		em.persist(newUser);

		transaction.commit();

		System.out.println("Lo user " + newUser.getUserId() + " è stato correttamente salvato!");
	}

	public User findById(String userId) {
		User found = em.find(User.class, UUID.fromString(userId)); // Convertiamo la Stringa in UUID
		if (found == null) throw new NotFoundException(userId);
		return found;
	}
}
