package com.dmmsoft.dbtests;

import com.dmmsoft.user.IUserService;
import com.dmmsoft.user.User;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by milo on 22.05.17.
 */


@WebServlet(urlPatterns = "/testservlet")
public class TestServlet extends HttpServlet {

    @Inject
   IEmbedingEntityService embedingEntityContainer;



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        // creating and setting values to embedable ClassA and ClassB
//        ClassA classA = new ClassA();
//        classA.setClassALevelField("value from Class A - A level field");
//
//        ClassB classB = new ClassB();
//        classB.setClassBLevelField("value from Class B - B level field");
//        classB.setClassALevelField("value from Casss B - inherited form A");
//
//        // CASE 1
//        EmbedingEntity entity = new EmbedingEntity();
//        entity.setEntityLevelField("value form Entity Case 1");
//        entity.setPropertyA(classA);
//
//        // CASE 2
//        EmbedingEntity entity2 = new EmbedingEntity();
//        entity2.setEntityLevelField("value form Entity Case 2");
//        entity2.setPropertyA(classB);  //  issue: classB own property ignored in db table
//
//
//        // CASE 3
//        EmbedingEntity entity3 = new EmbedingEntity();
//        entity3.setEntityLevelField("value form Entity Case 3");
//        entity3.setPropertyB(classB); //  issue: classB property inherited fom A ignored in db table


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        BigDecimal capital = new BigDecimal("120000");
        LocalDate BUY_DATE = LocalDate.parse("2009-09-10", formatter);
        LocalDate SELL_DATE = LocalDate.parse("2017-04-02", formatter);

        LocalInvestmentRevenueCriteria input = new LocalInvestmentRevenueCriteria(capital, BUY_DATE, SELL_DATE, "CHF", true);
        LocalInvestmentRevenueCriteria input1 = new LocalInvestmentRevenueCriteria();
        input1.setInvestedCapital(capital);
        input1.setSellDate(SELL_DATE);

        // embedingEntityContainer.addJarEntity(input);

        //List<LocalInvestmentRevenueCriteria> list =new ArrayList<>();
        //list.add(input);
        //list.add(input);
        //list.add(input);



        User user = new User();
        user.setLogin("user@super.pl");
        user.getFavourites().add(input);




        User user1 = new User();


        user1.setLogin("user1@super.pl");
        user1.getFavourites().add(input1);

        System.out.println("ilość kryteriów dodanych do usera: "+user.getFavourites().size());
        System.out.println("id dodanego criteria do usera "+user.getFavourites().get(0).getId());


        embedingEntityContainer.addUser(user);
        //embedingEntityContainer.addUser(user1);

      //  embedingEntityContainer.addEmbedingEntity(entity);
      //  embedingEntityContainer.addEmbedingEntity(entity2);
     //   embedingEntityContainer.addEmbedingEntity(entity3);

        System.out.println("reguest received...");
    }


}
