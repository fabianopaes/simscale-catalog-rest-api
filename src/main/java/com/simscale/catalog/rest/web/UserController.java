package com.simscale.catalog.rest.web;

import com.simscale.catalog.rest.config.EndpointConfig;
import com.simscale.catalog.rest.domain.User;
import com.simscale.catalog.rest.domain.resource.ErrorResource;
import com.simscale.catalog.rest.utils.URIPathBinder;
import org.springframework.http.HttpStatus;

import com.simscale.catalog.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;


@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = EndpointConfig.USERS_COLLECTION, method = GET)
    @ResponseBody
    public Iterable<User> list(){
        return userService.findAll();
    }

    @RequestMapping(value = EndpointConfig.USERS_COLLECTION, method = POST)
    @ResponseBody
    public ResponseEntity<Object> create(@Valid @RequestBody User user, Errors errors) throws URISyntaxException {

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ErrorResource.badRequest(errors));
        }
        
        userService.create(user);

        return ResponseEntity
            .created(URIPathBinder.resourceLocationBuilder(EndpointConfig.USERS_SINGLE_RESOURCE,  user.getId()))
            .body(user);
    }

    @RequestMapping(value = EndpointConfig.USERS_SINGLE_RESOURCE, method = GET)
    @ResponseBody
    public ResponseEntity<Object> get(@PathVariable(value="id") Long id){

        Optional<User> user = userService.findByIdOptional(id);
        if(user.isPresent()){
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(ErrorResource.notFound("Not found an user with the given id"),
                HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = EndpointConfig.USERS_SINGLE_RESOURCE, method = PUT)
    @ResponseBody
    public ResponseEntity<Object> update(@Valid @PathVariable(value="id") Long id, @RequestBody User user, Errors errors){

        Optional<User> resource = userService.findByIdOptional(id);
        if(resource.isPresent()){
            if (errors.hasErrors()) {
                return ResponseEntity.badRequest().body(ErrorResource.badRequest(errors));
            }
            userService.update(resource.get(), user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(ErrorResource.notFound("Not found an user with the given id"),
                HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = EndpointConfig.USERS_SINGLE_RESOURCE, method = DELETE)
    @ResponseBody
    public  ResponseEntity<Object> delete(@PathVariable(value="id") Long id){

        Optional<User> user = userService.findByIdOptional(id);
        if(user.isPresent()){
            userService.delete(user.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(ErrorResource.notFound("Not found an user with the given id"),
                HttpStatus.NOT_FOUND);

    }

}
