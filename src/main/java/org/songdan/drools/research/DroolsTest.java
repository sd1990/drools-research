package org.songdan.drools.research;

import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.model.KieBaseModel;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.songdan.drools.research.model.Account;
import org.songdan.drools.research.model.SpCheckContext;

/**
 * @author: Songdan
 * @create: 2019-11-06 17:23
 **/

public class DroolsTest {

    public static void main(String[] args) {
//        demo();
        reload();
    }

    private static void demo() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kcontainer = kieServices.getKieClasspathContainer();
        KieSession kieSession =
                kcontainer.newKieSession("ksession-rules");
        Account account = new Account(200);
        account.withdraw(150);
        kieSession.insert(account);
        kieSession.fireAllRules();
        SpCheckContext spCheckContext = new SpCheckContext();
        spCheckContext.setDiscountExpired(false);
        spCheckContext.setOpenServiceDiscountCls(true);
        spCheckContext.setSpecialDiscount(true);
        kieSession.insert(spCheckContext);
        kieSession.fireAllRules();
    }

    public static void reload() {
        // https://my.oschina.net/loujinhe/blog/3048254
        KieServices kService = KieServices.Factory.get();
        KieModuleModel kModuleModel = kService.newKieModuleModel();
        KieBaseModel kieBaseModel =
                kModuleModel.newKieBaseModel("dynamic-module");
        kieBaseModel.addPackage("rules.scene_dynamic");
        kieBaseModel.newKieSessionModel("dynamic_session");
        KieFileSystem kieFileSystem = kService.newKieFileSystem();
        kieFileSystem.writeKModuleXML(kModuleModel.toXML());

    }

}
