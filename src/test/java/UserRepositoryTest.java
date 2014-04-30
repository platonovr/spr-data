/**
 * Created by Roman on 10.04.2014.
 */


import com.spr.init.Initializer;
import com.spr.init.WebAppConfig;
import com.spr.model.User;
import com.spr.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebAppConfig.class, Initializer.class})

public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void findAll() throws SQLException {
        List<User> users = (List<User>) userRepository.findAll();
        for (User user : users) {
            assertNotNull(user);
        }
    }

    @Test
    public void findByLogin() throws SQLException {
        User user = userRepository.getUserByLogin("rom16rus@gmail.com");
        System.out.println(user.getLogin());
    }
}
