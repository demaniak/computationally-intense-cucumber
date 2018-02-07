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

import coetzee.hendrik.cic.entities.CicEntity;
import coetzee.hendrik.cic.entities.EntityEntity;
import coetzee.hendrik.cic.repo.CicRepository;
import coetzee.hendrik.cic.repo.EntityRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CicApplicationTests {

    @Autowired
    private EntityRepository entityRepo;
    
    @Autowired
    private CicRepository cicRepo;

    private EntityEntity testEntity;
    
    private CicEntity testCic;

    @Before
    public void before() {
        cicRepo.deleteAll();
        entityRepo.deleteAll();

        //@formatter:off
        testEntity = EntityEntity.builder()
                .emailAddress(RandomStringUtils.randomAlphanumeric(10)+"@" + RandomStringUtils.randomAlphanumeric(10))
                .entityName(RandomStringUtils.random(128))              
                .build();
        
        testCic = CicEntity.builder()
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
        entityRepo.save(EntityEntity.builder().build());
    }

    @Test
    public void testSaveOfOkEntity() {

        final EntityEntity saved = entityRepo.save(testEntity);

        assertNotNull(saved);
        assertNotNull(saved.getEntityId());

        final EntityEntity loaded = entityRepo.findOne(saved.getEntityId());
        assertEquals(saved, loaded);
    }

    @Test
    public void testEntityDeletion() {
        final EntityEntity saved = entityRepo.save(testEntity);

        assertNotNull(saved);
        assertNotNull(saved.getEntityId());
        
        entityRepo.delete(saved);
        
        final EntityEntity loaded = entityRepo.findOne(saved.getEntityId());
        assertNull (loaded);
    }
    
    @Test(expected = ConstraintViolationException.class)
    public void testConstraintValidationOnEmptyCic() {
        cicRepo.save(CicEntity.builder().build());
    }

    @Test
    public void testSaveOfOkCic() {
        
        EntityEntity savedEnt = entityRepo.save(testEntity);

        testCic.setEntity(savedEnt);
        final CicEntity saved = cicRepo.save(testCic);

        assertNotNull(saved);
        assertNotNull(saved.getCicId());

        final CicEntity loaded = cicRepo.findOne(saved.getCicId());
        assertEquals(saved, loaded);
    }

    @Test
    public void testCicDeletion() {
        EntityEntity savedEnt = entityRepo.save(testEntity);
        testCic.setEntity(savedEnt);
        final CicEntity saved = cicRepo.save(testCic);

        assertNotNull(saved);
        assertNotNull(saved.getCicId());
        
        cicRepo.delete(saved);
        
        final CicEntity loaded = cicRepo.findOne(saved.getCicId());
        assertNull (loaded);
    }

}
