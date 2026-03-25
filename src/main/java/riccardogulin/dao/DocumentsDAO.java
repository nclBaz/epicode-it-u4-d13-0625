package riccardogulin.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import riccardogulin.entities.Document;
import riccardogulin.exceptions.NotFoundException;

import java.util.UUID;

public class DocumentsDAO {
	private final EntityManager em;

	public DocumentsDAO(EntityManager em) {
		this.em = em;
	}

	public void saveDocument(Document newDocument) {
		// TODO: Controlli di validazione...
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		em.persist(newDocument);

		transaction.commit();

		System.out.println("Il documento " + newDocument.getDocumentId() + " è stato correttamente salvato!");
	}


	public Document findById(String documentId) {
		Document found = em.find(Document.class, UUID.fromString(documentId)); // Convertiamo la Stringa in UUID
		if (found == null) throw new NotFoundException(documentId);
		return found;
	}
}
