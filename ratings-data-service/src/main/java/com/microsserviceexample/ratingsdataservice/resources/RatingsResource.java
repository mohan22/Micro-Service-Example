package com.microsserviceexample.ratingsdataservice.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microsserviceexample.ratingsdataservice.models.Rating;
import com.microsserviceexample.ratingsdataservice.models.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {

	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId, 4);
	}

	@RequestMapping("/users/{userId}")
	public UserRating getUserRating(@PathVariable("userId") String userId) {
		List<Rating> userRatings = Arrays.asList(new Rating("1234", 4), new Rating("5678", 5));
		UserRating u = new UserRating();
		u.setRatings(userRatings);
		return u;
	}
}
