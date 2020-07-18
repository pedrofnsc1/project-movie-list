package pedro.projects.movielist.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


/*Declaro que minha classe tera seus construtores injetados apartir do spring,
* e que minha classe java será uma entidade dentro do banco de dados**/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Movie {
    //declaro que minha id vai ser gerada de maneira automatica no BD
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    
    @NotBlank(message = "Title must be inserted")
    String title;

    @NotBlank(message = "Category must be inserted")
    String category;

    @NotBlank(message = "Director must be inserted")
    String director;

    @NotBlank(message = "You must insert an IMDb profile")
    String imdb;

    @NotNull(message = "Release year must be inserted")
    @Min(value = 1900)
    Integer yearRelease;

    Integer rateMovie;

}
