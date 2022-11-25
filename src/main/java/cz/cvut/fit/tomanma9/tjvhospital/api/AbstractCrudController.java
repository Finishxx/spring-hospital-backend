package cz.cvut.fit.tomanma9.tjvhospital.api;

import cz.cvut.fit.tomanma9.tjvhospital.business.AbstractCrudService;
import cz.cvut.fit.tomanma9.tjvhospital.domain.DomainEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.NoSuchElementException;


// typing as E for entity
public abstract class AbstractCrudController<E extends DomainEntity<ID>, ID > {

    protected AbstractCrudService<E, ID> service;

    public AbstractCrudController(AbstractCrudService<E, ID> service) {
        this.service = service;
    }

    @PostMapping
    public E create(@RequestBody E e) { // puts body of html request into e
        return service.create( e );
    }

    @GetMapping
    public Iterable<E> readAll() {
        return service.readAll();
    }

    // Why do we handle exception here when working with id and not when put/delete?
    // Because when we put/delete we must have first get-ed id :D
    @GetMapping( "/{id}")
    public E readOne(@PathVariable ID id) {
        try {
            return service.readById(id).get();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public void update(@RequestBody E e, @PathVariable ID id) {
        service.update(e);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable ID id) {
        service.deleteById(id);
    }


}
