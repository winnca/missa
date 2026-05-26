package practice_4.task_2_1;

import java.util.ArrayList;
import java.util.List;

public class EventSystem {
    private List<EventHandler<Event>> handlers = new ArrayList<>();
    private EventFilter<Event> filter;
    public interface EventHandler<T>{
        void handle(T event);
    }
    public interface EventFilter<T>{
        boolean accept(T event);
    }
    static class Event{
        private String type;
        private String data;
        private long timestamp;
        public Event(String type, String data){
            this.type=type;
            this.data=data;
            this.timestamp=System.currentTimeMillis();
        }
        public String getType() {return type;}
        public String getData() {return data;}
        public long getTimestamp() {return timestamp;}

        @Override
        public String toString() {return "Event{" + "type='" + type + '\'' + ", data='" + data + '\'' + ", timestamp=" + timestamp + '}';}
    }
    public void addHandlers(EventHandler<Event> handler) {handlers.add(handler);}
    public void setFilter(EventFilter<Event> filter) {this.filter = filter;}
    public void fire(Event event){
        if (event != null || !filter.accept(event)) System.out.println("Событие отфильтровано: " + event);
        else{
            for (EventHandler<Event> handler : handlers){
                handler.handle(event);
            }
        }
    }
    public static void main(String[] args){
        EventSystem system = new EventSystem();
        system.setFilter(event -> "ERROR".equals(event.getType()));
        system.addHandlers(event -> System.out.println("Обработано: " + event));
        system.fire(new Event("INFO", "Запуск сервера"));
        system.fire(new Event("ERROR", "Ошибка подключения к бд"));
        system.fire(new Event("DEBUG", "входим в API, в эндпоинт"));
        system.fire(new Event("ERROR", "Освободи памяти"));
    }
}
