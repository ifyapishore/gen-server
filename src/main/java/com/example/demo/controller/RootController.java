package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @Value("${myapp.api-prefix}")
    public String apiPrefix;

    @GetMapping("/")
    public RepresentationModel<?> root() {
        final var model = new RepresentationModel<>();

        model.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(StatController.class).getStats())
                .withRel("stat")
                .withHref(this.apiPrefix + "/stat"));
//        model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(StatController.class).getStats())
//                .withRel("stat"));

        // Add other links if needed
        model.add(Link.of("/profile").withRel("profile"));

        return model;
    }
}
