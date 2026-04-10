//package practice_2.task_8_1;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//public class LibrarySystem {
//
//    // 1. Enum Genre
//    public enum Genre {
//        FICTION("Художественная литература"),
//        SCIENCE("Наука"),
//        HISTORY("История"),
//        PROGRAMMING("Программирование"),
//        ART("Искусство");
//
//        private final String russianName;
//
//        Genre(String russianName) {
//            this.russianName = russianName;
//        }
//
//        public String getRussianName() {
//            return russianName;
//        }
//
//        public static Genre fromString(String name) {
//            for (Genre g : Genre.values()) {
//                if (g.russianName.equalsIgnoreCase(name) || g.name().equalsIgnoreCase(name)) {
//                    return g;
//                }
//            }
//            throw new IllegalArgumentException("Неизвестный жанр: " + name);
//        }
//    }
//
//    // 2. Record Book
//    public record Book(String title, String author, int year, Genre genre, double price) {
//        public Book {
//            if (title == null || title.isBlank()) {
//                throw new IllegalArgumentException("Название книги не может быть пустым");
//            }
//            if (author == null || author.isBlank()) {
//                throw new IllegalArgumentException("Автор не может быть пустым");
//            }
//            int currentYear = java.time.Year.now().getValue();
//            if (year < 1450 || year > currentYear) {
//                throw new IllegalArgumentException("Год должен быть между 1450 и " + currentYear);
//            }
//            if (price < 0) {
//                throw new IllegalArgumentException("Цена не может быть отрицательной");
//            }
//        }
//
//        @Override
//        public String toString() {
//            return String.format("\"%s\" - %s (%d г., %s) - %.2f руб.",
//                    title, author, year, genre.getRussianName(), price);
//        }
//    }
//
//    // 3. Sealed interface и records
//    public sealed interface LibraryItem permits PhysicalBook, EBook {
//        String getInfo();
//        Book getBook();
//    }
//
//    public record PhysicalBook(Book book, String shelf) implements LibraryItem {
//        @Override
//        public String getInfo() {
//            return String.format("[Физическая] %s | Полка: %s", book, shelf);
//        }
//
//        @Override
//        public Book getBook() {
//            return book;
//        }
//    }
//
//    public record EBook(Book book, String format, double sizeMB) implements LibraryItem {
//        @Override
//        public String getInfo() {
//            return String.format("[Электронная] %s | Формат: %s, Размер: %.2f MB",
//                    book, format, sizeMB);
//        }
//
//        @Override
//        public Book getBook() {
//            return book;
//        }
//    }
//
//    // 4. Interface Searchable
//    public interface Searchable {
//        default boolean matches(String query) {
//            if (query == null || query.isBlank()) return false;
//            String lowerQuery = query.toLowerCase();
//            return getSearchableText().toLowerCase().contains(lowerQuery);
//        }
//
//        String getSearchableText();
//
//        static <T extends Searchable> List<T> search(List<T> items, String query) {
//            return items.stream()
//                    .filter(item -> item.matches(query))
//                    .collect(Collectors.toList());
//        }
//    }
//
//    // 5. Класс Library
//    public static class Library {
//        private final List<LibraryItem> items = new ArrayList<>();
//
//        public void add(LibraryItem item) {
//            items.add(item);
//        }
//
//        public void printCatalog() {
//            for (LibraryItem item : items) {
//                // Pattern matching switch
//                switch (item) {
//                    case PhysicalBook pb -> System.out.println("  📖 " + pb.getInfo());
//                    case EBook eb -> System.out.println("  💻 " + eb.getInfo());
//                }
//            }
//            System.out.println();
//        }
//
//        public Map<Genre, List<LibraryItem>> groupByGenre() {
//            return items.stream()
//                    .collect(Collectors.groupingBy(
//                            item -> item.getBook().genre(),
//                            () -> new EnumMap<>(Genre.class),
//                            Collectors.toList()
//                    ));
//        }
//
//        public double totalValue() {
//            return items.stream()
//                    .map(item -> item.getBook().price())
//                    .reduce(0.0, Double::sum);
//        }
//
//        public Optional<LibraryItem> mostExpensive() {
//            return items.stream()
//                    .max(Comparator.comparing(item -> item.getBook().price()));
//        }
//
//        public List<String> authorsByGenre(Genre genre) {
//            return items.stream()
//                    .filter(item -> item.getBook().genre() == genre)
//                    .map(item -> item.getBook().author())
//                    .distinct()
//                    .sorted()
//                    .collect(Collectors.toList());
//        }
//
//        public void printGroupedByGenre() {
//            Map<Genre, List<LibraryItem>> grouped = groupByGenre();
//            grouped.forEach((genre, itemList) -> {
//                System.out.println(genre.getRussianName() + ":");
//                itemList.forEach(item -> System.out.println("  - " + item.getBook().title()));
//            });
//            System.out.println();
//        }
//
//        public void printStatistics() {
//            System.out.printf("Всего книг: %d%n", items.size());
//            System.out.printf("Общая стоимость: %.2f руб.%n", totalValue());
//
//            mostExpensive().ifPresent(item -> {
//                System.out.println("Самая дорогая книга:");
//                System.out.println("  " + item.getInfo());
//            });
//
//            System.out.printf("Средняя цена: %.2f руб.%n",
//                    items.stream().mapToDouble(item -> item.getBook().price()).average().orElse(0));
//            System.out.println();
//        }
//    }
//
//    // Делаем LibraryItem поисковым
//    public static class SearchableLibraryItem implements Searchable {
//        private final LibraryItem item;
//
//        public SearchableLibraryItem(LibraryItem item) {
//            this.item = item;
//        }
//
//        @Override
//        public String getSearchableText() {
//            Book b = item.getBook();
//            return b.title() + " " + b.author() + " " + b.genre().getRussianName();
//        }
//
//        public LibraryItem getItem() {
//            return item;
//        }
//    }
//
//    public static void main(String[] args) {
//
//        // Создаём библиотеку
//        Library library = new Library();
//
//        // Добавляем физические книги
//        library.add(new PhysicalBook(
//                new Book("Война и мир", "Лев Толстой", 1869, Genre.FICTION, 1200.50),
//                "A-1"
//        ));
//
//        library.add(new PhysicalBook(
//                new Book("Преступление и наказание", "Фёдор Достоевский", 1866, Genre.FICTION, 950.00),
//                "A-2"
//        ));
//
//        library.add(new PhysicalBook(
//                new Book("Краткая история времени", "Стивен Хокинг", 1988, Genre.SCIENCE, 1500.00),
//                "B-3"
//        ));
//
//        library.add(new PhysicalBook(
//                new Book("SPQR. История Древнего Рима", "Мэри Бирд", 2015, Genre.HISTORY, 1800.00),
//                "C-1"
//        ));
//
//        library.add(new PhysicalBook(
//                new Book("Clean Code", "Роберт Мартин", 2008, Genre.PROGRAMMING, 2500.00),
//                "D-5"
//        ));
//
//        // Добавляем электронные книги
//        library.add(new EBook(
//                new Book("Java. Эффективное программирование", "Джошуа Блох", 2018, Genre.PROGRAMMING, 1800.00),
//                "PDF", 15.5
//        ));
//
//        library.add(new EBook(
//                new Book("Искусство цвета", "Иоханнес Иттен", 1961, Genre.ART, 2200.00),
//                "EPUB", 45.2
//        ));
//
//        library.add(new EBook(
//                new Book("Структура научных революций", "Томас Кун", 1962, Genre.SCIENCE, 1100.00),
//                "MOBI", 2.8
//        ));
//
//        library.add(new EBook(
//                new Book("Мастер и Маргарита", "Михаил Булгаков", 1967, Genre.FICTION, 800.00),
//                "FB2", 1.5
//        ));
//
//        // Демонстрация работы
//        library.printCatalog();
//        library.printStatistics();
//        library.printGroupedByGenre();
//
//        // Авторы по жанрам
//        for (Genre g : Genre.values()) {
//            List<String> authors = library.authorsByGenre(g);
//            if (!authors.isEmpty()) {
//                System.out.println(g.getRussianName() + ": " + String.join(", ", authors));
//            }
//        }
//        System.out.println();
//
//        // Демонстрация поиска
//        List<SearchableLibraryItem> searchableItems = library.items.stream()
//                .map(SearchableLibraryItem::new)
//                .collect(Collectors.toList());
//
//        String[] queries = {"Java", "история", "Толстой"};
//        for (String query : queries) {
//            System.out.println("Поиск по запросу: \"" + query + "\"");
//            List<SearchableLibraryItem> found = Searchable.search(searchableItems, query);
//            found.forEach(item -> System.out.println("  - " + item.getItem().getBook().title()));
//            System.out.println();
//        }
//
//        // Проверка метода fromString
//        System.out.println("Поиск жанра 'Программирование': " +
//                Genre.fromString("Программирование"));
//        System.out.println("Поиск жанра 'FICTION': " +
//                Genre.fromString("FICTION"));
//        System.out.println();
//    }
//}
