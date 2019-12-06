/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medikart;

/**
 *
 * @author Sankalp
 */
public class MediKart {
    static String url       = "jdbc:mysql://localhost:3306/medikart";
    static String user      = "root";
    static String password  = "student"; 

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new Welcome().setVisible(true);
    }
    
}
