import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.Assert.*;

/**
 * Created by Museum2015 on 18/9/2016.
 */
public class AdventureGameTest {



    public AdventureGameTest() throws FileNotFoundException {
    }

    /**
     * Test cases to test if the checkMap method analyses a json adventure game map correctly
     *
     * @result the testcase should return true since this sample json is valid (provided in class)
     */
    @Test
    public void checkMapValid() throws Exception {
        Gson gson = new Gson();
        FileReader jsonFileReader = new FileReader("/Users/Museum2015/Documents/Rooms/src/rooms.json");
        JsonReader reader = new JsonReader(jsonFileReader);
        RoomRoute roomRoute = gson.fromJson(reader, RoomRoute.class);
        Room[] possibleRoomsTrue = roomRoute.getRooms();

        assertTrue(AdventureGame.checkMap(possibleRoomsTrue));
    }

    /**
     * Test cases to test if the checkMap method analyses a json adventure game map correctly.
     * Different from the previous one, the json file here is tampered as one room object is deleted.
     *
     * @result the testcase should return false since this sample json is invalid.
     */
    @Test
    public void checkMapFalse() throws Exception {
        Gson gson = new Gson();
        FileReader jsonFileReader = new FileReader("/Users/Museum2015/Documents/Rooms/src/falserooms.json");
        JsonReader reader = new JsonReader(jsonFileReader);
        RoomRoute roomRoute = gson.fromJson(reader, RoomRoute.class);
        Room[] possibleRoomsFalse = roomRoute.getRooms();

        assertFalse(AdventureGame.checkMap(possibleRoomsFalse));
    }

    /**
     * Test cases to test if the returnRoomObject function returns the correct room object.
     *
     * @result the testcase should return true since the method returns the room object with the correct name.
     */
    @Test
    public void checkReturnRoomObject1() throws Exception {
        Gson gson = new Gson();
        FileReader jsonFileReader = new FileReader("/Users/Museum2015/Documents/Rooms/src/rooms.json");
        JsonReader reader = new JsonReader(jsonFileReader);
        RoomRoute roomRoute = gson.fromJson(reader, RoomRoute.class);
        Room[] possibleRooms = roomRoute.getRooms();

        assertTrue(AdventureGame.returnRoomObject("AcmOffice", possibleRooms).getRoomName().equals("AcmOffice"));
    }

    /**
     * Test cases to test if the returnRoomObject function returns the correct room object.
     *
     * @result the testcase should return true since the method returns the room object with the correct name.
     */
    @Test
    public void checkReturnRoomObject2() throws Exception {
        Gson gson = new Gson();
        FileReader jsonFileReader = new FileReader("/Users/Museum2015/Documents/Rooms/src/rooms.json");
        JsonReader reader = new JsonReader(jsonFileReader);
        RoomRoute roomRoute = gson.fromJson(reader, RoomRoute.class);
        Room[] possibleRooms = roomRoute.getRooms();

        assertTrue(AdventureGame.returnRoomObject("SiebelEntry", possibleRooms).getRoomName().equals("SiebelEntry"));
    }

}

