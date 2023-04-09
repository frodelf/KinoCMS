package progect.avadaMedia.KinoCMS.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    private String surname;

    private String nick;
    @Column(name = "e-mail")
    private String email;

    private String password;

    private String sex;

    private String phone;

    private String city;
    @Column(name = "credit-cart")
    private String creditCart;

    private String language;

    private String role;
    @Column(name = "registration_date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "birthday_date")
    @Temporal(TemporalType.DATE)
    private Date birthday;

}
