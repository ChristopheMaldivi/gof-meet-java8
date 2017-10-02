package observer;

import org.junit.Test;

import java.util.Date;
import java.util.function.Consumer;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Define a one-to-many dependency between objects so that when one object changes
 * state, all its dependents are notified and updated automatically.
 */

public class ObserverTest {

  @Test
  public void listener_is_called_when_event_occurs() {
    // given
    final Boolean[] called = new Boolean[1];
    MyObservable observable = new MyObservable();
    MyObservable.EventListener listener = () -> called[0] = true;

    observable.register(listener);

    // when
    observable.event();

    // then
    assertThat(called[0]).isTrue();
  }

  @Test
  public void date_listener_is_called_when_event_occurs() {
    // given
    final Date date = new Date(0);
    MyObservable observable = new MyObservable();
    Consumer<Date> listener = (d) -> date.setTime(d.getTime());

    observable.registerDateListener(listener);

    // when
    observable.dateEvent();

    // then
    assertThat(date).isNotEqualTo(new Date(0));
  }
}
