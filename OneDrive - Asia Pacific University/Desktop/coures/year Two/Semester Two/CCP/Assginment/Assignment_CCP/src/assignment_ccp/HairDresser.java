/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_ccp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class HairDresser extends Thread {

    String name;
    WaittingQueue q;
    int index = -1; 
    Customer cus;
    ArrayList<Equipments> eq = new ArrayList<Equipments>();
    boolean returend = false;
    ArrayList<Equipments> ScissorAndComb = new ArrayList<Equipments>();
    static String closingMessage = "";
    static int leaving =0;
    static boolean opeaing = true;
    
    public HairDresser(String name, WaittingQueue q, ArrayList<Equipments> eq) {
        this.name = name;
        this.q = q;
        this.eq = eq;
    }
    

    @Override
    public void run() {
        while (closing("")) {
            try {
                GetCutomer();

                if (closing("")) {

                    if (getScissorsCombs()) {
                        cutHair();
                        finished();
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(HairDresser.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
    // geting the customes and for cosing the salon 
    public void GetCutomer() throws Exception {

        synchronized (q.waittingChair) {
            while (q.waittingChair.size() == 0 && cus == null) {
                synchronized (q.barberChair) {
                    if (q.getCount() == 20 && q.barberChair.size() == 0) {
                        System.out.println("The total income for the day is 💰" + Salon.TotalDailyInCome + " 💰 💲");
                        closing("F");
                        q.waittingChair.notifyAll();
                        closingMessage = "Barber 💈 " + name + " is Closing the Salon Now as The Last Barber to Finish For the Day. Thank You for Visiting! 🔒 🔒 🔒";
                        return;
                    }
                }
                System.out.println("Barber \ud83d\udc88 " + name + " go to sleep \ud83d\udca4");
                q.waittingChair.wait();
                if (!closing("")) {
                    leaving++;
                    System.out.println("Barber 💈 " + name + " has finished His job and is Leaving the Salon 🚪 🚪 🚪 🚪 🚪 🚪 🚪 🚪 🚪 🚪 🚪");
                    if(leaving == 2) System.out.println(closingMessage);
                    return;
                }
                Thread.sleep(3000);
            }

            synchronized (eq) {
                if (eq.size() >= 1) {
                    Collections.shuffle(eq);
                }
            }
            if (index == -1) {
                index = q.addTOBarberChair();
                cus = q.barberChair.get(q.barberChair.size() - 1);
                System.out.println("Barber \ud83d\udc88 " + name + " Notifed By Customer " + cus.id + " \ud83c\udf99  \ud83c\udf99  \ud83c\udf99 ");
                System.out.println("Customer " + cus.id + " Assgin to Hairdresser " + name + " \ud83d\udc88");
                System.out.println("Cutomer: " + cus.id + " Set on the chair " + (index + 1));
            }

        }
    }

    public boolean getScissorsCombs() throws InterruptedException {
        int flag = new Random().nextInt(2);
        String CorS = flag == 1 ? "C" : "S";
        int counter = 0;

        do {
            synchronized (eq) {
                for (int i = 0; i < eq.size(); i++) {
                    if (eq.get(i).type == CorS) {
                        this.ScissorAndComb.add(eq.get(i));
                        eq.remove(i);
                        break;
                    }
                }
            }
            if (counter == 0) {
                if (this.ScissorAndComb.size() > 0 && returend == false) {
                    System.out.println("Barber 💈 " + name + " take " + ScissorAndComb.get(0).name);
                }
                TimeUnit.SECONDS.sleep(3);
                synchronized (eq) {
                    if (CorS == "S" && this.ScissorAndComb.size() == 0) {
                        return false;
                    }

                    CorS = CorS == "C" ? "S" : "C";
                    if (eq.size() > 0) {
                        for (int i = 0; i < eq.size(); i++) {
                            if (eq.get(i).type == CorS) {
                                this.ScissorAndComb.add(eq.get(i));
                                eq.remove(i);
                                break;
                            }
                        }
                    }
                    
                  
                    //return the comb if can't aquire the scisores
                    if (ScissorAndComb.size() == 1 && ScissorAndComb.get(0).type.equalsIgnoreCase("C")) {
                        if (returend == false) {
                            System.out.println("Barber 💈 " + name + " returned " + ScissorAndComb.get(0).name + " 🌟🌟🌟");
                        }
                        eq.add(ScissorAndComb.get(0));
                        ScissorAndComb.remove(0);
                        returend = true;
                        return false;
                    }
                    if (this.ScissorAndComb.size() == 0) {
                        returend = true;
                        return false;
                    }
                }
                if (ScissorAndComb.size() == 1 && ScissorAndComb.get(0).type.equalsIgnoreCase("S")) {
                    CorS = "C";
                    counter++;
                }
            }

        } while (ScissorAndComb.size() == 1);
        returend = false;
        System.out.println("Barber 💈 " + name + " ✂️✂️✂️ take " + ScissorAndComb.get(0).name + " & " + ScissorAndComb.get(1).name + " 🌟🌟🌟");
        return true;
    }

    public void cutHair() throws InterruptedException {
        //
        int duration = new Random().nextInt(4)+3;
        System.out.println("Customer " + cus.id + " Picked " + duration * 10 + "$ 💰");
        System.out.println("Barber 💈 " + name + " Start cutting the Hair ✂️ of the Customer: " + cus.id + " - " + 0 + "% ✂️");
        //show the progress of the cutting hair
        for (int i = 1; i <= 4; i++) {
            TimeUnit.SECONDS.sleep(duration / 4);
            System.out.println("Barber 💈 " + name + " cutting the Hair of the Customer: " + cus.id + " - " + i * 25 + "% ✂️");
        }

        System.out.println("Barber 💈 " + name + " finished cutting the hair of the customer " + cus.id);
        System.out.println("Customer " + cus.id + " Paid: " + duration * 10 + "$ 💰");
        Salon.AddIncome(duration * 10, this);
    }

    public void finished() throws InterruptedException {
        synchronized (eq) {
            synchronized (q.barberChair) {
                eq.add(ScissorAndComb.get(0));
                eq.add(ScissorAndComb.get(1));
                System.out.println("Barber 💈 " + name + " Put back " + ScissorAndComb.get(0).name + " & " + ScissorAndComb.get(1).name + " ✂️");
                ScissorAndComb.remove(0);
                ScissorAndComb.remove(0);
                index = -1;
                q.barberChair.remove(cus);
                System.out.println("Customer " + cus.id + " Left the Salon 🚪");
                cus = null;
            }
        }
    }

    
    public static synchronized boolean closing(String s) {
        if (s.equalsIgnoreCase("f")) {
            opeaing = false;
        }

        return opeaing;
    }
}
