package coetzee.hendrik.cic.repo;

import org.springframework.data.repository.CrudRepository;

import coetzee.hendrik.cic.entities.EntityEntity;

/**
 * 
 * Repository for {@link EntityEntity}'s.
 *
 */
public interface EntityRepository extends CrudRepository<EntityEntity, Long> {

}
