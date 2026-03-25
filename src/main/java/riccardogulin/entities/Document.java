package riccardogulin.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "documents")
public class Document {
	@Id
	@GeneratedValue
	@Column(name = "document_id")
	private UUID documentId;

	@Column(nullable = false)
	private String country;
	@Column(name = "issue_date", nullable = false)
	private LocalDate issueDate;
	@Column(name = "expiration_date", nullable = false)
	private LocalDate expirationDate;
	@Column(nullable = false)
	private String code;

	// 1 TO 1
	@OneToOne
	@JoinColumn(name = "user_id", nullable = false, unique = true) // Personalizziamo la FK
	private User owner; // Se creo un attributo che punta ad un'altra
	// entità sono OBBLIGATO ad usare un'annotazione @OneTo...

	protected Document() {
	}

	public Document(String code, String country, User owner) {
		this.code = code;
		this.country = country;
		this.owner = owner;
		this.issueDate = LocalDate.now();
		this.expirationDate = LocalDate.now().plusYears(10);
	}

	public UUID getDocumentId() {
		return documentId;
	}

	public String getCountry() {
		return country;
	}

	public String getCode() {
		return code;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public LocalDate getIssueDate() {
		return issueDate;
	}

	public User getOwner() {
		return owner;
	}

	@Override
	public String toString() {
		return "Document{" +
				"documentId=" + documentId +
				", country='" + country + '\'' +
				", issueDate=" + issueDate +
				", expirationDate=" + expirationDate +
				", code='" + code + '\'' +
				", owner=" + owner +
				'}';
	}
}
