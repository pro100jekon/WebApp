package com.epam.smyrnov.orders.controller;

import com.epam.smyrnov.orders.model.dto.response.OrderResponse;
import com.epam.smyrnov.orders.service.OrdersService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkRelation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService service;

    @GetMapping
    // for admin todo
    public CollectionModel<OrderResponse> getAll(@RequestParam(required = false) @Positive Integer page) {
        val response = service.getAll(page);

        // links setup
        val links = new ArrayList<Link>();
        links.add(
                linkTo(
                        methodOn(OrdersController.class)
                                .getAll(page == null ? 1 : page))
                        .withSelfRel());
        if (response.size() == 20) {
            links.add(linkTo(
                    methodOn(OrdersController.class)
                            .getAll(page != null ? page + 1 : 1))
                    .withRel("next"));
        }
        if (page != null && page > 1) {
            links.add(
                    linkTo(
                            methodOn(OrdersController.class)
                                    .getAll(page - 1))
                            .withRel("prev"));
        }
        return CollectionModel.of(service.getAll(page), links);
    }

    @GetMapping("{id}")
    // execute if id matches with auth principal todo
    public OrderResponse getById(@PathVariable Long id) {
        return service.get(id);
    }
}
