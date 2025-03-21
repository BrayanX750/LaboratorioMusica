/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratorio9;

/**
 *
 * @author Usuario
 */
public class Cabecera {
  Musica musica; 
    Cabecera siguiente;

    public Cabecera(Musica musica) {
        this.musica = musica;
        this.siguiente = null;
    }  
}
