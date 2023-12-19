package company.bloggingplatform.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "posts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private LocalDate publicationDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    UserEntity userEntity;

    @OneToMany(mappedBy = "postEntity", cascade = CascadeType.ALL)
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private List<CommentEntity> commentEntities;

}
