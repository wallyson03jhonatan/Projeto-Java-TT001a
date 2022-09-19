/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.projetojava;

import model.Cliente;
import model.ClienteDAO;
import java.util.List;
import model.Animal;
import model.AnimalDAO;
import model.Especie;
import model.EspecieDAO;
/**
 *
 * @author wally
 */
public class ProjetoJava {

    public static void main(String[] args) {
      //  Cliente c1 = ClienteDAO.getInstance().create("Marcia", "12345678989");
      Animal a3 = AnimalDAO.getInstance().create("Raiposo", 2, 1, 2, 1);
      System.out.println(a3);
    }
}
