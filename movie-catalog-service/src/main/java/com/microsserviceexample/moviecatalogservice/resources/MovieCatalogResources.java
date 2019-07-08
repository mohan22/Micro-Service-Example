package com.microsserviceexample.moviecatalogservice.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.microsserviceexample.moviecatalogservice.models.CatalogItem;
import com.microsserviceexample.moviecatalogservice.models.Movie;
import com.microsserviceexample.moviecatalogservice.models.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResources {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private WebClient.Builder webClientBuilder;

	@RequestMapping("/{userID}")
	public List<CatalogItem> getCatalog(@PathVariable("userID") String userID) {

		UserRating ratings = restTemplate.getForObject("http://RATINGS-DATA-SERVICE/ratingsdata/users/" + userID,
				UserRating.class);

		return ratings.getRatings().stream().map(rating -> {
			// Movie movie =
			// webClientBuilder.build().get().uri("http://localhost:8082/movies/" +
			// rating.getMovieId())
			// .retrieve().bodyToMono(Movie.class).block(); -> Asynchronous
			Movie movie = restTemplate.getForObject("http://MOVIE-INFO-SERVICE/movies/" + rating.getMovieId(),
					Movie.class); // Synchronous
			return new CatalogItem(movie.getName(), "Desc", rating.getRating());
		}).collect(Collectors.toList());
	}
}
