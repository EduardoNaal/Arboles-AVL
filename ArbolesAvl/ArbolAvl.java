/**
 * Clase ArbolAVL: Árbol AVL auto-balanceado
 * Mantén el balance automáticamente después de inserciones y eliminaciones
 * Cada nodo tiene un valor, referencia al hijo izquierdo y derecho, y almacena altura
 * 
 * @author Eduardo
 */
public class ArbolAvl {
    NodoAvl raiz;

    /**
     * Limpia el árbol, eliminando todos los nodos
     */
    public void limpiar() {
        raiz = null;
    }

    /**
     * Inserta un nuevo valor en el árbol AVL
     * @param valor Valor a insertar
     */
    public void insertar(int valor) {
        raiz = insertarAVL(raiz, valor);
    }

    /**
     * Función recursiva para la inserción de un valor en el árbol AVL
     * @param nodoActual Nodo actual en el que se está considerando la inserción
     * @param valor Valor que se va a insertar
     * @return Nodo actual después de la inserción
     */
    private NodoAvl insertarAVL(NodoAvl nodoActual, int valor) {
        if (nodoActual == null) {
            return (new NodoAvl(valor));
        }

        if (valor < nodoActual.valor) {
            nodoActual.izquierda = insertarAVL(nodoActual.izquierda, valor);
        } else if (valor > nodoActual.valor) {
            nodoActual.derecha = insertarAVL(nodoActual.derecha, valor);
        } else {
            // Si el valor ya existe, no hacemos nada
            return nodoActual;
        }

        // Actualizamos la altura del nodo actual
        nodoActual.altura = 1 + Math.max(obtenerAltura(nodoActual.izquierda), obtenerAltura(nodoActual.derecha));

        // Calculamos el factor de equilibrio del nodo actual
        int factorEquilibrio = obtenerFactorEquilibrio(nodoActual);

        // Realizamos rotaciones según el factor de equilibrio
        if (factorEquilibrio > 1 && valor < nodoActual.izquierda.valor) {
            return rotarDerecha(nodoActual);
        }

        if (factorEquilibrio < -1 && valor > nodoActual.derecha.valor) {
            return rotarIzquierda(nodoActual);
        }

        if (factorEquilibrio > 1 && valor > nodoActual.izquierda.valor) {
            nodoActual.izquierda = rotarIzquierda(nodoActual.izquierda);
            return rotarDerecha(nodoActual);
        }

        if (factorEquilibrio < -1 && valor < nodoActual.derecha.valor) {
            nodoActual.derecha = rotarDerecha(nodoActual.derecha);
            return rotarIzquierda(nodoActual);
        }

        return nodoActual;
    }

    /**
     * Busca un elemento en el árbol AVL e imprime un mensaje
     * @param elemento Elemento a buscar
     */
    public void buscar(int elemento) {
        if (buscarEnAVL(raiz, elemento)) {
            System.out.println("Encontrado!");
        } else {
            System.out.println("No encontrado :(");
        }
    }

    /**
     * Función recursiva para la búsqueda de un elemento en el árbol AVL
     * @param nodoActual Nodo actual en el que se está considerando la búsqueda
     * @param elemento Elemento a buscar
     * @return true si el elemento se encuentra, false de lo contrario
     */
    private boolean buscarEnAVL(NodoAvl nodoActual, int elemento) {
        if (nodoActual == null) {
            return false;
        } else if (elemento == nodoActual.valor) {
            return true;
        } else if (elemento < nodoActual.valor) {
            return buscarEnAVL(nodoActual.izquierda, elemento);
        } else {
            return buscarEnAVL(nodoActual.derecha, elemento);
        }
    }

    /**
     * Elimina un elemento del árbol AVL
     * @param valor Valor a eliminar
     */
    public void eliminar(int valor) {
        raiz = eliminarAVL(raiz, valor);
    }

    /**
     * Función recursiva para la eliminación de un elemento en el árbol AVL
     * @param nodoActual Nodo actual en el que se está considerando la eliminación
     * @param valor Valor a eliminar
     * @return Nodo actual después de la eliminación
     */
    private NodoAvl eliminarAVL(NodoAvl nodoActual, int valor) {
        if (nodoActual == null)
            return nodoActual;

        if (valor < nodoActual.valor) {
            nodoActual.izquierda = eliminarAVL(nodoActual.izquierda, valor);
        } else if (valor > nodoActual.valor) {
            nodoActual.derecha = eliminarAVL(nodoActual.derecha, valor);
        } else {
            // El nodo es igual al valor, se elimina
            // Nodo con un único hijo o es una hoja
            if ((nodoActual.izquierda == null) || (nodoActual.derecha == null)) {
                NodoAvl temp = (nodoActual.izquierda != null) ? nodoActual.izquierda : nodoActual.derecha;

                // Caso que no tiene hijos
                if (temp == null) {
                    nodoActual = null; // Se elimina dejándolo en null
                } else {
                    // Caso con un hijo
                    nodoActual = temp; // Elimina el valor actual reemplazándolo por su hijo
                }
            } else {
                // Nodo con dos hijos, se busca el predecesor
                NodoAvl temp = getNodoConValorMaximo(nodoActual.izquierda);

                // Se copia el dato del predecesor
                nodoActual.valor = temp.valor;

                // Se elimina el predecesor
                nodoActual.izquierda = eliminarAVL(nodoActual.izquierda, temp.valor);
            }
        }

        // Si solo tiene un nodo
        if (nodoActual == null)
            return nodoActual;

        // Actualiza altura
        nodoActual.altura = 1 + Math.max(obtenerAltura(nodoActual.izquierda), obtenerAltura(nodoActual.derecha));

        // Calcula factor de equilibrio (FE)
        int factorEquilibrio = obtenerFactorEquilibrio(nodoActual);

        // Se realizan las rotaciones pertinentes dado el FE
        if (factorEquilibrio > 1 && obtenerFactorEquilibrio(nodoActual.izquierda) >= 0) {
            return rotarDerecha(nodoActual);
        }

        if (factorEquilibrio < -1 && obtenerFactorEquilibrio(nodoActual.derecha) <= 0) {
            return rotarIzquierda(nodoActual);
        }

        if (factorEquilibrio > 1 && obtenerFactorEquilibrio(nodoActual.izquierda) < 0) {
            nodoActual.izquierda = rotarIzquierda(nodoActual.izquierda);
            return rotarDerecha(nodoActual);
        }

        if (factorEquilibrio < -1 && obtenerFactorEquilibrio(nodoActual.derecha) > 0) {
            nodoActual.derecha = rotarDerecha(nodoActual.derecha);
            return rotarIzquierda(nodoActual);
        }

        return nodoActual;
    }

    /**
     * Realiza una rotación hacia la derecha en el árbol AVL
     * @param nodoActual Nodo actual en el que se está considerando la rotación
     * @return Nueva raíz después de la rotación
     */
    private NodoAvl rotarDerecha(NodoAvl nodoActual) {
        NodoAvl nuevaRaiz = nodoActual.izquierda;
        NodoAvl temp = nuevaRaiz.derecha;

        nuevaRaiz.derecha = nodoActual;
        nodoActual.izquierda = temp;

        nodoActual.altura = 1 + Math.max(obtenerAltura(nodoActual.izquierda), obtenerAltura(nodoActual.derecha));
        nuevaRaiz.altura = 1 + Math.max(obtenerAltura(nuevaRaiz.izquierda), obtenerAltura(nuevaRaiz.derecha));

        return nuevaRaiz;
    }

    /**
     * Realiza una rotación hacia la izquierda en el árbol AVL
     * @param nodoActual Nodo actual en el que se está considerando la rotación
     * @return Nueva raíz después de la rotación
     */
    private NodoAvl rotarIzquierda(NodoAvl nodoActual) {
        NodoAvl nuevaRaiz = nodoActual.derecha;
        NodoAvl temp = nuevaRaiz.izquierda;

        nuevaRaiz.izquierda = nodoActual;
        nodoActual.derecha = temp;

        nodoActual.altura = 1 + Math.max(obtenerAltura(nodoActual.izquierda), obtenerAltura(nodoActual.derecha));
        nuevaRaiz.altura = 1 + Math.max(obtenerAltura(nuevaRaiz.izquierda), obtenerAltura(nuevaRaiz.derecha));

        return nuevaRaiz;
    }

    /**
     * Muestra el árbol AVL
     */
    public void mostrarArbolAVL() {
        System.out.println("Arbol AVL");
        mostrarArbol(raiz, 0);
    }

    /**
     * Función auxiliar para mostrar el árbol AVL de manera recursiva
     * @param nodo Nodo actual que se está considerando
     * @param profundidad Profundidad actual en el árbol
     */
    private void mostrarArbol(NodoAvl nodo, int profundidad) {
        if (nodo.derecha != null) {
            mostrarArbol(nodo.derecha, profundidad + 1);
        }
        for (int i = 0; i < profundidad; i++) {
            System.out.print("    ");
        }
        System.out.println("(" + nodo.valor + ")");

        if (nodo.izquierda != null) {
            mostrarArbol(nodo.izquierda, profundidad + 1);
        }
    }

    /**
     * Obtiene la altura de un nodo dado
     * @param nodoActual Nodo del cual se quiere obtener la altura
     * @return Altura del nodo
     */
    private int obtenerAltura(NodoAvl nodoActual) {
        if (nodoActual == null) {
            return 0;
        }
        return nodoActual.altura;
    }

    /**
     * Obtiene el factor de equilibrio de un nodo dado
     * @param nodoActual Nodo del cual se quiere obtener el factor de equilibrio.
     * @return Factor de equilibrio del nodo
     */
    private int obtenerFactorEquilibrio(NodoAvl nodoActual) {
        if (nodoActual == null) {
            return 0;
        }
        return obtenerAltura(nodoActual.izquierda) - obtenerAltura(nodoActual.derecha);
    }

    /**
     * Obtiene el nodo con el valor máximo en un subárbol
     * @param nodo Nodo raíz del subárbol
     * @return Nodo con el valor máximo en el subárbol
     */
    private NodoAvl getNodoConValorMaximo(NodoAvl nodo) {
        NodoAvl actual = nodo;

        while (actual.derecha != null) {
            actual = actual.derecha;
        }

        return actual;
    }
}
