/**
 * Clase NodoAVL: representa un nodo en un Árbol AVL
 * Cada nodo tiene un valor, una altura y referencias a los hijos izquierdo y derecho
 * La altura se utiliza para mantener el equilibrio del árbol y garantizar un rendimiento óptimo
 * 
 * @author Eduardo
 */
public class NodoAvl {
    int altura;            // Altura del nodo
    int valor;             // Valor del nodo
    NodoAvl izquierda;     // Referencia al hijo izquierdo
    NodoAvl derecha;       // Referencia al hijo derecho

    /**
     * Constructor de la clase NodoAVL que inicializa el nodo con un valor dado
     * @param valor El valor del nodo
     */
    NodoAvl(int valor) {
        this.valor = valor;
        altura = 1; // Inicializamos la altura en 1, ya que el nodo se acaba de crear
    }
}
