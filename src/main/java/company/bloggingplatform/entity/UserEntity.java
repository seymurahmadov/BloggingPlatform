package company.bloggingplatform.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import company.bloggingplatform.enumuration.Role;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "blog_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String username;

    private String mail;

    @JsonIgnore
    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;


//    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
////    @JsonIgnore
//    @JsonIgnoreProperties({"hibernateLazyInitializer"})
//    private List<PostEntity> postEntities;




}
