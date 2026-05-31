package practice_5.task_1_1;

import java.util.*;

public class TaskManager {
    ArrayList<String> taskList;
    public TaskManager(){this.taskList = new ArrayList<>();}
    public void addTask(String task){ taskList.add(task);}
    public void addTaskPosition(String task, int position){ // недопустимый индекс = который не укладывается в диапазон arraylist
        if (position >= 0 && position <= taskList.size()) taskList.add(position, task);
        else taskList.add(task);
    }
    public boolean removeTask(String task){return taskList.remove(task);}
    public String getTask(int index){
        try{
            return taskList.get(index);
        } catch (IndexOutOfBoundsException e){
            //System.out.println("Вышли за границы массива" + e);
            return null;
        }
    }
    public void searchTasks(String keyword){
        if (keyword == null || keyword.isEmpty()) return;
        String keywordHelp = keyword.toLowerCase();
        for (String s : taskList){
            if (s.toLowerCase().contains(keywordHelp)) System.out.println(s+"\n");
        }
    }
    public void sortTasks(){ Collections.sort(taskList);}
    public void printAll(){
        if (taskList.isEmpty()) System.out.println("Список пуст");
        else{
            for (int i=0;i<taskList.size();i++){
                System.out.println((i+1) + " " + taskList.get(i));
            }
        }
        System.out.println();
    }
}

class Main{
    public static void main(String[] args){
        TaskManager taskManager = new TaskManager();

        System.out.println("Выведем все задачи");
        taskManager.printAll();
        taskManager.addTask("Сделать домашку");
        taskManager.addTask("Погулять с собакой");
        taskManager.addTask("Покормить домашних животных");
        taskManager.addTask("Отправить Тест преподавателю");

        System.out.println("\nВыведем все задачи");
        taskManager.printAll();

        System.out.println("\nВставьте срочную задачу в начало");
        taskManager.addTaskPosition("Сдать дз сегодня по Java", 0);
        taskManager.printAll();

        System.out.println("\nНайдите по ключевому слову «тест»");
        taskManager.searchTasks("тест");
        taskManager.printAll();

        System.out.println("\nСортировка");
        taskManager.sortTasks();
        System.out.println("Выведем все задачи");
        taskManager.printAll();
    }
}