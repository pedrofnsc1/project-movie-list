package pedro.projects.movielist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pedro.projects.movielist.models.Movie;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    List<Movie> findByDirector(String director);

    List<Movie> findByYearRelease(Integer year);

}
