/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratorio9;


public class ListaEnlazada {

    Cabecera cabeza;

    public void agregarCancion(String titulo, String artista, String coverPath, int duracion, String ruta, String genero) {
        Musica nuevaCancion = new Musica(titulo, artista, coverPath, duracion, ruta, genero);
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
    
    // Método para eliminar una canción por su índice
    public void eliminarCancion(int indice) {
        if (cabeza == null) {
            return;
        }
        if (indice == 0) {
            cabeza = cabeza.siguiente;
            return;
        }
        Cabecera current = cabeza;
        int count = 0;
        while (current != null && current.siguiente != null) {
            if (count == indice - 1) {
                current.siguiente = current.siguiente.siguiente;
                return;
            }
            current = current.siguiente;
            count++;
        }
    }
}
