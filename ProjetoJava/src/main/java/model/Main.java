package model;

import java.util.List;

public class Main {
    public static void main(String args[])
    {
        //Cliente c1 = ClienteDAO.getInstance().create("marcelli", "0284924");
        List<Cliente> c1 = ClienteDAO.getInstance().retrieveAll();

       // Animal a1 = AnimalDAO.getInstance().createAnimal("Kito Henrique", "4", "1");
        
        
      System.out.println(c1);
    }
    
}