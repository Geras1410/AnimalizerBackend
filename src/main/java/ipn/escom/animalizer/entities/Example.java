package ipn.escom.animalizer.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "example")
@Data
@Table(indexes = @Index(columnList = "exid", name = "index_example", unique = true))
public class Example {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String Exid;

    @Column(nullable = false)
    private String imageName;

    @Column(nullable = false)
    private String FPred;

    @Column(nullable = false)
    private String SPred;

    @Column(nullable = false)
    private String TPred;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
