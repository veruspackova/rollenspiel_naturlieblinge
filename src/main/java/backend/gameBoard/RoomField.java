package backend.gameBoard;

import backend.enums.RoomType;
import backend.artifacts.items.Item;
import backend.character.Character;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;

/**
 * RoomFiled class
 * <p>
 *     roomfields are placed on the gameboard<br>
 *     store informations about what is there
 * </p>
 *
 * @author jonasmalsbenden
 */
public class RoomField {
    private Map<Integer, Integer> coordinates = new HashMap<>();
    /**
     * Room type
     * (gives information about the type of field)
     */
    private RoomType roomType;
    /**
     * Room item
     * (gives information about the items sored on this field)
     */
    private Item item;
    /**
     * Room character
     * (gives information about the characters standing on this field)
     */
    private Character character;
    /**
     * Constructor
     * (generates Roomfiled without items or Characters on it)
     */
    public RoomField(RoomType roomType, int x, int y){
        this.roomType = roomType;
        this.coordinates.put(x, y);
    }
    /**
     * Constructor
     * (generates Roomfiled without any Characters on it)
     */
//    public RoomField(RoomType roomType, Item item){
//        this.roomType = roomType;
//        this.item = item;
//    }
    /**
     * Constructor
     * (generates Roomfiled without any items on it)
     */
    public RoomField(RoomType roomType, Character character, int x, int y){
        this.roomType = roomType;
        this.character = character;
        this.coordinates.put(x, y);
    }
    /**
     * Constructor
     * (generates Roomfiled with items and characters)
     */
    public RoomField(RoomType roomType, Item item, Character character, int x, int y){
        this.roomType = roomType;
        this.item = item;
        this.character = character;
        this.coordinates.put(x, y);
    }
    //getter
    public Character getCharacter() {
        return character;
    }
    public RoomType getRoomType() {
        return roomType;
    }
    public Item getItem() {
        return item;
    }

    public Map<Integer, Integer> getCoordinates() {
        return coordinates;
    }
    //setter
    /**
     * setCharacter
     * (place a character on this field)
     */
    public void setCharacter(Character character) {
        this.character = character;
    }
    /**
     * setItem
     * (place an item on this field)
     */
    public void setItem(Item item) {
        this.item = item;
    }

    public void setCoordinates(int x, int y) {
        this.coordinates.put(x, y);
    }
}
