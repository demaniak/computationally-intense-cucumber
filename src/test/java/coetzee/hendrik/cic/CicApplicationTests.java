package coetzee.hendrik.cic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import coetzee.hendrik.cic.entities.Entity;
import coetzee.hendrik.cic.repo.EntityRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CicApplicationTests {

    @Autowired
    private EntityRepository entityRepo;

    private Entity testEntity;

    @Before
    public void before() {
        entityRepo.deleteAll();

        //@formatter:off
        testEntity = Entity.builder()
                .emailAddress(RandomStringUtils.randomAlphanumeric(10)+"@" + RandomStringUtils.randomAlphanumeric(10))
                .entityName(RandomStringUtils.random(128))              
                .build();
        //@formatter:on
    }

    @Test
    public void contextLoads() {
    }

    @Test(expected = ConstraintViolationException.class)
    public void testConstraintValidationOnEmptyEntity() {
        entityRepo.save(Entity.builder().build());
    }

    @Test
    public void testSaveOfOkEntity() {

        final Entity saved = entityRepo.save(testEntity);

        assertNotNull(saved);
        assertNotNull(saved.getEntityId());

        final Entity loaded = entityRepo.findOne(saved.getEntityId());
        assertEquals(saved, loaded);
    }

    @Test
    public void testDeletion() {
        final Entity saved = entityRepo.save(testEntity);

        assertNotNull(saved);
        assertNotNull(saved.getEntityId());
        
        entityRepo.delete(saved);
        
        final Entity loaded = entityRepo.findOne(saved.getEntityId());
        assertNull (loaded);
    }

}
