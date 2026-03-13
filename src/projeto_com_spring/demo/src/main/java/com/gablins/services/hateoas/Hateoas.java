package com.gablins.services.hateoas;

import com.gablins.DTOs.ClienteVO;
import com.gablins.controllers.ClienteController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class Hateoas
{

    public static void addHateoasLinks(Long id, String cpf, String email, ClienteVO result)
    {
        result.add(linkTo(methodOn(ClienteController.class).findById(id)).withRel("findById").withType("GET"));
        result.add(linkTo(methodOn(ClienteController.class).findByCpf(cpf)).withRel("findByCpf").withType("GET"));
        result.add(linkTo(methodOn(ClienteController.class).findByEmail(email)).withRel("findByEmail").withType("GET"));
        result.add(linkTo(methodOn(ClienteController.class).delete(id)).withRel("delete").withType("DELETE"));
        result.add(linkTo(methodOn(ClienteController.class).findAll()).withRel("findAll").withType("GET"));
        result.add(linkTo(methodOn(ClienteController.class).create(result)).withRel("create").withType("POST"));
        result.add(linkTo(methodOn(ClienteController.class).update(result)).withRel("update").withType("PUT"));
    }
    public static void addHateoasLinks(ClienteVO result)
    {
        result.add(linkTo(methodOn(ClienteController.class).findById(result.getId())).withRel("findById").withType("GET"));
        result.add(linkTo(methodOn(ClienteController.class).findByCpf(result.getCpf())).withRel("findByCpf").withType("GET"));
        result.add(linkTo(methodOn(ClienteController.class).findByEmail(result.getEmail())).withRel("findByEmail").withType("GET"));
        result.add(linkTo(methodOn(ClienteController.class).delete(result.getId())).withRel("delete").withType("DELETE"));
        result.add(linkTo(methodOn(ClienteController.class).findAll()).withRel("findAll").withType("GET"));
        result.add(linkTo(methodOn(ClienteController.class).create(result)).withRel("create").withType("POST"));
        result.add(linkTo(methodOn(ClienteController.class).update(result)).withRel("update").withType("PUT"));
    }

    public static void addHateoasLinksToList(List<ClienteVO> resultList)
    {

        for (ClienteVO result : resultList) {
            addHateoasLinks(result.getId(), result.getCpf(), result.getEmail(), result);
        }

    }
}
