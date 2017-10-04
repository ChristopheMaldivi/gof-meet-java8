package strategy;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Define a family of algorithms, encapsulate each one, and make them interchangeable.
 * Strategy lets the algorithm vary independently from clients that use it.
 */

public class StrategyTest {

  String consoleMessage = "";
  Consumer<String> console = (message) -> consoleMessage = message;

  @Test
  public void an_error_filter_returns_true_if_text_starts_with_ERROR() {
    // given
    String errorMessage = "ERROR - something bad happened";

    // Check doc here: https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html
    // - TYPE: Predicate function which receives a String
    // - LAMBDA: receives a String argument and returns true if text starts with 'ERROR'
    // TYPE listener = LAMBDA
    // ? errorFilter =

    // when
    boolean filtered = false; // => UNCOMMENT errorFilter.?(errorMessage);

    // then
    assertThat(filtered).isTrue();
  }

  @Test
  public void an_error_formatter_returns_the_input_text_in_UPPER_CASE() {
    // given
    String errorMessage = "ERROR - something bad happened";

    // => FILL UNARY OPERATOR TYPE AND LAMBDA FUNCTION BELOW!
    // ? errorFormatter = ?

    // when
    String formattedText = ""; // => UNCOMMENT errorFormatter.?(errorMessage);

    // then
    assertThat(formattedText).isEqualTo("ERROR - SOMETHING BAD HAPPENED");
  }

  @Test
  public void a_text_prefixed_with_ERROR_is_published_in_upper_case() {
    // given
    String message = "ERROR - something bad happened";

    // => FILL PREDICATE TYPE AND LAMBDA FUNCTION BELOW!
    // ? filter = ?
    // => FILL UNARY OPERATOR TYPE AND LAMBDA FUNCTION BELOW!
    // ? formatter = ?

    Publisher publisher = new Publisher();

    // when
    // => UNCOMMENT publisher.publishText(console, message, filter, formatter);

    // then
    assertThat("ERROR - SOMETHING BAD HAPPENED").isEqualTo(consoleMessage);
  }

  @Test
  public void a_text_shorter_than_21_chars_is_published_in_lower_case() {
    // given
    String message = "DEBUG - I'am here";

    // => FILL PREDICATE TYPE AND LAMBDA FUNCTION BELOW!
    // ? filter = ?
    // => FILL UNARY OPERATOR TYPE AND LAMBDA FUNCTION BELOW!
    // ? formatter = ?

    Publisher publisher = new Publisher();

    // when
    // => UNCOMMENT publisher.publishText(console, message, filter, formatter);

    // then
    assertThat("debug - i'am here").isEqualTo(consoleMessage);
  }
}
