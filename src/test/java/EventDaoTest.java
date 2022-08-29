/**
 * @author Vitaly Balushchak
 * @version 1.0
 */
import com.example.fp_epam_app.DAO.entity.Event;
import com.example.fp_epam_app.DAO.mapper.EventDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EventDaoTest {
    @Test
    public void EventTest() throws ParseException {
        Event TestEvent = new Event();
        TestEvent.setName("Test");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        TestEvent.setTimestamp(new java.sql.Timestamp(df.parse("2022-08-29 03:32:00").getTime()));
        TestEvent.setPlace("Lviv");
        TestEvent.setAmount(100);
        try{

            EventDAO.getInstance().registerEvent(TestEvent);
            Event event = EventDAO.getInstance().getEvent("1");
            Assertions.assertNotNull(TestEvent);
            Assertions.assertEquals(TestEvent.getName(),event.getName());
            Assertions.assertEquals(TestEvent.getTimestamp(),event.getTimestamp());
            Assertions.assertEquals(TestEvent.getPlace(),event.getPlace());
            Assertions.assertEquals(TestEvent.getAmount(),event.getAmount());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void DeleteEventTest() {
        try {
            Event DeleteEvent = EventDAO.getInstance().delete("54");
            Event event = EventDAO.getInstance().getEvent("54");
            String notNyl = "" + DeleteEvent;
            System.out.println(notNyl);
            Assertions.assertNull(DeleteEvent);
            Assertions.assertNull(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
