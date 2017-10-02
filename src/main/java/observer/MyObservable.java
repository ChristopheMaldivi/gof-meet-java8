package observer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

class MyObservable {

  interface EventListener {
    void onEvent();
  }

  private List<Consumer<Date>> dateListeners = new ArrayList<>();
  private List<EventListener> listeners = new ArrayList<>();

  void register(EventListener listener) {
    listeners.add(listener);
  }

  void registerDateListener(Consumer<Date> listener) {
    dateListeners.add(listener);
  }

  void event() {
    listeners.forEach(listener -> listener.onEvent());
  }

  void dateEvent() {
    dateListeners.forEach(dateListener -> dateListener.accept(new Date()));
  }
}
