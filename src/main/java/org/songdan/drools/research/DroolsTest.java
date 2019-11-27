package org.songdan.drools.research;

import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.model.KieBaseModel;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.utils.KieHelper;
import org.songdan.drools.research.model.Account;
import org.songdan.drools.research.model.Cheese;
import org.songdan.drools.research.model.Person;
import org.songdan.drools.research.model.SpCheckContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Songdan
 * @create: 2019-11-06 17:23
 **/

public class DroolsTest {

    public static void main(String[] args) {
        statelessDemo();
        System.out.println("=============");
//        statefulDemo();
        accountDemo();
//        reload();
//        String ruleDrl = "package org.songdan\n" +
//                "import org.songdan.drools.research.model.Account\n" +
//                "rule \"accountBalanceAtLeast\"\n" +
//                "  when\n" +
//                "  $account : Account( balance < 100 )\n" +
//                "  then\n" +
//                "  System.out.println(\"Warning! money running out!\");\n" +
//                "end";
//        generate(ruleDrl);
    }


    private static void drtDemo() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kContainer.newKieSession("TemplatesKS");
        kieSession.insert(new Cheese("stilton", "stilton"));
        kieSession.insert(new Person("michael", 42));
        List<String> list = new ArrayList<>();
        kieSession.setGlobal("list", list);
        kieSession.fireAllRules();
        kContainer.dispose();
    }

    private static void statelessDemo() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kContainer = kieServices.getKieClasspathContainer();
        StatelessKieSession kieSession = kContainer.newStatelessKieSession("statelessSession");
        Account account = new Account(200);
        account.withdraw(150);
        kieSession.execute(account);
        account.setBalance(50);
        kieSession.execute(new Account(200));
    }

    private static void statefulDemo() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession =
                kContainer.newKieSession("sp-rules");
        List<Object> list = new ArrayList<>();
        SpCheckContext spCheckContext = new SpCheckContext();
        spCheckContext.setDiscountExpired(false);
        spCheckContext.setOpenServiceDiscountCls(true);
        spCheckContext.setSpecialDiscount(true);
        kieSession.setGlobal("list",list);
        kieSession.insert(spCheckContext);
        kieSession.fireAllRules();
        Person person = new Person("lily", 50);
        kieSession.insert(person);
        kieSession.fireAllRules();
        System.out.println(list);
    }

    private static void accountDemo() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession =
                kContainer.newKieSession("account-rules");
        Account account = new Account(200);
        account.withdraw(150);
        kieSession.insert(account);
        kieSession.fireAllRules();
    }

    public static void generate(String rule) {

        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(rule, ResourceType.DRL);
        KieSession kieSession = kieHelper.build().newKieSession();
        Account account = new Account(200);
        account.withdraw(150);
        kieSession.insert(account);
        int firedRuleNum = kieSession.fireAllRules();
        System.out.println("fired rule num is " + firedRuleNum);

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
