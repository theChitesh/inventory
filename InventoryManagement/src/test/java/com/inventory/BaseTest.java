package com.inventory;

import java.io.IOException;

import javax.servlet.Filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventory.config.InventoryManagementApplication;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@ContextConfiguration(classes = {InventoryManagementApplication.class})
@TestExecutionListeners(listeners = {WithSecurityContextTestExecutionListener.class, DirtiesContextTestExecutionListener.class})
@Rollback
public class BaseTest extends AbstractJUnit4SpringContextTests {

  protected static final String ADMIN_TOKEN =
      "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImtpZCI6Ik9EY3hPVEJDT1VWR09UVXhPREpETmpreU9VRkdRelZHUlVVNU0wRkZPRU15T0RFMk1qZ3hPQSJ9.eyJ1c2VybmFtZSI6ImlyYW5uYSIsInVzZXJpZCI6ImlyYW5uYSIsInJvbGUiOiJzeXMtYWRtaW4ifQ.MzlcyYNrdwtWiO_80YBhhK5OnN-4Fy1cvk2IDtVLX2NePERzvINjyM4KmN4NCudaW-F67GTwdWd3FI-dfH_enW7PM2tpgEnylfeY6Zl48PXlTzKFwX-3UYXQARE_zhGgzfXtwGbmPRhCVdwpTgPCBiD1l1KsnjSTVx3n4NI12lWBJTnOJlc_d0vNl4YOzsbrj_wOnkecwH9gD-z5wV13zGUbn3tin6dkaRH9dFi0Qzx5fUlDFm6minSDNZKp5JShZQ4tFXPAjnAL2KThfHQdpp3a62GD9dHbLyAl9Vim7ce1b3BHENhjF75R6aA79VrZxcViW7uF6HW0vdr2G0_2qw";
  private static final String STOCKS_URI = "/stocks";
  protected MockMvc mockMvc;
  @Autowired
  protected ObjectMapper objectMapper;
  @Autowired
  protected WebApplicationContext context;
  @Autowired
  private Filter springSecurityFilterChain;

  @Before
  public void beforeClass() throws Exception {
    mockMvc = MockMvcBuilders.webAppContextSetup(context)
        .addFilters(springSecurityFilterChain)
        .build();
  }

  protected ResultActions getAllStocks(final String token) throws Exception {
    return performGetOperation(STOCKS_URI, headersWithToken(token));
  }


  protected HttpHeaders headersWithToken(final String jwtToken) {
    HttpHeaders adminHeaders = new HttpHeaders();
    adminHeaders.add("Content-Type", MediaType.APPLICATION_JSON.toString());
    adminHeaders.add("Authorization", jwtToken);
    return adminHeaders;
  }

  private ResultActions performGetOperation(final String uri,
                                            final HttpHeaders headers,
                                            final String... uriParams) throws Exception {
    return mockMvc.perform(
        get(uri, (Object[]) uriParams)
            .headers(headers)
    );
  }

  private <T> ResultActions performPostOperation(final String uri,
                                                 final T dto,
                                                 final HttpHeaders headers,
                                                 final String... uriParams) throws Exception {
    return mockMvc.perform(
        post(uri, (Object[]) uriParams)
            .content(objectMapper.writeValueAsString(dto))
            .headers(headers)
    );
  }


  private ResultActions performDeleteOperation(final String uri,
                                               final HttpHeaders headers,
                                               final String... uriParams) throws Exception {
    return mockMvc.perform(
        delete(uri, (Object[]) uriParams)
            .headers(headers)
    );
  }

  protected <T> T extractDtoFromMockResult(final MvcResult mvcResult, final Class<T> clazz) throws IOException {
    String responseBody = mvcResult.getResponse().getContentAsString();
    return objectMapper.readValue(responseBody, clazz);
  }
}
