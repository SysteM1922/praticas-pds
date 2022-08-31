
//import, to not repeat "java.util" so many times
import java.util.Iterator;
import java.util.ListIterator;

import java.util.NoSuchElementException;

public class VectorGeneric<T> {
    private T[] vec;
    private int nElem;
    private final static int ALLOC = 50;
    private int dimVec = ALLOC;

    @SuppressWarnings("unchecked")
    public VectorGeneric() {
        vec = (T[]) new Object[dimVec];
        nElem = 0;
    }

    public boolean addElem(T elem) {
        if (elem == null)
            return false;
        ensureSpace();
        vec[nElem++] = elem;
        return true;
    }

    private void ensureSpace() {
        if (nElem >= dimVec) {
            dimVec += ALLOC;
            @SuppressWarnings("unchecked")
            T[] newArray = (T[]) new Object[dimVec];
            System.arraycopy(vec, 0, newArray, 0, nElem);
            vec = newArray;
        }
    }

    public boolean removeElem(T elem) {
        for (int i = 0; i < nElem; i++) {
            if (vec[i].equals(elem)) {
                if (nElem - i - 1 > 0) // not last element
                    System.arraycopy(vec, i + 1, vec, i, nElem - i - 1);
                vec[--nElem] = null; // libertar último objecto para o GC
                return true;
            }
        }
        return false;
    }

    public int totalElem() {
        return nElem;
    }

    public T getElem(int i) {
        return (T) vec[i];
    }

    // a)
    // return iterators

    public Iterator<T> Iterator() {
        return (this).new VectorIterator<T>();
    }

    public ListIterator<T> listIterator() {
        return (this).new VectorListIterator<T>(0);
    }

    public ListIterator<T> listIterator(int index) {
        return (this).new VectorListIterator<T>(index);

    }

    // private classes

    private class VectorIterator<T> implements Iterator<T> {
        private int index;

        VectorIterator() {
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            if (nElem > index) {
                return true;
            }
            return false;
        }

        @Override
        public T next() {
            boolean condition = hasNext();
            if (condition) {
                @SuppressWarnings("unchecked")
                T ret_value = (T) VectorGeneric.this.vec[index++];
                return ret_value;
            } else
                throw new NoSuchElementException("Operation not supported");
        }

    }

    private class VectorListIterator<T> implements ListIterator<T> {
        private int index;

        VectorListIterator(int index) {
            this.index = index;
        }

        public boolean hasNext() {
            if (nElem > index) {
                return true;
            } else {
                this.index = this.index - 1;
                return false;
            }
        }

        public T next() {
            if (hasNext()) {
                @SuppressWarnings("unchecked")
                T ret_value = (T) VectorGeneric.this.vec[this.index++];
                return ret_value;
            } else
                throw new NoSuchElementException("Index out of bounds");
        }

        public int nextIndex() {

            return this.index + 1;
        }

        public boolean hasPrevious() {
            if (this.index > 0) {
                return true;
            } else {
                this.index = this.index + 1;
                return false;
            }
        }

        public T previous() {
            if (hasPrevious()) {
                @SuppressWarnings("unchecked")
                T ret_value = (T) VectorGeneric.this.vec[this.index--];
                return ret_value;
            } else {
                throw new NoSuchElementException("Index out of bounds aqui");
            }
        }

        public int previousIndex() {
            return this.index - 1;
        }

        // function add/remove/set are optional, only included them for erros not to
        // appear

        @Override
        public void add(T e) {
            throw new UnsupportedOperationException("Not supportes operation!");
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supportes operation!");
        }

        @Override
        public void set(T e) {
            throw new UnsupportedOperationException("Not supportes operation!");
        }
    }
}
