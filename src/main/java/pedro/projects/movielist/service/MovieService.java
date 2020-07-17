package pedro.projects.movielist.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pedro.projects.movielist.models.Movie;
import pedro.projects.movielist.repositories.MovieRepository;

import java.util.List;


//declaro que vai ser do tipo service, vai oferecer o sistema de busca para o controller
@Service
public class MovieService {


    MovieRepository movieRepository;

    @Autowired
    public void setMovieRepository(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    //lista todos os filmes que existem no banco de dados
    public List<Movie> findAll(){
        return movieRepository.findAll();
    }

    //salva o filme cadastrado
    public Movie add(Movie movie){
        return movieRepository.save(movie);
    }

    //recebe o id selecionado, será usado para editar um item add
    public Movie get(Integer id){
        return movieRepository.getOne(id);
    }

    //vou usar essa função para deletar um item add, apartir do id dele
    public void delete(Integer id){
        movieRepository.deleteById(id);
    }

}
