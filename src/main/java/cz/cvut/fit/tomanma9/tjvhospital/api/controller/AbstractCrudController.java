package cz.cvut.fit.tomanma9.tjvhospital.api.controller;

import cz.cvut.fit.tomanma9.tjvhospital.business.AbstractCrudService;
import cz.cvut.fit.tomanma9.tjvhospital.domain.DomainEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.StreamSupport;


// typing as E for entity, typing D for Dto
public abstract class AbstractCrudController<E extends DomainEntity<ID>, D, ID > {

    protected AbstractCrudService<E, ID> service;
    protected Function<E, D> toDtoConverter;
    protected Function<D, E> toEntityConverter;

    public AbstractCrudController(AbstractCrudService<E, ID> service, Function<E, D> toDtoConverter, Function<D, E> toEntityConverter) {
        this.toDtoConverter = toDtoConverter;
        this.toEntityConverter = toEntityConverter;
        this.service = service;
    }

    @PostMapping
    // We receive Dto and we send out Dto, so we need to convert both
    public D create(@RequestBody D d) { // puts body of html request into e
        try {
            return toDtoConverter.apply(service.create(toEntityConverter.apply(d)));
        } catch (EntityExistsException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    // credits to demo-public for this stream
    public Iterable<D> readAll() {
        return StreamSupport.stream(service.readAll().spliterator(), false).map(toDtoConverter).toList();
    }

    // Why do we handle exception here when working with id and not when put/delete?
    // Because when we put/delete we must have first get-ed id :D
    @GetMapping( "/{id}")
    public D readOne(@PathVariable ID id) {
        try {
            return toDtoConverter.apply(service.readById(id).get()); // get throws NoSuchElementException
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public void update(@RequestBody D d, @PathVariable ID id) {
        try {
            service.update(toEntityConverter.apply(d));
        } catch (EntityNotFoundException | NoSuchElementException e ) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable ID id) {
        try {
            service.deleteById(id);
        } catch (EntityNotFoundException | NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


}
