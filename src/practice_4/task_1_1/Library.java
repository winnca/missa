package practice_4.task_1_1;

public class Library {
    private String name;
    private int capacity;
    Library(String name, int capacity){
        this.name=name;
        this.capacity=capacity;
    }
    class Book{
        String title;
        String author;
        int year;
        Book(String title, String author, int year){
            this.title=title;
            this.author=author;
            this.year=year;
        }
        public void getInfo(){
            System.out.println("Книга: " + title + " автора " + author + " (" + year + ") " + "в библиотеке " + name);
        }
    }
    public static void main(String[] args){
        Library library = new Library("Городская библиотека", 100);
        Book book1 = library.new Book("Война и мир", "Толстой Л.Н.", 1869);
        book1.getInfo();
    }
}
