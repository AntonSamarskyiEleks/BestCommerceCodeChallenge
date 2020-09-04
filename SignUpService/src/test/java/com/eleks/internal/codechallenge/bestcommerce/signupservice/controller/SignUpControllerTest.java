package com.eleks.internal.codechallenge.bestcommerce.signupservice.controller;

import com.eleks.internal.codechallenge.bestcommerce.common.repository.MerchantRepository;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc
public class SignUpControllerTest {

    @MockBean
    private MerchantRepository merchantRepository;

    @Autowired
    private SignUpController signUpController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void postRequestWithValidMerchantReturnsOkResponseTest() throws Exception {
        MediaType textPlainUtf8 = new MediaType(MediaType.TEXT_PLAIN, StandardCharsets.UTF_8);
        String merchant = "{\"email\" : \"anton@example.com\", \"password\": \"validPass123\", " +
                "\"merchantType\": \"Software products\", \"merchantName\": \"Anton's shop\"," +
                "\"ownerName\": \"Anton\", \"address\": \"55 Some str., Kyiv, Ukraine\", " +
                "\"phoneNumber\": \"+38(000)123-45-67\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/singup")
                .content(merchant)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(textPlainUtf8));
    }

    @Test
    public void postRequestWithNotValidEmailReturnsErrorTest() throws Exception {
        String merchant = "{\"email\" : \"NOT_AN_EMAIL\", \"password\": \"validPass123\", " +
                "\"merchantType\": \"Software products\", \"merchantName\": \"Anton's shop\"," +
                "\"ownerName\": \"Anton\", \"address\": \"55 Some str., Kyiv, Ukraine\", " +
                "\"phoneNumber\": \"+38(000)123-45-67\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/singup")
                .content(merchant)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Is.is("must be a well-formed email address")))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void postRequestWithNotValidPasswordReturnsErrorTest() throws Exception {
        String merchant = "{\"email\" : \"anton@example.com\", \"password\": \"123\", " +
                "\"merchantType\": \"Software products\", \"merchantName\": \"Anton's shop\"," +
                "\"ownerName\": \"Anton\", \"address\": \"55 Some str., Kyiv, Ukraine\", " +
                "\"phoneNumber\": \"+38(000)123-45-67\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/singup")
                .content(merchant)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error", Is.is("Password is not valid (should contain at least 6 alphanumeric characters)")))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void postRequestWithoutEmailReturnsErrorTest() throws Exception {
        String merchant = "{\"password\": \"validPass123\", " +
                "\"merchantType\": \"Software products\", \"merchantName\": \"Anton's shop\"," +
                "\"ownerName\": \"Anton\", \"address\": \"55 Some str., Kyiv, Ukraine\", " +
                "\"phoneNumber\": \"+38(000)123-45-67\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/singup")
                .content(merchant)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Is.is("Email is mandatory")))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void postRequestWithoutPasswordReturnsErrorTest() throws Exception {
        String merchant = "{\"email\" : \"anton@example.com\", " +
                "\"merchantType\": \"Software products\", \"merchantName\": \"Anton's shop\"," +
                "\"ownerName\": \"Anton\", \"address\": \"55 Some str., Kyiv, Ukraine\", " +
                "\"phoneNumber\": \"+38(000)123-45-67\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/singup")
                .content(merchant)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", Is.is("Password is mandatory")))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void postRequestWithoutMerchantTypeReturnsErrorTest() throws Exception {
        String merchant = "{\"email\" : \"anton@example.com\", \"password\": \"validPass123\", " +
                "\"merchantName\": \"Anton's shop\"," +
                "\"ownerName\": \"Anton\", \"address\": \"55 Some str., Kyiv, Ukraine\", " +
                "\"phoneNumber\": \"+38(000)123-45-67\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/singup")
                .content(merchant)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.merchantType", Is.is("Merchant Type is mandatory")))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void postRequestWithoutMerchantNameReturnsErrorTest() throws Exception {
        String merchant = "{\"email\" : \"anton@example.com\", \"password\": \"validPass123\", " +
                "\"merchantType\": \"Software products\", " +
                "\"ownerName\": \"Anton\", \"address\": \"55 Some str., Kyiv, Ukraine\", " +
                "\"phoneNumber\": \"+38(000)123-45-67\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/singup")
                .content(merchant)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.merchantName", Is.is("Merchant Name is mandatory")))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void postRequestWithoutOwnerNameReturnsErrorTest() throws Exception {
        String merchant = "{\"email\" : \"anton@example.com\", \"password\": \"validPass123\", " +
                "\"merchantType\": \"Software products\", \"merchantName\": \"Anton's shop\"," +
                "\"address\": \"55 Some str., Kyiv, Ukraine\", " +
                "\"phoneNumber\": \"+38(000)123-45-67\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/singup")
                .content(merchant)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.ownerName", Is.is("Owner Name is mandatory")))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void postRequestWithoutAddressReturnsErrorTest() throws Exception {
        String merchant = "{\"email\" : \"anton@example.com\", \"password\": \"validPass123\", " +
                "\"merchantType\": \"Software products\", \"merchantName\": \"Anton's shop\"," +
                "\"ownerName\": \"Anton\", " +
                "\"phoneNumber\": \"+38(000)123-45-67\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/singup")
                .content(merchant)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.address", Is.is("Address is mandatory")))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void postRequestWithoutPhoneNumberReturnsErrorTest() throws Exception {
        String merchant = "{\"email\" : \"anton@example.com\", \"password\": \"validPass123\", " +
                "\"merchantType\": \"Software products\", \"merchantName\": \"Anton's shop\"," +
                "\"ownerName\": \"Anton\", \"address\": \"55 Some str., Kyiv, Ukraine\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/singup")
                .content(merchant)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber", Is.is("Phone Number is mandatory")))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

}
