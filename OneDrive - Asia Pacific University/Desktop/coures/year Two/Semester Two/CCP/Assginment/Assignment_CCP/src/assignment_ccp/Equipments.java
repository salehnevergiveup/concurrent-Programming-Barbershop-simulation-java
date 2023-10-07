/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_ccp;

/**
 *
 * @author User
 */
public class Equipments {
    String name;  
    String type;
    public Equipments(String name ,  String type) { 
        this.name  = name; 
        this.type = type;  
    }
    
    public String getType() { 
        return type;
    }
    
    public String getName() { 
        return name;
    }
    
}
