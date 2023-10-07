/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package assignment_ccp;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author User
 */
public class Assignment_CCP {

    /**
     * @param args the command line arguments
     */
    public static boolean timer() throws InterruptedException {
        TimeUnit.MINUTES.sleep(5);
        return true;
    }

    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
            Salon s = new Salon();
            s.start();
            s.join();
    }

}
