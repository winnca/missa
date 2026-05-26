package practice_4.task_4_4;

// Уровень 1: Работа с базой данных (низкий уровень)
class DatabaseLayer {
    public String fetchData(int id) throws Exception {
        if (id <= 0) {
            // Симулируем SQL ошибку
            throw new Exception("SQL Error: Invalid ID " + id);
        }
        return "данные_" + id;
    }
}

// Уровень 2: Сервисный слой
class DataService {
    private DatabaseLayer db = new DatabaseLayer();

    public String getData(int id) throws ServiceException {
        try {
            return db.fetchData(id);
        } catch (Exception e) {
            throw new ServiceException("Не удалось получить данные для id=" + id, e);
        }
    }
}

// Пользовательское исключение сервисного слоя
class ServiceException extends Exception {
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}

// Уровень 3: Слой приложения
public class Application {
    public static void main(String[] args) {
        DataService service = new DataService();

        // Тест с корректным id
        try {
            System.out.println(service.getData(1));
        } catch (ServiceException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        // Тест с некорректным id
        try {
            System.out.println(service.getData(-1));
        } catch (ServiceException e) {
            System.out.println("Ошибка сервиса: " + e.getMessage());
            System.out.println("Причина: " + e.getCause().getMessage()); // Исходное исключение
            System.out.println("\nПолный стек:");
            e.printStackTrace();
        }
    }
}
