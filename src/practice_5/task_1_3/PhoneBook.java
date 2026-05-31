package practice_5.task_1_3;

import java.util.*;

public class PhoneBook {
    private TreeMap<String, List<String>> contacts;
    public PhoneBook(){this.contacts=new TreeMap<>();}
    public void addContact(String name, String phone){
        if (name == null || phone == null || name.isEmpty() || phone.isEmpty()) return;
        contacts.computeIfAbsent(name, el -> new ArrayList<>()).add(phone);
    }
    public List<String> findByName(String name){
        if (name == null || name.isEmpty() || !contacts.containsKey(name)) {
            System.out.println("Список пуст");
            return new ArrayList<>();
        }
        return contacts.get(name);
    }
    public Map<String, List<String>> searchByName(String partialName){
        if (partialName == null || partialName.isEmpty()) {
            System.out.println("Пуст");
            return new HashMap<>();
        }
        Map<String, List<String>> map = new HashMap<>();
        String help = partialName.toLowerCase();
        for (Map.Entry<String, List<String>> entry : contacts.entrySet()){
            if (entry.getKey().toLowerCase().contains(help)) map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }
    public boolean removePhone(String name, String phone){
        if (name == null || phone == null || name.isEmpty() || phone.isEmpty() || !contacts.containsKey(name)){
            System.out.println("Некорректные данные");
            return false;
        }
        List<String> phones = contacts.get(name);
        boolean flag = phones.remove(phone);
        if (flag && phones.isEmpty()) {
            contacts.remove(name);
        }
        return flag;
    }
    public List<String> findDuplicatePhones() {
        List<String> allPhones = new ArrayList<>();
        for (List<String> phoneList : contacts.values()) {
            allPhones.addAll(phoneList);
        }
        List<String> duplicates = new ArrayList<>();

        for (int i = 0; i < allPhones.size(); i++) {
            String currentPhone = allPhones.get(i);
            if (currentPhone.isEmpty()) continue;
            if (duplicates.contains(currentPhone)) continue;
            int count = 1;
            for (int j = i + 1; j < allPhones.size(); j++) {
                if (currentPhone.equals(allPhones.get(j))) {
                    count++;
                }
            }
            if (count > 1) {
                duplicates.add(currentPhone);
            }
        }
        for (int i = 0; i < duplicates.size() - 1; i++) {
            for (int j = 0; j < duplicates.size() - i - 1; j++) {
                if (duplicates.get(j).compareTo(duplicates.get(j + 1)) > 0) {
                    String temp = duplicates.get(j);
                    duplicates.set(j, duplicates.get(j + 1));
                    duplicates.set(j + 1, temp);
                }
            }
        }
        return duplicates;
    }
    public void printStats() {
        int countPhones = 0;
        for (List<String> list : contacts.values()) {
            countPhones += list.size();
        }
        double averagePhones = contacts.isEmpty() ? 0 : (double) countPhones / contacts.size();
        System.out.println("Всего контактов: " + contacts.size());
        System.out.println("Всего номеров: " + countPhones);
        System.out.println("Среднее количество номеров на контакт:" + averagePhones);
    }
    public void printAll(){
        if (contacts == null || contacts.isEmpty()){
            System.out.println("Заполни контакты");
            return;
        }
        for (Map.Entry<String, List<String>> entry: contacts.entrySet()) System.out.println(entry.getKey() + " " + entry.getValue());
        System.out.println();
    }
}

class Main{
    public static void main(String[] args){
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.addContact("Александр Винницкий", "+7 (926) 777-88-99");
        phoneBook.addContact("Александр Винницкий", "+7 (926) 777-17-99");
        phoneBook.addContact("Екатерина Пушкарёва", "+7 (926) 777-88-13");
        phoneBook.addContact("Виктория Руденко", "+7 (916) 888-90-32");

        phoneBook.addContact("Анна Хилькевич", "+7 (926) 777-88-13");
        phoneBook.addContact("Максимилиан", "+7 (916) 333-90-32");

        System.out.println("Выведем всех");
        phoneBook.printAll();

        System.out.println("Поиск по имени");
        Map<String, List<String>> map = phoneBook.searchByName("анна");
        System.out.println(map + "\n");

        System.out.println("Поиск дубликатов");
        System.out.println(phoneBook.findDuplicatePhones());

        System.out.println("\nВывод статистики");
        phoneBook.printStats();

        System.out.println("\nУдаляем номер");
        phoneBook.removePhone("Анна Хилькевич", "+7 (926) 777-88-13");

        System.out.println("\nВыведем всех");
        phoneBook.printAll();
    }
}