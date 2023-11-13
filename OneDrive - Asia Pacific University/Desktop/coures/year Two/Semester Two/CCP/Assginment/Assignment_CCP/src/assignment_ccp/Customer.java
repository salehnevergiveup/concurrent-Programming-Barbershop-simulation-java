/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_ccp;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class Customer extends Thread {

    int id;
    int type;
    WaittingQueue q;

    public Customer(int id, WaittingQueue q) {
        this.id = id;
        this.q = q;
        type = new Random().nextInt(3)+1;
    }

    @Override
    public void run() {
        //Add themselves to the wattingChair or the queue
         q.addCustomer(this);
        if (type == 1) {
            try {
         
             Timer();
            } catch (Exception ex) {
                Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //cutomer will be board and live the salon if watting in the queue 
    public void Timer() throws Exception {
        Thread.sleep(1000 * new Random().nextInt(5)+1);
         q.removeCustomer(this, true);
    }

}
