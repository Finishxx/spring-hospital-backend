package cz.cvut.fit.tomanma9.tjvhospital.domain;

import java.io.Serializable;

public interface DomainEntity<ID> extends Serializable {

    ID getId();
}
