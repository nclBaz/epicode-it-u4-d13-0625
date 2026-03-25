package riccardogulin;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import riccardogulin.dao.DocumentsDAO;
import riccardogulin.dao.UsersDAO;
import riccardogulin.entities.Document;
import riccardogulin.entities.User;
import riccardogulin.exceptions.NotFoundException;

public class Application {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4d13pu");

	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();

		UsersDAO ud = new UsersDAO(em);
		DocumentsDAO dd = new DocumentsDAO(em);

		User aldo = new User("Aldo", "Baglio");
		User giova = new User("Giovanni", "Storti");
		User giacomo = new User("Giacomo", "Poretti");

//		ud.saveUser(aldo);
//		ud.saveUser(giova);
//		ud.saveUser(giacomo);

		// ******************************* 1TO1 **********************************
		// Document aldoDoc = new Document("1234", "Italy", aldo); <-- NON POSSIAMO USARE OGGETTI NON MANAGED PER IMPOSTARE UNA RELAZIONE
		// aldo in questo caso è un oggetto "new", un oggetto Java "semplice", non ha niente a che fare con il DB (non ha neanche un ID!!) quindi NON MANAGED
		// Per ottenere oggetti managed o li salvo in questo momento oppure LI LEGGO DAL DB (findById)
		try {
			User aldoFromDB = ud.findById("0b356752-640d-4bdc-a862-832eb2de3cbb");
			System.out.println(aldoFromDB.getDocument());
			Document aldoDoc = new Document("1234", "Italy", aldoFromDB); // <-- aldoFromDB è MANAGED quindi lo posso usare per la relazione
			// dd.saveDocument(aldoDoc);
		} catch (NotFoundException ex) {
			System.out.println(ex.getMessage());
		}

		try {
			Document aldoDocFromDB = dd.findById("c3582ef9-0967-4cd9-b477-ad0670181f0d");
			System.out.println(aldoDocFromDB);
		} catch (NotFoundException ex) {
			System.out.println(ex.getMessage());
		}

		em.close();
		emf.close();
	}
}
