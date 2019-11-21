package org.songdan.drools.research.model;

/**
 * @author: Songdan
 * @create: 2019-11-21 14:22
 **/
public class Sprinkler {

    private Room room;

    private boolean on;

    public Sprinkler(Room room) {
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }
}
