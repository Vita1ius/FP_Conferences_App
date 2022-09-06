import com.example.fp_epam_app.DAO.entity.User;
import com.example.fp_epam_app.DAO.mapper.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ProductServiceTest {
    @Test
    void doGetTest() throws ServletException, IOException, ClassNotFoundException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        UserDAO userDAO = mock(UserDAO.class);
        User user = mock(User.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(req.getSession()).thenReturn(session);
        when(req.getAttribute("report")).thenReturn(dispatcher);
        when(req.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(req.getAttribute("login")).thenReturn(user);
        when(userDAO.getUser("belizo")).thenReturn(user);
        when(req.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        verify(req,times(1));
    }

}
