import static org.junit.Assert.*;

/**
 * Created by Museum2015 on 14/9/2016.
 */
public class ValidRoomTest {
    @org.junit.Test

    public void checkValidityOfRoute() throws Exception {
        assertTrue(ValidRoom.checkValidityOfRoute().equals("MatthewsStreet"));
    }

}