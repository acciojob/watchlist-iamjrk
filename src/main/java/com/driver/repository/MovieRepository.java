package com.driver.repository;

import com.driver.model.Director;
import com.driver.model.Movie;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class MovieRepository
{
    HashMap<String, Movie> movieDb= new HashMap<>();
    HashMap<String,Director> directorDb= new HashMap<>();
    HashMap<String, List<String>> directorMovieDb = new HashMap<>();

    //   1. Add a movie: POST /movies/add-movie
    public String addMovie(Movie movie)
    {
        String key = movie.getName();
        movieDb.put(key,movie);
        return "Movie added successfully";
    }

    //   2. Add a director: POST /movies/add-director
    public String addDirector(Director director)
    {
        String key = director.getName();
        directorDb.put(key,director);
        return "Director added successfully";
    }

    //   3.  Pair an existing movie and director: PUT /movies/add-movie-director-pair
    public String addMovieDirectorPair(String movieName, String directorName)
    {
        if(directorMovieDb.containsKey(directorName))
        {
            directorMovieDb.get(directorName).add(movieName);
        }
        else{
            List<String> list = new ArrayList<>();
            list.add(movieName);
            directorMovieDb.put(directorName,list);
        }
        return "director and movie pair added successfully";
    }

    //get movies
    public List<Movie> getMovieList()
    {
        List<Movie>movieList=new ArrayList<>();
        for (Movie movie: movieDb.values())
        {
            movieList.add(movie);
        }
        return  movieList;
    }
    //get directors
    public List<Director> getDirectorList()
    {
        List<Director>directorList=new ArrayList<>();
        for (Director director: directorDb.values())
        {
            directorList.add(director);
        }
        return  directorList;
    }

    //   6.  Get List of movies name for a given director name: GET /movies/get-movies-by-director-name/{director}
    public List<String> getDirectorMovieList(String name)
    {
        return directorMovieDb.get(name);
    }

    //delete director & all its movies
    public String deleteDirectorAndMovies(String name)
    {
        List<String> movieList= directorMovieDb.get(name);

        //deleting movies from  movie database
        for(String movie:movieList)
        {
            movieDb.remove(movie);
        }

        //also deleting director name from director database
        directorDb.remove(name);

        //also deleting director & all its movies from DirectorMovie Database
        directorMovieDb.remove(name);

        return "Director and its movies has been deleted";
    }
}
