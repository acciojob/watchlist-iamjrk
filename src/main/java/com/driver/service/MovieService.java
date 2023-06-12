package com.driver.service;

import com.driver.model.Director;
import com.driver.model.Movie;
import com.driver.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService
{
    @Autowired
    MovieRepository movieRepository;

    //   1. Add a movie: POST /movies/add-movie
    public String addMovie(Movie movie)
    {
        String status = movieRepository.addMovie(movie);
        return status;
    }

    //   2. Add a director: POST /movies/add-director
    public String addDirector(Director director)
    {
        String status = movieRepository.addDirector(director);
        return status;
    }

    //   3.  Pair an existing movie and director: PUT /movies/add-movie-director-pair
    public String addMovieDirectorPair(String movieName, String directorName)
    {
        String status = movieRepository.addMovieDirectorPair(movieName,directorName);
        return status;
    }

    //   4.  Get Movie by movie name: GET /movies/get-movie-by-name/{name}
    public Movie getMovieByName(String name)
    {
        List<Movie> movieList= movieRepository.getMovieList();
        Movie movieObj=null;
        for(Movie movie:movieList)
        {
            if(movie.getName().equals(name))
            {
                movieObj=movie;
                break;
            }
        }
        return movieObj;
    }

    //   5.  Get Director by director name: GET /movies/get-director-by-name/{name}
    public Director getDirectorByName(String name)
    {
        List<Director> directorList= movieRepository.getDirectorList();
        Director directorObj=null;
        for(Director director:directorList)
        {
            if(director.getName().equals(name))
            {
                directorObj=director;
                //break;
            }
        }
        return directorObj;
    }

    //   6.  Get List of movies name for a given director name: GET /movies/get-movies-by-director-name/{director}
    public List<String> getMoviesByDirectorName(String name)
    {
        List<String> movieList= movieRepository.getDirectorMovieList(name);
        return movieList;
    }

    //   7.  Get List of all movies added: GET /movies/get-all-movies
    public List<String> findAllMovies()
    {
        List<String> movieList=new ArrayList<>();
        for(Movie movie: movieRepository.getMovieList())
        {
            movieList.add(movie.getName());
        }
        return movieList;
    }

    //Delete a director and its movies from the records: DELETE /movies/delete-director-by-name
    public String deleteDirectorByName(String name)
    {
        String status= movieRepository.deleteDirectorAndMovies(name);
        return status;
    }

    public String deleteAllDirectors()
    {
        List<Director> directorList=movieRepository.getDirectorList();
        for (Director director:directorList)
        {
            deleteDirectorByName(director.getName());
        }
        return "All the director and their movies has been deleted";
    }
}
