package coetzee.hendrik.cic.repo;

import org.springframework.data.repository.CrudRepository;

import coetzee.hendrik.cic.entities.Entity;

/**
 * 
 * Repository for {@link Entity}'s.
 *
 */
public interface EntityRepository extends CrudRepository<Entity, Long> {

}
