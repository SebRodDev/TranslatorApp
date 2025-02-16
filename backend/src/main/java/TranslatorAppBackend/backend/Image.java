package TranslatorAppBackend.backend;

import jakarta.persistence.*;

@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "FilePath")
    private String filePath;

    @Column(name = "DateUploaded")
    private String dateUploaded;

    @Column(name = "FileName")
    private String fileName;

    // Using the ManyToOne annotation to state that many images are mapped
    // to one user which allows for better organization

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Image() {} // empty constructor just for organization

    public Image(String filePath, String dateUploaded, String fileName) {
        this.filePath = filePath;
        this.dateUploaded = dateUploaded;
        this.fileName = fileName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getDateUploaded() {
        return dateUploaded;
    }

    public void setDateUploaded(String date) {
        this.dateUploaded = date;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
