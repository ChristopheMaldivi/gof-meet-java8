package strategy;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

class Publisher {

  public void publishText(Consumer<String> console, String message, Predicate<String> filter, UnaryOperator<String> formatter) {
    if (filter.test(message)) {
      console.accept(formatter.apply(message));
    }
  }
}
