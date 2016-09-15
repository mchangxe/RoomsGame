

import java.io.FileNotFoundException;
import java.io.FileReader;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.sun.org.apache.xpath.internal.operations.Bool;


/**
 * Created by Museum2015 on 14/9/2016.
 */
public class ValidRoom {

    //RoomRoute.Rooms startingRoom, RoomRoute.Rooms endingRoom
    public static Boolean checkValidityOfRoute(RoomRoute.Rooms startingRoom, RoomRoute.Rooms endingRoom) throws FileNotFoundException {

        Gson gson = new Gson();
        FileReader jsonFileReader = new FileReader("/Users/Museum2015/Documents/Rooms/src/rooms.json");
        JsonReader reader = new JsonReader(jsonFileReader);
        RoomRoute roomRoute = gson.fromJson(reader, RoomRoute.class);

        RoomRoute.Rooms[] possibleRooms = roomRoute.getRooms();


        String startingRoomName = startingRoom.getRoomName();
        String endingRoomName = endingRoom.getRoomName();
        RoomRoute.Directions[] endingRoomsDirections = endingRoom.getDirections();
        RoomRoute.Directions[] startingRoomsDirections = startingRoom.getDirections();
        String startToEndDirection = "west";
        String endToStartDirection = "east";
        Boolean startingRoomExists = false;
        Boolean endingRoomExists = false;
        Boolean checkValidRoute = false;

        for (int j = 0; j < startingRoomsDirections.length; j++) {
            if (startingRoomsDirections[j].getRoom().equals(endingRoomName)) {
                startToEndDirection = startingRoomsDirections[j].getDirection();
                endingRoomExists = true;
            }
        }

        for (int i = 0; i < endingRoomsDirections.length; i++) {
            if (endingRoomsDirections[i].getRoom().equals(endingRoomName)) {
                endToStartDirection = endingRoomsDirections[i].getDirection();
                startingRoomExists = true;
            }
        }

        if (!endingRoomExists || !startingRoomExists) {
            checkValidRoute = false;
        }else{
            checkValidRoute = true;
        }

        return checkValidRoute;
    }
}
