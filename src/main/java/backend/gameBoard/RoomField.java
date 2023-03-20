package backend.gameBoard;

import backend.artifacts.ISearchable;
import backend.enums.RoomType;
import backend.artifacts.items.Item;
import backend.character.Character;

import java.util.ArrayList;
import java.util.List;

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
    //coordinates give the coordinates of this RoomField. index 0 is x, index 1 is y
    private ArrayList<Integer> coordinates = new ArrayList<>();
    /**
     * Room type
     * (gives information about the type of field)
     */
    private RoomType roomType;
    /**
     * Room item
     * (gives information about the items sored on this field)
     */
    private List<ISearchable> itemList = new ArrayList<>();
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
        this.coordinates.add(x);
        this.coordinates.add(y);
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
        this.coordinates.add(x);
        this.coordinates.add(y);
    }
    /**
     * Constructor
     * (generates Roomfiled with items and characters)
     */
    public RoomField(RoomType roomType, Item item, Character character, int x, int y){
        this.roomType = roomType;
        this.itemList.add(item);
        this.character = character;
        this.coordinates.add(x);
        this.coordinates.add(y);
    }
    //getter
    public Character getCharacter() {
        return character;
    }
    public RoomType getRoomType() {
        return roomType;
    }
    public List<ISearchable> getItemList() {
        return itemList;
    }

    public ArrayList<Integer> getCoordinates() {
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
    public void setItemList(List<ISearchable> itemList) {
        this.itemList = itemList;
    }

    public void addItem(ISearchable item){
        this.itemList.add(item);
    }

    public void setCoordinates(int x, int y) {
        this.coordinates.add(x);
        this.coordinates.add(y);
    }
}
