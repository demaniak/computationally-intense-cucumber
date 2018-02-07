package coetzee.hendrik.cic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Calendar;

import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import coetzee.hendrik.cic.entities.Cic;
import coetzee.hendrik.cic.entities.Entity;
import coetzee.hendrik.cic.repo.CicRepository;
import coetzee.hendrik.cic.repo.EntityRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CicApplicationTests {

    @Autowired
    private EntityRepository entityRepo;
    
    @Autowired
    private CicRepository cicRepo;

    private Entity testEntity;
    
    private Cic testCic;

    @Before
    public void before() {
        cicRepo.deleteAll();
        entityRepo.deleteAll();

        //@formatter:off
        testEntity = Entity.builder()
                .emailAddress(RandomStringUtils.randomAlphanumeric(10)+"@" + RandomStringUtils.randomAlphanumeric(10))
                .entityName(RandomStringUtils.random(128))              
                .build();
        
        testCic = Cic.builder()
                .body(RandomStringUtils.random(300))
                .cicTimestamp(Calendar.getInstance())
                .cicType(RandomStringUtils.random(64))
                .entity(testEntity)
                .sourceSystem(RandomStringUtils.random(64))
                .subject(RandomStringUtils.random(64))
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
    public void testEntityDeletion() {
        final Entity saved = entityRepo.save(testEntity);

        assertNotNull(saved);
        assertNotNull(saved.getEntityId());
        
        entityRepo.delete(saved);
        
        final Entity loaded = entityRepo.findOne(saved.getEntityId());
        assertNull (loaded);
    }
    
    @Test(expected = ConstraintViolationException.class)
    public void testConstraintValidationOnEmptyCic() {
        cicRepo.save(Cic.builder().build());
    }

    @Test
    public void testSaveOfOkCic() {
        
        Entity savedEnt = entityRepo.save(testEntity);

        testCic.setEntity(savedEnt);
        final Cic saved = cicRepo.save(testCic);

        assertNotNull(saved);
        assertNotNull(saved.getCicId());

        final Cic loaded = cicRepo.findOne(saved.getCicId());
        assertEquals(saved, loaded);
    }

    @Test
    public void testCicDeletion() {
        Entity savedEnt = entityRepo.save(testEntity);
        testCic.setEntity(savedEnt);
        final Cic saved = cicRepo.save(testCic);

        assertNotNull(saved);
        assertNotNull(saved.getCicId());
        
        cicRepo.delete(saved);
        
        final Cic loaded = cicRepo.findOne(saved.getCicId());
        assertNull (loaded);
    }

}
