package coetzee.hendrik.cic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import coetzee.hendrik.cic.entities.CicEntity;
import coetzee.hendrik.cic.entities.EntityEntity;
import coetzee.hendrik.cic.err.CicNotFoundException;
import coetzee.hendrik.cic.repo.CicRepository;
import coetzee.hendrik.cic.repo.EntityRepository;
import coetzee.hendrik.cic.rest.CicRegistration;
import coetzee.hendrik.cic.services.CicServiceImpl;

public class CicServiceTests {
    private CicRepository cicRepoMock;

    private EntityRepository entityRepoMock;

    private CicServiceImpl cicservice;

    private EntityEntity testEntity;
    private CicEntity testCic;

    private CicRegistration cicReg;

    @Before
    public void before() {
        cicRepoMock = mock(CicRepository.class);

        entityRepoMock = mock(EntityRepository.class);

        cicservice = new CicServiceImpl();
        cicservice.setCicRepo(cicRepoMock);
        cicservice.setEntityRepo(entityRepoMock);

        //@formatter:off
        testEntity = EntityEntity.builder()
                .emailAddress("m@m")
                .entityId(getRandLong())
                .entityName(RandomStringUtils.random(80))
                .build();
        
        cicReg = CicRegistration.builder()
                .body(RandomStringUtils.random(300))
                .cicTimestamp(System.currentTimeMillis())
                .cicType(RandomStringUtils.random(64))
                .entity(testEntity)
                .sourceSystem(RandomStringUtils.random(64))
                .subject(RandomStringUtils.random(64))
                
                .build();
        
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(cicReg.getCicTimestamp());
        testCic = CicEntity.builder()
                .body(cicReg.getBody())
                .cicId(getRandLong())
                .cicTimestamp(cal)
                .cicType(cicReg.getCicType())
                .entity(cicReg.getEntity())
                .sourceSystem(cicReg.getSourceSystem())
                .subject(cicReg.getSubject())
                .build();
        //@formatter:on
    }

    private Long getRandLong() {
        return new Random().nextLong();
    }

    @Test
    public void testRegister() {
        when(entityRepoMock.findOne(testEntity.getEntityId())).thenReturn(testEntity);
        when(cicRepoMock.save(any(CicEntity.class))).thenReturn(testCic);

        CicEntity cicEnt = cicservice.register(cicReg);

        verify(entityRepoMock, times(1)).findOne(testEntity.getEntityId());
        verify(cicRepoMock, times(1)).save(any(CicEntity.class));
        assertNotNull(cicEnt);

    }

    @Test
    public void testGetWithExisting() {
        Long randLong = getRandLong();

        when(cicRepoMock.findOne(randLong)).thenReturn(testCic);

        CicEntity shouldNotBeNull = cicservice.get(randLong);
        assertEquals(testCic, shouldNotBeNull);

        verify(cicRepoMock, times(1)).findOne(randLong);
    }

    @Test(expected = CicNotFoundException.class)
    public void testGetWithNonExisting() {
        Long randLong = getRandLong();

        when(cicRepoMock.findOne(randLong)).thenReturn(null);

        CicEntity shouldBeNull = cicservice.get(randLong);
        //Should not get here btw.
        assertNull(shouldBeNull);
    }

}
