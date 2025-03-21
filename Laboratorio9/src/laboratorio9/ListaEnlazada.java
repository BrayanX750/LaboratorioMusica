/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratorio9;


public class ListaEnlazada {

   Cabecera cabeza;

    public void agregarCancion(String titulo, String artista, String coverPath, int duracion, String ruta) {
        Musica nuevaCancion = new Musica(titulo, artista, coverPath, duracion, ruta);
        Cabecera nuevo = new Cabecera(nuevaCancion);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Cabecera temp = cabeza;
            while (temp.siguiente != null) {
                temp = temp.siguiente;
            }
            temp.siguiente = nuevo;
        }
    }

    public Cabecera seleccionarCancion(int indice) {
        Cabecera temp = cabeza;
        int contador = 0;
        while (temp != null && contador < indice) {
            temp = temp.siguiente;
            contador++;
        }
        return temp;
    }
}
