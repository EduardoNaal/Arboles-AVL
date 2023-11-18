
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Eduardo
 */
public class MAIN {

    public static void main(String[] args) {
        //Se incertan nodos al arbol
        ArbolAvl arbol=new ArbolAvl();
        arbol.insertar(10);
        arbol.insertar(5);
        arbol.insertar(15);
        arbol.insertar(3);
        arbol.insertar(8);
        arbol.insertar(12);
        arbol.insertar(2);
        arbol.insertar(20);
        
        //Imprimimos el árbol Avl
        System.out.println("Arbol AVL inicial:");
        arbol.mostrarArbolAVL();    //Se mostrara
        arbol.buscar(8);    //Se busca el nodo
        
        arbol.eliminar(12); //eliminamos el nodo
                     

        System.out.println("Arbol AVL despues de eliminar 12:");
        arbol.mostrarArbolAVL();
        
        arbol.eliminar(20);
        System.out.println("Arbol AVL despues de eliminar 20:");
        arbol.mostrarArbolAVL();
        
        System.out.println("Se incerto un nuevo nodo 13");
        arbol.insertar(13);
        arbol.mostrarArbolAVL();
        
    }
}



