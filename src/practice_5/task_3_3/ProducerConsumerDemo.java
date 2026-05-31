package practice_5.task_3_3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

public class ProducerConsumerDemo {

    // Вариант 1: Ручная реализация с synchronized/wait/notify
    static class SharedBuffer {
        private final Queue<Integer> buffer = new LinkedList<>();
        private final int capacity;

        public SharedBuffer(int capacity) {
            this.capacity = capacity;
        }

        public synchronized void put(int item) throws InterruptedException {
            while (buffer.size() >= capacity) {
                wait();
            }
            buffer.offer(item);
            notifyAll();
        }

        public synchronized int take() throws InterruptedException {
            while (buffer.isEmpty()) {
                wait();
            }
            int item = buffer.poll();
            notifyAll();
            return item;
        }
    }

    // Вариант 2: Используя BlockingQueue (рекомендуемый подход)
    static void demonstrateBlockingQueue() throws InterruptedException {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(5);

        // Производитель
        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    String task = "Задача-" + i;
                    queue.put(task); // Блокирует если очередь полна
                    System.out.println("[Производитель] Добавил: " + task
                            + " (в очереди: " + queue.size() + ")");
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Потребитель
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    String task = queue.take(); // Блокирует если очередь пуста
                    System.out.println("[Потребитель] Обработал: " + task);
                    Thread.sleep(200); // Потребитель медленнее
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        System.out.println("Все задачи обработаны!");
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== BlockingQueue Producer-Consumer ===");
        demonstrateBlockingQueue();
    }
}