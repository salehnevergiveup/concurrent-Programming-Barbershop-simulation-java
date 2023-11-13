/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_ccp;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Salon extends  Thread{
    static double TotalDailyInCome  =0; 
    public static String  name;
    WaittingQueue q =  new WaittingQueue();
    ArrayList<Equipments> eq = new ArrayList<Equipments>();
    //creating the Hairdressers
    HairDresser HD1 = new HairDresser("1", q, eq);
    HairDresser HD2 = new HairDresser("2", q, eq);
    HairDresser HD3 = new HairDresser("3", q, eq);
    CustomerCreator cc = new CustomerCreator(q);
    
    public Salon() { 
        eq.add(new GoldenCombs("Golden Comb[1]" , "C"));
        eq.add(new GoldenCombs("Golden Comb[2]" , "C"));
        eq.add(new GoldenScissors("Golden Scissor[1]",  "S"));
        eq.add(new GoldenScissors("Golden Scissor[2]", "S"));
    }
    @Override
    public void run() {  
        //starting the cutomerCreator and the Hairdressers
        cc.start();
        HD1.start();
        HD2.start();
        HD3.start();
    }
    
    //countting the total income
    public static synchronized void AddIncome(double income, HairDresser hairdresser) {
        TotalDailyInCome += income;
        System.out.println(hairdresser.name + " 💈: added " + income + "$ to the shop 💰💲💰");
    }
    

}
