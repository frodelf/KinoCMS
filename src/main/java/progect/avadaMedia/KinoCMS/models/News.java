package progect.avadaMedia.KinoCMS.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Base64;

@Entity
@Table(name = "news")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Lob
    @Column(name = "image", columnDefinition="mediumblob")
    private byte[] image;

    private boolean isAds;

    @OneToOne
    private SEOBlock seoBlock;

    public String getImage(){
        return Base64.getEncoder().encodeToString(image);
    }

}
