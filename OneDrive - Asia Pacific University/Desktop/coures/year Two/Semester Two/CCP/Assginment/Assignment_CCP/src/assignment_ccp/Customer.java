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
    // indecats if the customer assgined to the barber or not 
    boolean status = false;
    String[] typeArray = {"paciente", "impaciente"};
    int type;
    WaittingQueue q;

    public Customer(int id, WaittingQueue q) {
        this.id = id;
        this.q = q;
        type = new Random().nextInt(2);
    }

    @Override
    public void run() {
        add();
        if (type == 1) {
            try {
         
                Timer();
            } catch (Exception ex) {
                Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void add() {
        q.addCustomer(this);
    }

    public void Timer() throws Exception {
        Thread.sleep(1000 * new Random().nextInt(3)+1);
        
       
        synchronized (q.queue) {
            if (q.queue.contains(this)) {
                remove();
            }
        }
    }

    public void remove() {
        q.removeCustomer(this);
    }

}
