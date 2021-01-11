package work.iruby.blog.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import work.iruby.blog.entity.BlogUser;
import work.iruby.blog.entity.LoginMsg;
import work.iruby.blog.service.BlogUserServiceImpl;

/**
 * @author Ruby
 * @date 2021/1/11 18:59
 */
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class AuthTest {
    private MockMvc mvc;


    @Mock
    private BlogUserServiceImpl mockBlogUserService;
    @Mock
    private AuthenticationManager mockAuthenticationManager;

    @BeforeEach
    void init() {
        mvc = MockMvcBuilders.standaloneSetup(new AuthController(mockBlogUserService, mockAuthenticationManager)).build();
    }

    @Test
    void auth() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/auth")).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(mvcResult -> {
                    ObjectMapper mapper = new ObjectMapper();
                    //new TypeReference 中的对象没有空参构造器会报错
                    LoginMsg<BlogUser> loginMsg = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<LoginMsg<BlogUser>>() {
                    });
                    Assertions.assertFalse(loginMsg.getLogin());
                });
    }
}
