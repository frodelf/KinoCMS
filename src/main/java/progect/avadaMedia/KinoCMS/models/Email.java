package progect.avadaMedia.KinoCMS.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Entity
@Table(name = "email")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String subject;
    @Lob
    @Column(name = "message", columnDefinition="mediumblob")
    private byte[] message;

    public MultipartFile getMessages() throws IOException {
        try {
            ByteArrayResource resource = new ByteArrayResource(message);
            return new MockMultipartFile(subject, subject, null, resource.getInputStream());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
