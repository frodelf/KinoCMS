package progect.avadaMedia.KinoCMS.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Base64;

@Entity
@Table(name = "schedule")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "time")
    private String time;
    @Column(name = "name")
    private String name;
    @Column(name = "hall")
    private String hall;
    @Column(name = "price")
    private String price;
    @Column(name = "day")
    private String day;
}
