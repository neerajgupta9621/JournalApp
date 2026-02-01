package com.edigest.my.first.project.service;

import com.edigest.my.first.project.entity.User;
import com.edigest.my.first.project.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

   @BeforeEach
   void setUp(){

   }



    @Disabled
    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testSaveNewUser(User user){
        assertTrue(userService .saveNewUser(user));
    }


    @Disabled
   @ParameterizedTest
    @CsvSource({
           "1,1,2",
            "2,10,12",
            "3,3,9"
    })

    public void test(int a, int b, int expected){
        assertEquals(expected, a + b);
    }
}
