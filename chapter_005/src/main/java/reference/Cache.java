package reference;

import java.lang.ref.SoftReference;

public class Cache<T> {
    private SoftReference<T> softOobject;
    T getObject() {
        return this.softOobject.get();
    }
    void putObject(T object) {
        this.softOobject = new SoftReference<>(object);
    }
}
