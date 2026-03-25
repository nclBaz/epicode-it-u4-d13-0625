package riccardogulin.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue // Modalità AUTO. Se il campo è UUID genererà un identificativo univoco ogni volta
	@Column(name = "user_id")
	private UUID userId;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String surname;

	// 1 TO 1 BIDIREZIONALE
	@OneToOne(mappedBy = "owner")
	// LA BIDIREZIONALITA' NON E' OBBLIGATORIA!
	// (anzi potrebbe portarmi più rogne che benefici)
	// N.B. Non verrà creata una nuova colonna nella tabella users!!!!!
	private Document document;

	protected User() {
	}

	public User(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}

	public Document getDocument() { // LA BIDIREZIONALITA' MI FORNISCE IL GETTER DI DOCUMENT
		return document;
	}

	public UUID getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	@Override
	public String toString() {
		return "User{" +
				"userId=" + userId +
				", name='" + name + '\'' +
				", surname='" + surname + '\'' +
				'}';
	}
}
