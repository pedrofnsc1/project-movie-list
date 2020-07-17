package pedro.projects.movielist.controller;

import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pedro.projects.movielist.models.Movie;
import pedro.projects.movielist.service.MovieService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class MovieController {

    MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }
    @RequestMapping("/")
    public String getHome(Model model, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        Cookie cookie[] = request.getCookies();

            for (int i = 0; i < cookie.length; i++) {
                if (cookie[i].getName().equals("accessTime")) {
                    List<Movie> movieList = movieService.findAll();
                    model.addAttribute("movieList", movieList);
                    model.addAttribute("cookieTime", cookie[i].getValue());
                    return "index";
                }
            }
        return "redirect:/new-cookie";
    }
    @RequestMapping("/insert-movie")
    public String getPageNewMovie(Model model){
        var movie = new Movie();
        model.addAttribute("movie", movie);
        return "insert-movie";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView saveMovie(@ModelAttribute @Valid Movie movie, Errors errors, RedirectAttributes attributes){

        if(errors.hasErrors()){
            ModelAndView mvInsert = new ModelAndView("insert-movie");
            //attributes.addFlashAttribute("message", "Verify all fields");
            return mvInsert;
        }else{
            ModelAndView mv = new ModelAndView("redirect:/");
           attributes.addFlashAttribute("message", "Movie added at your list");
            movieService.add(movie);
            return mv;
        }
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView editMovie(@PathVariable(name = "id") Integer id){

        ModelAndView mv = new ModelAndView("edit");
        var movie = movieService.get(id);
        mv.addObject("movie", movie);
        return mv;
    }

    @RequestMapping("/delete/{id}")
    public String deleteMovie(@PathVariable(name = "id") Integer id){
        movieService.delete(id);
        return "redirect:/";
    }

    @RequestMapping("/new-cookie")
    public String newCookie(HttpServletRequest request ,HttpServletResponse response){

        HttpSession session = request.getSession();
        Cookie cookie = new Cookie("accessTime", "redirectedToIndex");

        Date access = new Date(session.getLastAccessedTime());
        DateFormat format_date = new SimpleDateFormat("EEE-dd-MM-yyyy|hh:mm:ss");
        String dateFormatString = format_date.format(access);
        cookie.setValue(dateFormatString);
        cookie.setMaxAge(1800);
        response.addCookie(cookie);

        return "redirect:/";
    }
}
