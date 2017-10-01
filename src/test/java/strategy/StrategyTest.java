package strategy;

import org.fest.assertions.Assertions;
import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class StrategyTest {

  String consoleMessage = "";
  Consumer<String> console = (message) -> consoleMessage = message;

  @Test
  public void an_error_filter_returns_true_if_text_starts_with_ERROR() {
    // given
    String errorMessage = "ERROR - something bad happened";
    Predicate<String> errorFilter = (text) -> text.startsWith("ERROR");

    // when
    boolean filtered = errorFilter.test(errorMessage);

    // then
    Assertions.assertThat(filtered).isTrue();
  }

  @Test
  public void an_error_formatter_returns_the_input_text_in_UPPER_CASE() {
    // given
    String errorMessage = "ERROR - something bad happened";
    UnaryOperator<String> errorFormatter = (text) -> text.toUpperCase();

    // when
    String formattedText = errorFormatter.apply(errorMessage);

    // then
    Assertions.assertThat(formattedText).isEqualTo("ERROR - SOMETHING BAD HAPPENED");
  }

  @Test
  public void a_text_prefixed_with_ERROR_is_published_in_upper_case() {
    // given
    String message = "ERROR - something bad happened";
    Predicate<String> filter = (text) -> text.startsWith("ERROR");
    UnaryOperator<String> formatter = (text) -> text.toUpperCase();
    Publisher publisher = new Publisher();

    // when
    publisher.publishText(console, message, filter, formatter);

    // then
    Assertions.assertThat("ERROR - SOMETHING BAD HAPPENED").isEqualTo(consoleMessage);
  }

  @Test
  public void a_text_shorter_than_21_chars_is_published_in_lower_case() {
    // given
    String message = "DEBUG - I'am here";
    Predicate<String> filter = (text) -> text.length() < 21;
    UnaryOperator<String> formatter = (text) -> text.toLowerCase();
    Publisher publisher = new Publisher();

    // when
    publisher.publishText(console, message, filter, formatter);

    // then
    Assertions.assertThat("debug - i'am here").isEqualTo(consoleMessage);
  }
}
