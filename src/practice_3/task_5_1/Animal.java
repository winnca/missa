package practice_3.task_5_1;

import java.util.*;

public abstract class Animal {
    private String name;
    private int age;
    private double weight;
    private int energyLevel;

    public Animal(String name, int age, double weight, int energyLevel) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.energyLevel = energyLevel;
    }

    public void eat(int calories){System.out.println("Съел столько то калорий: " + calories);}
    public void sleep(int hours){ System.out.println("Сплю столько часов: " + hours);}
    public void makeSound(){ System.out.println("Умею издавать звуки");}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public int getAge() { return age;}
    public void setAge(int age) { this.age = age;}
    public double getWeight() { return weight;}
    public void setWeight(double weight) { this.weight = weight;}
    public int getEnergyLevel() { return energyLevel;}
    public void setEnergyLevel(int energyLevel) { this.energyLevel = energyLevel; }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "{name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", energyLevel=" + energyLevel +
                '}';
    }
}

class Predator extends Animal{
    public Predator(String name, int age, double weight, int energyLevel){ // хищник
        super(name, age, weight, energyLevel);
    }
    public void hunt(){ // охотиться
        setEnergyLevel(getEnergyLevel()+30);
        setWeight(getWeight()-0.5);
        System.out.println("Охота повышает energyLevel = " + getEnergyLevel() + "; снижает weight = " + getWeight());
    }
}

class Herbivore extends Animal{
    public Herbivore(String name, int age, double weight, int energyLevel){
        super(name, age, weight, energyLevel);
    }
    public void graze(){ // пасётся
        setEnergyLevel(getEnergyLevel()+15);
        System.out.println("Пастбище повышает energyLevel = " + getEnergyLevel());
    }
}

class Lion extends Predator implements Trainable{ // лев
    public Lion(String name, int age, double weight, int energyLevel){
        super(name, age, weight, energyLevel);
    }
    @Override
    public void makeSound(){
        System.out.println("Р-р-р!");
    }
    public void roar(){ // рёв
        System.out.println("Рёв");
    }

    @Override
    public void train(String command) {
        System.out.println("Команда = " + command);
    }

    @Override
    public void listCommands() {
        train("Сидеть");
        train("Лежать");
        train("Бегать");
    }
}

class Elephant extends Herbivore{
    public Elephant(String name, int age, double weight, int energyLevel){
        super(name, age, weight, energyLevel);
    }
    @Override
    public void makeSound(){
        System.out.println("Тууут!");
    }

    public void trumpet(){ // трубит
        System.out.println("Трубит");
    }
}

interface Trainable{
    void train(String command);
    void listCommands();
}

class Zoo{ // репозиторий
    List<Animal> animals = new ArrayList<>();
    public void addAnimal(Animal a){ // добавляем животное
        animals.add(a);
        System.out.println("Добавили животное: " + a.getClass().getSimpleName() + "; имя = " + a.getName());
    }
    public void feedAll(){ // кормить всех животных
        for (Animal a : animals){
            System.out.println("Покормили животное = " + a.getClass().getSimpleName() + "; имя = " + a.getName());
            a.eat(100);
        }
    }
    public void makeNoise(){ // шумит
        for (Animal a : animals){
            a.makeSound();
            System.out.println("Шумело животное = " + a.getClass().getSimpleName() + "; имя = " + a.getName());
        }
    }
    public List<Animal> getHungryAnimals(){
        List<Animal> hungryAnimals = new ArrayList<>();
        for (Animal a: animals){
            if(a.getEnergyLevel() < 30) {
                System.out.println("Животное голодное = " + a.getClass().getSimpleName() + "; имя = " + a.getName());
                hungryAnimals.add(a);
            }
        }
        return hungryAnimals;
    }

    public Optional<Animal> findAnimal(String name){ // обёртка на null, чтобы не было исключения NullPointerException
        for (Animal a: animals){
            if(a.getName().equalsIgnoreCase(name)) return Optional.of(a); // создаёт коллекцию с ненулевым значением, если a == null, то вызовется исключение NullPointerException.
            // equalsIgnoreCase сравнивает без учёта регистров. Лев Пумба = лев пумба
        }
        return Optional.empty(); // пустая коллекция
    }
}

class ZooTest{
    public static void main(String[] args){
        Zoo zoo = new Zoo();

        // Создаём животных
        Lion simba = new Lion("Simba", 5, 150.0, 50);
        Elephant dumbo = new Elephant("Dambo", 10, 3000.0, 20);
        Lion leo = new Lion("Leo", 3, 120.0, 70);
        Elephant babar = new Elephant("Neo", 8, 2800.0, 15);

        // Добавляем в зоопарк
        zoo.addAnimal(simba);
        zoo.addAnimal(dumbo);
        zoo.addAnimal(leo);
        zoo.addAnimal(babar);

        // Демонстрация звуков
        zoo.makeNoise();

        // Голодные животные (энергия < 30)
        for (Animal a : zoo.getHungryAnimals()) {
            System.out.println(a.getName() + " (energy: " + a.getEnergyLevel() + ")");
        }

        // Кормление
        zoo.feedAll();
        System.out.println("\nAfter eating:");
        for (Animal a : zoo.getHungryAnimals()) {
            if (a.getEnergyLevel() < 30) System.out.println(a.getName() + " hungry: " + a.getEnergyLevel());
            else System.out.println(a.getName() + " not hungry: " + a.getEnergyLevel());
        }

        // Специфические действия льва
        simba.hunt();
        simba.roar();
        simba.listCommands();

        // Специфические действия слона
        dumbo.graze();
        dumbo.trumpet();

        // Поиск животного по имени
        Optional<Animal> found = zoo.findAnimal("Dambo");
        found.ifPresentOrElse(a -> System.out.println("Found: " + a), () -> System.out.println("Animal Not Found"));

        found = zoo.findAnimal("Does not exists");
        found.ifPresentOrElse(a -> System.out.println("Found: " + a), () -> System.out.println("Animal Not Found"));
    }
}