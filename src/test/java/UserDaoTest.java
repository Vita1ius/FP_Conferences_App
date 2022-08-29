/**
 * @author Vitaly Balushchak
 * @version 1.0
 */
import com.example.fp_epam_app.DAO.entity.User;
import com.example.fp_epam_app.DAO.mapper.UserDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserDaoTest {
    @Test
    public void UserTest(){
        User TestUser = new User();
        TestUser.setLogin("Test1");
        TestUser.setPassword("password");
        TestUser.setName("TestName");
        TestUser.setEmail("test@gmail.com");
        try{

            UserDAO userDAO = UserDAO.getInstance();
            userDAO.registerUser(TestUser);

            User user = userDAO.validate_loginAndPassword(TestUser.getLogin(),TestUser.getPassword());
            Assertions.assertNotNull(TestUser);
            Assertions.assertEquals("Test1",user.getLogin());
            Assertions.assertEquals(TestUser.getLogin(),user.getLogin());
            Assertions.assertEquals(TestUser.getEmail(),user.getEmail());
            Assertions.assertEquals(TestUser.getPassword(),user.getPassword());
            Assertions.assertEquals(0,user.getAccount());
            Assertions.assertEquals("user",user.getRole());

            userDAO.cashBack("Test1","300");
            //getUser
            User GetUser = userDAO.getUser(TestUser.getLogin());
            Assertions.assertNotNull(TestUser);
            Assertions.assertEquals(TestUser.getLogin(),GetUser.getLogin());
            Assertions.assertEquals(TestUser.getEmail(),GetUser.getEmail());
            Assertions.assertEquals(TestUser.getPassword(),GetUser.getPassword());
            Assertions.assertEquals(300,GetUser.getAccount());
            Assertions.assertEquals("user",GetUser.getRole());

        }catch(Exception e){
            e.printStackTrace();
        }

    }
    @Test
    public void DeleteUserTest() {
        try {
            User DeleteUser = UserDAO.getInstance().delete("Test1");
            User user = UserDAO.getInstance().getUser("Test1");
            String notNyl = "" + DeleteUser;
            System.out.println(notNyl);
            Assertions.assertNull(DeleteUser);
            Assertions.assertNull(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
