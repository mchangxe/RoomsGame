import java.util.HashMap;
import java.util.Map;

/**
 * Created by Museum2015 on 14/9/2016.
 */
public class RoomRoute {

    private String initialRoom;
    private Rooms[] rooms;

    public String getInitialRoom() {return initialRoom;}
    public Rooms[] getRooms() {return rooms;}


    public class Rooms{

        private String name;
        private String description;
        private Directions [] directions;


        public String getRoomName() {return name;}
        public String getRoomDescription() {return description;}
        public Directions[] getDirections() {return directions;}

    }

    public class Directions{
        private String direction;
        private String room;

        public String getDirection(){return direction;}
        public String getRoom(){return room;}
    }


}
