package gc;

import java.util.ArrayList;

public class Demonstration {

    /**
     * Внутренний тестовый класс
     * соедржащий одно поле
     * и прегруженным методом finalize печатающим содрежимое поля
     * обьект занимает 288 килобайт
     */
        public static class TestUnit {
            final private String name;

            public TestUnit(String name) {
                this.name = name;
            }

            public String getName() {
                return name;
            }
            @Override
            protected void finalize() throws Throwable {
                super.finalize();
                System.out.println(this.getName() + " destroyed");
            }

        }

    public static class EmptyClass {

        }

    /**
     * Демонстрация работы сборщика при параметрах jvm -Xmx4m
     * создаётся обьект object тестового класса для демонстрации
     * после чего указателю на обьект присваевается null
     * То есть на созданный обьект теперь ничего не указывает
     * После чего создаёт массив и в цикле заполняется.
     * В какой то момент, при заполнении выделенной памяти происходить очистка обьекта
     * и визывается перегруженный метод finalize, где печается содержимое поля обьекта.
     * @param args
     */

    public static void main(String[] args) {
        info();
        var object = new EmptyClass(); //new TestUnit("Test Zero");
        info();
        object = null;
        TestUnit[] arr = new TestUnit[17000];
        info();
        for (int i = 0; i < 17000; i++) {
            arr[i] = new TestUnit("Test " + i);
        }
        info();
    }

    /**
     * статический метод печатающий информацию о памяти
     */
    public static void info() {
        int kb = 1024;

        var runtime = Runtime.getRuntime();
        System.out.println("--------------------------------------------------------------");
        System.out.println("Used memory " + (runtime.totalMemory() - runtime.freeMemory()) / kb);
        System.out.println("Free memory " + runtime.freeMemory() / kb);
        System.out.println("Total memory " + runtime.totalMemory() / kb);
        System.out.println("Max memory " + runtime.maxMemory() / kb);
    }


}
