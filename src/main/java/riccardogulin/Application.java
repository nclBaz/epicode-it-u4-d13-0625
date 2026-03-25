package riccardogulin;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import riccardogulin.dao.BlogsDAO;
import riccardogulin.dao.CategoriesDAO;
import riccardogulin.dao.DocumentsDAO;
import riccardogulin.dao.UsersDAO;
import riccardogulin.entities.Blog;
import riccardogulin.entities.Category;
import riccardogulin.entities.User;
import riccardogulin.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public class Application {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4d13pu");

	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();

		UsersDAO ud = new UsersDAO(em);
		DocumentsDAO dd = new DocumentsDAO(em);
		BlogsDAO bd = new BlogsDAO(em);
		CategoriesDAO cd = new CategoriesDAO(em);

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
//		try {
//			User aldoFromDB = ud.findById("0b356752-640d-4bdc-a862-832eb2de3cbb");
//			System.out.println(aldoFromDB.getDocument());
//			Document aldoDoc = new Document("1234", "Italy", aldoFromDB); // <-- aldoFromDB è MANAGED quindi lo posso usare per la relazione
//			// dd.saveDocument(aldoDoc);
//		} catch (NotFoundException ex) {
//			System.out.println(ex.getMessage());
//		}
//
//		try {
//			Document aldoDocFromDB = dd.findById("c3582ef9-0967-4cd9-b477-ad0670181f0d");
//			System.out.println(aldoDocFromDB);
//		} catch (NotFoundException ex) {
//			System.out.println(ex.getMessage());
//		}

		// ******************************* 1TOMANY **********************************

//		User aldoFromDB = ud.findById("0b356752-640d-4bdc-a862-832eb2de3cbb");
//		Blog backendBlog = new Blog("Java", "Java è bellissimo", aldoFromDB);
//
//		// 	bd.saveBlog(backendBlog);
//
//		Blog blogFromDB = bd.findById("f861c5a0-0190-4f75-b2ef-6e8a8260d3aa");
//		System.out.println(blogFromDB);
//
//		aldoFromDB.getBlogs().forEach(System.out::println); // <-- Senza Bidirezionalità non potrei fare questa cosa qua


		// ******************************* MANYTOMANY **********************************
		Category cat1 = new Category("OOP");
		Category cat2 = new Category("Backend");
		Category cat3 = new Category("Frontend");
		Category cat4 = new Category("Development");
		Category cat5 = new Category("Java");

//		cd.saveCategory(cat1);
//		cd.saveCategory(cat2);
//		cd.saveCategory(cat3);
//		cd.saveCategory(cat4);
//		cd.saveCategory(cat5);

		try {
			// 1. Cerco il blog nel DB
			Blog backendBlogFromDB = bd.findById("f861c5a0-0190-4f75-b2ef-6e8a8260d3aa");
			// 2. Cerco le categorie nel DB
			Category javaCatFromDB = cd.findById("7d862b53-3964-414a-ab39-349e488978fc");
			// Category backendCatFromDB = cd.findById("ed1c2238-1c51-4850-bc5b-ac14c35f2d04");

			// 3. Creo una lista con esse
			ArrayList<Category> blogCategories = new ArrayList<>(List.of(javaCatFromDB));

			// 4. Sfrutto il setter di Blog per associare la lista di categorie ad esso
			backendBlogFromDB.setCategories(blogCategories);

			// 5. Ri-salvo il blog
			// (quello che succede dietro le quinte è che Jpa assocerà automaticamente tutti gli id di quelle categorie
			// al blog in questione)
			// bd.saveBlog(backendBlogFromDB);


//			backendBlogFromDB.getCategories().forEach(System.out::println);
			System.out.println("Tutti i blog associati alla categoria Java");
			javaCatFromDB.getBlogs().forEach(System.out::println);

		} catch (NotFoundException ex) {
			System.out.println(ex.getMessage());
		}


		em.close();
		emf.close();
	}
}
