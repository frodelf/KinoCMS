package progect.avadaMedia.KinoCMS.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Base64;

@Entity
@Table(name = "pages")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "text", length = 10000)
    private String description;

    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Lob
    @Column(name = "image", columnDefinition="mediumblob")
    private byte[] image;

    @Lob
    @Column(name = "image1", columnDefinition="mediumblob")
    private byte[] image1;

    @Lob
    @Column(name = "image2", columnDefinition="mediumblob")
    private byte[] image2;

    @Lob
    @Column(name = "image3", columnDefinition="mediumblob")
    private byte[] image3;

    @Lob
    @Column(name = "image4", columnDefinition="mediumblob")
    private byte[] image4;

    @OneToOne
    private SEOBlock seoBlock;

    public String getImage(){
        return Base64.getEncoder().encodeToString(image);
    }
    public String getImage1(){
        return Base64.getEncoder().encodeToString(image1);
    }
    public String getImage2(){
        return Base64.getEncoder().encodeToString(image2);
    }
    public String getImage3(){
        return Base64.getEncoder().encodeToString(image3);
    }
    public String getImage4(){
        return Base64.getEncoder().encodeToString(image4);
    }
}
