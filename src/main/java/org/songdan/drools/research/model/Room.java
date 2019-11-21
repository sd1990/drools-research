package org.songdan.drools.research.model;

/**
 * @author: Songdan
 * @create: 2019-11-21 14:21
 **/
public class Room {

    private String name;

    public Room(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                '}';
    }
}
