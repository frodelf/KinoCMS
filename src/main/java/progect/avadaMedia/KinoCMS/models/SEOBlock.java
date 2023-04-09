package progect.avadaMedia.KinoCMS.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "seo_block")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SEOBlock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String url;
    private String title;
    private String keywords;
    private String depiction;
}
