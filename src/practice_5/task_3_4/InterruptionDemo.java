package practice_5.task_3_4;

public class InterruptionDemo {

    static class LongRunningTask implements Runnable {
        private final String taskName;
        private final int totalSteps;

        public LongRunningTask(String taskName, int totalSteps) {
            this.taskName = taskName;
            this.totalSteps = totalSteps;
        }

        @Override
        public void run() {
            System.out.printf("[%s] Старт, шагов: %d%n", taskName, totalSteps);

            for (int step = 1; step <= totalSteps; step++) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.printf("[%s] Обнаружено прерывание, завершение%n", taskName);
                    return;
                }

                System.out.printf("[%s] Шаг %d/%d%n", taskName, step, totalSteps);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.printf("[%s] Прервано на шаге %d%n", taskName, step);
                    Thread.currentThread().interrupt();
                    return;
                }
            }

            System.out.printf("[%s] Завершено!%n", taskName);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread task1 = new Thread(new LongRunningTask("Задача-А", 10), "Thread-A");
        Thread task2 = new Thread(new LongRunningTask("Задача-Б", 10), "Thread-B");

        task1.start();
        task2.start();

        Thread.sleep(1500); // Ждём 1.5 секунды

        System.out.println("\n--- Прерываем Задачу-А ---");
        task1.interrupt(); // Прерываем один поток

        task1.join();
        task2.join();

        System.out.printf("Задача-А прервана: %s%n", !task1.isAlive());
        System.out.printf("Задача-Б завершена: %s%n", !task2.isAlive());
    }
}
