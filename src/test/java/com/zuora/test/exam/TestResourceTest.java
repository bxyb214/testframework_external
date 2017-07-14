package com.zuora.test.exam;



import com.zuora.TestAppcation;
import com.zuora.web.rest.TestResource;
import com.zuora.web.rest.vm.AccountingCode;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Test class for the AccountingCode REST controller.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestAppcation.class})
public class TestResourceTest {

    private static final String DEFAULT_TYPE = "AAAAAAAAAAB";

    @Autowired
    private WebApplicationContext wac;

    private MockMvc restMockMvc;

    private AccountingCode accountingCode;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.restMockMvc = MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();
    }

    @Before
    public void initTest() {
        accountingCode = new AccountingCode();
        accountingCode.setName(System.currentTimeMillis() + "");
        accountingCode.setType(DEFAULT_TYPE);
    }


    @Test
    public void createAccountingCodes() throws Exception {
        restMockMvc.perform(post("/apps/v1/accounting-codes")
            .accept("application/json")
            .headers(TestUtil.getHttpHeaders())
            .content(TestUtil.convertObjectToJsonBytes(accountingCode)))
//            .andDo(print());
            .andExpect(status().isOk());
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(1)))
//            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
//            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
}
