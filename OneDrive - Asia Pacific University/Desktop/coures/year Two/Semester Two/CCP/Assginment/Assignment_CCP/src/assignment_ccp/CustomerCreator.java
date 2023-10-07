/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_ccp;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class CustomerCreator extends Thread {

    WaittingQueue q;
    public CustomerCreator(WaittingQueue q) {
        this.q = q;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 20; i++) {
                Customer cus = new Customer(i, q);
                cus.start();

                try {//
                    TimeUnit.SECONDS.sleep(new Random().nextInt(4));
                } catch (InterruptedException ex) {
                    Logger.getLogger(CustomerCreator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            TimeUnit.SECONDS.sleep(3);
            System.out.println("🚪 We are closing now. Thank you for choosing us today! 💇‍♀️💆‍♂️💅");


        } catch (InterruptedException ex) {
            Logger.getLogger(CustomerCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
