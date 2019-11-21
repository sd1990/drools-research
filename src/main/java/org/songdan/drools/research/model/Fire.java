package org.songdan.drools.research.model;

/**
 * @author: Songdan
 * @create: 2019-11-21 14:23
 **/
public class Fire {

    private Room room;

    public Fire(Room room) {
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
