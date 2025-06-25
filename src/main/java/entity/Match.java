package entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "player1", referencedColumnName = "id")
    private Player player1;
    @ManyToOne
    @JoinColumn(name = "player2", referencedColumnName = "id")
    private Player player2;
    @ManyToOne
    @JoinColumn(name = "winner", referencedColumnName = "id")
    private Player winner;
}
