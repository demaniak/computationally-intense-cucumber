package coetzee.hendrik.cic;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.nio.charset.StandardCharsets;
import java.util.Random;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import coetzee.hendrik.cic.entities.CicEntity;
import coetzee.hendrik.cic.rest.CicController;
import coetzee.hendrik.cic.rest.CicRegistration;
import coetzee.hendrik.cic.services.CicService;
import lombok.extern.slf4j.Slf4j;

// @SpringBootTest
@RunWith(SpringRunner.class)
@WebMvcTest(value = CicController.class, secure = false)
@Slf4j
public class CicControllerTests {
    @Autowired
    private MockMvc mockMvc;

    private Resource postBody;

    private Random rand;

    @MockBean
    private CicService cicServMock;

    @Before
    public void before() {

        postBody = new ClassPathResource("/post-body.json");

        rand = new Random();
    }

    @Test
    public void testRegisterCic() throws Exception {
        final long randId = rand.nextLong();
        when(cicServMock.register(any(CicRegistration.class))).thenReturn(CicEntity.builder().cicId(randId).build());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/cic")
                .content(IOUtils.toString(postBody.getInputStream(), StandardCharsets.UTF_8))
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        ResponseEntity<?> asyncResult = (ResponseEntity<?>) result.getAsyncResult();

        assertEquals("http://localhost/cic/" + randId, asyncResult.getHeaders().get(HttpHeaders.LOCATION).get(0));

        assertEquals(HttpStatus.CREATED, asyncResult.getStatusCode());

    }

    @Test
    public void testGet() throws Exception {
        final Long randId = rand.nextLong();
        when(cicServMock.get(randId)).thenReturn(CicEntity.builder().cicId(randId).build());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cic/" + randId).accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        ResponseEntity<CicEntity> asyncResult = (ResponseEntity<CicEntity>) result.getAsyncResult();
        CicEntity resultEntity = asyncResult.getBody();

        assertEquals(randId, resultEntity.getCicId());

        assertEquals(HttpStatus.OK, asyncResult.getStatusCode());
    }

}
