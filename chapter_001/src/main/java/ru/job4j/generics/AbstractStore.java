package ru.job4j.generics;

public abstract class AbstractStore<T extends Base> implements Store<T> {
    protected SimpleArray<T> inputArray;

    public AbstractStore(int size) {
        this.inputArray = new SimpleArray(size);
    }

    public int findIndexbyId(String id) {
        var result = -1;
        int index = 0;
        while (index < this.inputArray.getArraySize()) {
            var forReplace = inputArray.get(index);
            if (forReplace.getId().equals(id)) {
                result = index;
                break;
            } else {
                index++;
            }
        }
        return result;
    }

    public void add(T model) {
        inputArray.add(model);
    }

    public boolean replace(String id, T model) {
        var result = false;
        var index = this.findIndexbyId(id);
        if (index >= 0) {
            this.inputArray.set(index, model);
            result = true;
        }
        return result;
    }

    public boolean delete(String id) {
        var result = false;
        var index = this.findIndexbyId(id);
        if (index >= 0) {
            this.inputArray.remove(index);
            result = true;
        }
        return result;
    }

    public T findById(String id) {
        T result = null;
        var index = this.findIndexbyId(id);
        if (index >= 0) {
            result = inputArray.get(index);
        }
        return result;
    }



}
