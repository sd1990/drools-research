package org.songdan.drools.research.model;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author: Songdan
 * @create: 2019-11-21 14:33
 **/
public class Hotel {

    private KieSession ksession;

    private Map<String, Room> name2room;

    private volatile boolean flag = true;

    public Hotel() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kContainer = kieServices.getKieClasspathContainer();
        ksession = kContainer.newKieSession("hotelSession");

        String[] names = new String[]{"kitchen", "bedroom", "office", "livingroom"};
        name2room = new HashMap<>();
        for(String name: names){
            Room room = new Room(name);
            name2room.put(name, room);
            ksession.insert(room);
            Sprinkler sprinkler = new Sprinkler(room);
            ksession.insert(sprinkler);
        }
        ksession.fireAllRules();
    }

    public static void main(String[] args) throws InterruptedException {
        Hotel hotel = new Hotel();
        Fire kitchenFire = new Fire(hotel.name2room.get( "kitchen") );
        Fire officeFire = new Fire(hotel.name2room.get( "office") );
        FactHandle kitchenFireHandle = hotel.ksession.insert(kitchenFire);
        FactHandle officeFireHandle = hotel.ksession.insert(officeFire);

        hotel.ksession.fireAllRules();

        //1s 灭火
        System.out.println("fire is closed now !!!");

        hotel.ksession.delete(kitchenFireHandle);
        hotel.ksession.delete(officeFireHandle);

        hotel.ksession.fireAllRules();
    }

    private void close() {
        flag = false;
    }

}
