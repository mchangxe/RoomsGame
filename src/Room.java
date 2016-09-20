/**
 * Created by Museum2015 on 19/9/2016.
 */

/**
 * Slass that represents a room
 * Each object of this class has a name, a description and the avaible directions a player can go from this room
 */
public class Room{

    private String name;
    private String description;
    private Direction[] directions;


    public String getRoomName() {return name;}
    public String getRoomDescription() {return description;}
    public Direction[] getDirections() {return directions;}

}
