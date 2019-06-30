package com.microsserviceexample.moviecatalogservice.resources;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microsserviceexample.moviecatalogservice.models.CatalogItem;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResources {

	@RequestMapping("/{userID}")
	public List<CatalogItem> getCatalog(@PathVariable("userID") String userID) {
		return Collections.singletonList(new CatalogItem("Transformers", "test", 4));
	}
}
