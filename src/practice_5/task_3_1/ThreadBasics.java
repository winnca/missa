package practice_5.task_3_1;

public class ThreadBasics {

    static class DownloadSimulator extends Thread {
        private String filename;
        private int sizeKB;
        private int downloadedKB = 0;

        public DownloadSimulator(String filename, int sizeKB) {
            super("Downloader-" + filename);
            this.filename = filename;
            this.sizeKB = sizeKB;
        }

        @Override
        public void run() {
            System.out.printf("[%s] Начало загрузки %s (%d KB)%n",
                    getName(), filename, sizeKB);

            while (downloadedKB < sizeKB) {
                try {
                    Thread.sleep(100); // Симулируем задержку
                    downloadedKB = Math.min(downloadedKB + 100, sizeKB);
                    System.out.printf("[%s] Загружено: %d/%d KB (%.0f%%)%n",
                            getName(), downloadedKB, sizeKB,
                            (double) downloadedKB / sizeKB * 100);
                } catch (InterruptedException e) {
                    System.out.printf("[%s] Загрузка прервана!%n", getName());
                    return;
                }
            }

            System.out.printf("[%s] Загрузка %s завершена!%n", getName(), filename);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Запускаем параллельные загрузки...\n");

        Thread t1 = new DownloadSimulator("document.pdf", 300);
        Thread t2 = new DownloadSimulator("image.jpg", 500);
        Thread t3 = new DownloadSimulator("video.mp4", 1000);

        t1.start();
        t2.start();
        t3.start();

        System.out.println("Все загрузки запущены параллельно!");

        t1.join();
        t2.join();
        t3.join();

        System.out.println("\nВсе загрузки завершены!");
    }
}