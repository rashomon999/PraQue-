package structures;

import model.Crop;
import model.Space;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomLinkedList<T> implements Iterable<T> {
    private Node<T> head;
    private int size;

    public CustomLinkedList() {
        this.head = null;
        this.size = 0;
    }

    // Método para agregar un nodo
    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        size++;
    }

    // Obtener el tamaño de la lista
    public int getSize() {
        return size;
    }

    // Mostrar el contenido de la lista
    public void display() {
        Node<T> current = head;
        while (current != null) {
            System.out.println(current.getData());
            current = current.getNext();
        }
    }

    /** description: Método para ordenar la lista por días de crecimiento de los cultivos (Insertion Sorting).
     *

     **/
    public void insertionSortByGrowthDays() {
        if (head == null || head.getNext() == null) {
            return;
        }

        Node<T> sortedList = null;
        Node<T> current = head;

        while (current != null) {
            Node<T> next = current.getNext();
            sortedList = sortedInsert(sortedList, current);
            current = next;
        }

        head = sortedList;
    }

    // Método auxiliar para la inserción ordenada
    private Node<T> sortedInsert(Node<T> sortedList, Node<T> newNode) {
        // Comparación entre nodos de tipo Space (que contienen Crop)
        if (sortedList == null || getCropGrowthDays(sortedList.getData()) > getCropGrowthDays(newNode.getData())) {
            newNode.setNext(sortedList);
            sortedList = newNode;
        } else {
            Node<T> current = sortedList;
            while (current.getNext() != null && getCropGrowthDays(current.getNext().getData()) <= getCropGrowthDays(newNode.getData())) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            current.setNext(newNode);
        }
        return sortedList;
    }

    // Método para obtener los días de crecimiento de un cultivo almacenado en un Space o un Crop
    private int getCropGrowthDays(T data) {
        if (data instanceof Space) {
            Space space = (Space) data;
            return space.getCrop().getCropGrowthDays();
        } else if (data instanceof Crop) {
            Crop crop = (Crop) data;
            return crop.getCropGrowthDays();
        }
        throw new IllegalArgumentException("Tipo no soportado para la ordenación por días de crecimiento.");
    }

    // Método para eliminar un nodo específico basado en su valor
    public boolean remove(T data) {
        if (head == null) {
            return false;
        }

        // Si el nodo a eliminar es el primero
        if (head.getData().equals(data)) {
            head = head.getNext();
            size--;
            return true;
        }

        // Buscar el nodo a eliminar
        Node<T> current = head;
        while (current.getNext() != null && !current.getNext().getData().equals(data)) {
            current = current.getNext();
        }

        // Si encontramos el nodo, lo eliminamos
        if (current.getNext() != null) {
            current.setNext(current.getNext().getNext());
            size--;
            return true;
        }

        return false; // Nodo no encontrado
    }

    // Método para limpiar la lista enlazada (eliminar todos los nodos)
    public void clear() {
        head = null;
        size = 0;
    }

    // Método para verificar si la lista está vacía
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public Iterator<T> iterator() {
        return new CustomIterator();
    }

    // Clase interna que implementa un iterador personalizado para la lista enlazada
    private class CustomIterator implements Iterator<T> {
        private Node<T> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            T data = current.getData();
            current = current.getNext();
            return data;
        }
    }

    // Método para obtener el nodo cabeza
    public Node<T> getHead() {
        return head;
    }
}
