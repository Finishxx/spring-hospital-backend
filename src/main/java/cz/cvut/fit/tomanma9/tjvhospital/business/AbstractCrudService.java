package cz.cvut.fit.tomanma9.tjvhospital.business;

import cz.cvut.fit.tomanma9.tjvhospital.domain.DomainEntity;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.Optional;

public abstract class AbstractCrudService <T extends DomainEntity<K>, K> {

    protected final CrudRepository<T, K> repository;

    protected AbstractCrudService(CrudRepository<T,K> repository) {this.repository = repository; }

    public T create(T entity) {
        if (repository.existsById(entity.getId()))
            throw new EntityExistsException();
        return repository.save(entity);
    }

    public Optional<T> readById(K id) { return repository.findById(id); }
    public Iterable<T> readAll() { return repository.findAll(); }

    public T update(T entity) {
        if (! repository.existsById(entity.getId()))
            throw new EntityNotFoundException();
        return repository.save(entity);
    }

    public void deleteById(K id) { repository.deleteById( id );}

}
