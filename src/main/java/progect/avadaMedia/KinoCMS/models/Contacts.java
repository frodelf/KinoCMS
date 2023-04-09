package progect.avadaMedia.KinoCMS.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Base64;

@Entity
@Table(name = "contacts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contacts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "address", columnDefinition = "text")
    private String address;
    private double x;
    private double y;
    @Lob
    @Column(name = "image", columnDefinition="mediumblob")
    private byte[] image;

    public String getImage(){
        return Base64.getEncoder().encodeToString(image);
    }
}