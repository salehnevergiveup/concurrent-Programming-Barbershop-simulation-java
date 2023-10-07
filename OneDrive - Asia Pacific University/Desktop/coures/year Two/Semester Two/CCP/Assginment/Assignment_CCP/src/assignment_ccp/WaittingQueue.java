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
public class WaittingQueue {

    ArrayList<Customer> waittingChair = new ArrayList<Customer>();
    ArrayList<Customer> queue = new ArrayList<Customer>();
    ArrayList<Customer> barberChair = new ArrayList<Customer>();
    static int counter = 0;

    public void addCustomer(Customer cus) {
        synchronized (waittingChair) {
            synchronized (queue) {

                if (waittingChair.size() < 5) {

                    System.out.println("Customer " + cus.id + " is sitting on the Waiting Chair ♿♿♿");

                    waittingChair.add(cus);
                    if (waittingChair.size() == 1) {
                        waittingChair.notify();
                    }
                } else if (queue.size() < 5) {

                    System.out.println("Customer " + cus.id + " is standing in the Waiting Queue....🚶‍♂️🚶‍♂️🚶‍♂️");
                    queue.add(cus);
                } else {
                    System.out.println("The Salon is packed. Customer " + cus.id + " left 🚪🚪🚪");
                }
                counter++;
            }
        }
    }

    public void removeCustomer(Customer cus) {
        synchronized (waittingChair) {

            synchronized (queue) {

                if (waittingChair.size() <= 0) {
                    System.out.println("The Shop is Empty. No Customers Came in Yet....");
                    return;
                } else if (waittingChair.contains(cus)) {
                    if (cus == waittingChair.get(0)) {
                        waittingChair.remove(cus);
                        if (queue.size() > 0) {
                            waittingChair.add(queue.get(0));
                            System.out.println("Customer " + queue.get(0).id + " moved to sit on the Waiting Chair ♿♿♿");
                            queue.remove(0);
                        }
                    } else {
                        System.out.println("Customer " + cus.id + " got bored and left the Waiting Chair 🪑");
                        if (queue.size() > 0) {
                            System.out.println("Customer " + queue.get(0).id + " is taking the place of Customer " + cus.id);
                        }
                        waittingChair.remove(cus);
                    }
                } else if (queue.contains(cus)) {

                    System.out.println("Customer " + cus.id + " got bored and left the Salon*****************************");
                    queue.remove(cus);
                }
            }
        }
    }

    public int addTOBarberChair() {
        synchronized (waittingChair) {
            synchronized (barberChair) {
                Customer cus = waittingChair.get(0);
                barberChair.add(cus);
                int index = barberChair.indexOf(cus);
                removeCustomer(cus);
                return index;
            }
        }
    }

    public static synchronized int getCount() {
        return counter;
    }
}
