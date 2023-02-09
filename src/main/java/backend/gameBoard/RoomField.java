package backend.gameBoard;

import backend.enums.RoomType;
import backend.misc.Item;

public class RoomField {
    private RoomType roomType;
    private Item item;
    private Character character;

    public RoomField(RoomType roomType){
        this.roomType = roomType;
    }
    public RoomField(RoomType roomType, Item item){
        this.roomType = roomType;
        this.item = item;
    }
    public RoomField(RoomType roomType, Item item, Character character){
        this.roomType = roomType;
        this.item = item;
        this.character = character;
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
    //setter
    public void setCharacter(Character character) {
        this.character = character;
    }
    public void setItem(Item item) {
        this.item = item;
    }
}
