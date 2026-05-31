package practice_5.task_3_2;

public class SynchronizationDemo {

    // Небезопасный счётчик
    static class UnsafeCounter {
        private int count = 0;
        public void increment() { count++; }
        public int getCount() { return count; }
    }

    static class SafeCounter {
        private int count = 0;

        public synchronized void increment() {
            count++;
        }

        public synchronized int getCount() {
            return count;
        }
    }

    static void testCounter(Object counter, int threads, int incrementsPerThread)
            throws InterruptedException {
        Thread[] threadArray = new Thread[threads];
        for (int i = 0; i < threads; i++) {
            threadArray[i] = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    if (counter instanceof UnsafeCounter uc) uc.increment();
                    else if (counter instanceof SafeCounter sc) sc.increment();
                }
            });
        }

        for (Thread t : threadArray) t.start();
        for (Thread t : threadArray) t.join();

        int expected = threads * incrementsPerThread;
        int actual = counter instanceof UnsafeCounter uc ? uc.getCount()
                : counter instanceof SafeCounter sc ? sc.getCount() : -1;
        System.out.printf("Ожидаем: %d, Получили: %d, %s%n",
                expected, actual,
                actual == expected ? "КОРРЕКТНО" : "ОШИБКА (потеряно " + (expected - actual) + ")");
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Небезопасный счётчик ===");
        for (int i = 0; i < 5; i++) {
            testCounter(new UnsafeCounter(), 10, 1000);
        }

        System.out.println("\n=== Безопасный счётчик ===");
        for (int i = 0; i < 5; i++) {
            testCounter(new SafeCounter(), 10, 1000);
        }
    }
}

