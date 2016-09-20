

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.sun.org.apache.xpath.internal.operations.Bool;


/**
 * Created by Museum2015 on 14/9/2016.
 */
public class AdventureGame {

    public static void main(String args[]) throws FileNotFoundException{

        Gson gson = new Gson();
        FileReader jsonFileReader = new FileReader("/Users/Museum2015/Documents/Rooms/src/rooms.json");
        JsonReader reader = new JsonReader(jsonFileReader);
        RoomRoute roomRoute = gson.fromJson(reader, RoomRoute.class);
        Room[] possibleRooms = roomRoute.getRooms();

        boolean validMap = checkMap(possibleRooms);
        if (!validMap){
            System.out.println("The map is invalid, quiting adventure game...");
            System.exit(0);
        }else{
            System.out.println("The map is valid, let's play the game!");
        }

        //adventure game starts

        String userInput = "";
        String userInputUC = "";
        Room currentLocation = returnRoomObject(roomRoute.getInitialRoom(), possibleRooms);
        Room nextLocation;
        String newDirection;

        MAIN_GAME:
        while(!userInput.equals("EXIT")){


            System.out.println(currentLocation.getRoomDescription());
            System.out.print("From here you can go: ");

            for (int i=0; i<currentLocation.getDirections().length; i++){

                if (i!=currentLocation.getDirections().length-1) {
                    System.out.print(currentLocation.getDirections()[i].getDirection() + ",");
                }else{
                    System.out.println("or "+ currentLocation.getDirections()[i].getDirection());
                }
            }

            boolean foundNewLocation = false;

            while (!foundNewLocation) {
                Scanner userResReader = new Scanner(System.in);
                userInput = userResReader.nextLine();
                userInputUC = userInput.toUpperCase();
                String[] userInputArray = userInputUC.split("\\s+");


                if (userInputArray[0].equals("EXIT")) {
                    break MAIN_GAME;
                } else if (userInputArray[0].equals("GO")) {
                    newDirection = userInputArray[1];

                    SEARCH_FOR_NEW_LOCATION:
                    for (int i = 0; i < currentLocation.getDirections().length; i++) {
                        if (i != currentLocation.getDirections().length - 1) {
                            if (currentLocation.getDirections()[i].getDirection().toUpperCase().equals(newDirection)) {
                                currentLocation = returnRoomObject(currentLocation.getDirections()[i].getRoomName(),
                                        possibleRooms);
                                foundNewLocation = true;
                                break SEARCH_FOR_NEW_LOCATION;
                            }
                        } else {
                            if (currentLocation.getDirections()[i].getDirection().toUpperCase().equals(newDirection)) {
                                currentLocation = returnRoomObject(currentLocation.getDirections()[i].getRoomName(),
                                        possibleRooms);
                                foundNewLocation = true;
                                break SEARCH_FOR_NEW_LOCATION;
                            } else {
                                System.out.println("You cannot go that way, enter again.");

                            }
                        }

                    }

                } else {
                    System.out.println("I do not understand " + userInput);
                }
            }
        }
    }

    /**
     * This method searches all the room objects that is available in the game to find the one
     * with a specific room name and returns the room object.
     *
     * @param  roomName  The name of the room object that this method is looking to return
     * @param  listOfRooms The name of the array of room objects available in the map of the game
     * @return      The room object that has the name roomName
     */
    public static Room returnRoomObject(String roomName, Room[] listOfRooms){
        for (Room room: listOfRooms){
            if (room.getRoomName().equals(roomName)){
                return room;
            }
        }
        return null;
    }

    /**
     * This method checks if the map provided of the game is valid.
     * In order for a map to be valid,
     *
     * @param  rooms The name of the array of room objects available in the map of the game
     * @return      a boolean that indicates if the map of the game is valid.
     */
    public static boolean checkMap(Room[] rooms){

        /* create a hash map that has a room name key, linked to the value of an array containing rooms that
         * you can travel to from that room
         */
        HashMap<String,ArrayList<String>> roomMatch = new HashMap<>();
        for (Room room : rooms) {
            String roomName = room.getRoomName();
            for (Direction dir : room.getDirections()) {
                if (roomMatch.containsKey(roomName)) {
                    ArrayList<String> dirRooms = roomMatch.get(roomName);
                    dirRooms.add(dir.getRoomName());
                    roomMatch.put(roomName,dirRooms);
                } else {
                    roomMatch.put(roomName,new ArrayList<String>(){{add(dir.getRoomName());}});
                }
            }
        }

        for (Map.Entry<String,ArrayList<String>> entry : roomMatch.entrySet()) {
            String key = entry.getKey();
            ArrayList<String> value = entry.getValue();
            for (String name : value) {
                if (!roomMatch.containsKey(name)) return false;
                if (!roomMatch.get(name).contains(key)) return false;
            }
        }
        return true;
    }

}

