package cz.cvut.fit.tomanma9.tjvhospital.business;

import cz.cvut.fit.tomanma9.tjvhospital.domain.DomainEntity;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.EntityExistsException;
import java.util.Random;

public abstract class AbstractLongIdCrudService<T extends DomainEntity<Long>> extends AbstractCrudService<T, Long> {

    protected AbstractLongIdCrudService(CrudRepository repository) {
        super(repository);
    }

    private Random random = new Random();

    // Auto increment was just not working, this should do the job just fine
    // for the moment, but it is not pretty
    @Override
    public T create(T entity) {
        if (entity.getId() != null && repository.existsById(entity.getId()))
            throw new EntityExistsException();
        if (entity.getId() == null)
            do {
                entity.setId(random.nextLong());
            } while (repository.existsById(entity.getId()));
        return repository.save(entity);
    }
}
