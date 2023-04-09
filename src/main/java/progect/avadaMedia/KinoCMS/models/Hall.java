package progect.avadaMedia.KinoCMS.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Base64;

@Entity
@Table(name = "hall")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @Lob
    @Column(name = "main-image", columnDefinition="mediumblob")
    private byte[] mainImage;

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

    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @OneToOne
    private SEOBlock seoBlock;

    public String getMainImage(){
        return Base64.getEncoder().encodeToString(mainImage);
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
