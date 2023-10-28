package org.example;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Реализация структуры данных ArrayList (не потокобезопасный). Этот класс предоставляет методы
 * для управления размером массива, который используется внутри для хранения списка элементов.
 * Методы - добавить элемент, добавить элемент по индексу, получить элемент, удалить элемент,
 * очистить всю коллекцию, отсортировать, заменить элемент по индексу.
 * @param <T> - тип элементов в этом списке
 */
public class MyArrayList<T> {

    /**
     * Начальная емкость по умолчанию.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Массив-хранилище MyArrayList, в котором хранятся элементы.
     * Емкость MyArrayList — это длина этого Массива-хранилища. Любой
     * пустой MyArrayList будет расширен до DEFAULT_CAPACITY при
     * добавлении первого элемента.
     */
    private Object[] array;

    /**
     * Размер MyArrayList (количество содержащихся в нем элементов).
     */
    public int size;

    /**
     * Создает список MyArrayList на основе передаваемого массива.
     * Размер списка size будет соответсваовать длине переданного массива
     * @param array - передаваемый массив
     */
    public MyArrayList(Object[] array) {
        this.array = array;
        this.size = array.length;
    }

    /**
     * Создает пустой список с указанной начальной емкостью.
     * Размер MyArrayList будет равен 0, так как элементы в списке отсутствуют
     */
    public MyArrayList() {
        array = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Добавляет передаваемый элемент в конец спискка
     * @param element - элемент, который будет добавлен в этот список
     */
    public void add(T element) {
        if (size == array.length) {
            increaseCapacity();
        }
        array[size++] = element;
    }

    /**
     * Вставляет ппередаваемый элемент в указанную позицию в этом списке.
     * Смещает элемент, находящийся в данный момент в этой позиции (если есть),
     * и любые последующие элементы вправо (добавляет единицу к их индексам).
     * Если указаннаяя позиция выходит за пределы списка, будет выброшено IndexOutOfBoundsException().
     * @param index - позиция, куда будет вставлен новый элемент
     * @param element - новый элемент
     */
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (size == array.length) {
            increaseCapacity();
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    /**
     * Выдает элемент содержащийся в списке по указанной позиции.
     * Если указанная позиция выходит за пределы списка, будет выброшено
     * IndexOutOfBoundsException().
     * @param index - позиция элемента, который будет выдан.
     * @return
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (T) array[index];
    }

    /**
     * Удаляет элемент содержащийся на указанной позиции.
     * Если указанная позиция выходит за пределы списка, будет выброшено IndexOutOfBoundsException().
     * @param index - позиция элемента, который будет удален.
     */
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null;
    }

    /**
     * Очищает список, удалаяет все содержащиеся в нем элементы.
     */
    public void clear() {
        Arrays.fill(array, null);
        size = 0;
    }

    /**
     * Заменяет элемент содержащийся в списке на указанной позиции
     * на передаваемый элемент.
     * Если указанная позиция выходит за пределы списка, будет выброшено IndexOutOfBoundsException().
     * @param index - позиция элемента который будет заменен.
     * @param element - новый элемент, который заменит находящийся в списке.
     */
    public void replace(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        array[index] = element;
    }

    /**
     * Сортирует этот список в соответствии с порядком,
     * заданным указанным компаратором. Сортировка реализуется алгоритмом "быстрой сортировки".
     * @param comparator - передаваемый компаратором, для сравнения элементов.
     */
    public void sort(Comparator< ? super T> comparator) {
        QuickSort.quickSort(this, comparator);
    }

    private void increaseCapacity() {
        int newCapacity = array.length * 2;
        array = Arrays.copyOf(array, newCapacity);
    }

    /**
     * Реализует алгоритм быстрой сортировки (QuickSort) для списка MyArrayList элементов типа T,
     * используя передаваемый компаратор для сравнения элементов.
     * Метод quickSort является точкой входа в алгоритм и вызывает
     * приватный метод quickSort с дополнительными параметрами.
     * Приватный метод quickSort выполняет рекурсивную сортировку списка.
     * Он сначала выбирает опорный элемент (pivot) из списка (в данном случае это последний элемент),
     * а затем разделяет список на две части: элементы, меньшие или равные опорному, и элементы, большие опорного.
     * Затем он вызывает себя рекурсивно для обеих частей списка, чтобы отсортировать их.
     * Приватный метод partition выполняет фазу разделения в алгоритме быстрой сортировки.
     * Он использует опорный элемент, чтобы разделить список на две части. Все элементы, меньшие или
     * равные опорному, помещаются перед ним, а все элементы, большие опорного, - после него. Метод также возвращает индекс опорного элемента.
     * Приватный метод swap обменивает элементы списка по указанным индексам.
     */
    public static class QuickSort {
        public static <T> void quickSort(MyArrayList<T> list, Comparator<? super T> comparator) {
            quickSort(list, comparator, 0, list.size - 1);
        }

        private static <T> void quickSort(MyArrayList<T> list,
                                          Comparator<? super T> comparator,
                                          int left,
                                          int right) {
            if (left < right) {
                int pivotIndex = partition(list, comparator, left, right);
                quickSort(list, comparator, left, pivotIndex - 1);
                quickSort(list, comparator, pivotIndex + 1, right);
            }
        }

        private static <T> int partition(MyArrayList<T> list,
                                         Comparator<? super T> comparator,
                                         int left,
                                         int right) {
            T pivot = list.get(right);
            int i = left - 1;

            for (int j = left; j < right; j++) {
                if (comparator.compare(list.get(j), pivot) <= 0) {
                    i++;
                    swap(list, i, j);
                }
            }

            swap(list, i + 1, right);
            return i + 1;
        }

        private static <T> void swap(MyArrayList<T> list, int i, int j) {
            T temp = list.get(i);
            list.replace(i, list.get(j));
            list.replace(j, temp);
        }
    }
}

