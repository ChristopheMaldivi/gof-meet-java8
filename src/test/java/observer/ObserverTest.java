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

    // => ADD LAMBDA LISTENER HERE!
    // LAMBDA: no argument and do: 'called[0] = true'
    MyObservable.EventListener listener = null /* REPLACE null BY LAMBDA */;

    observable.register(listener);

    // when
    observable.event();

    // then
    assertThat(called[0]).isTrue();
  }

  @Test
  public void date_listener_is_called_when_event_occurs_with_current_date() {
    // given
    final Date myDate = new Date(0);
    MyObservable observable = new MyObservable();

    // => FILL TYPE AND LAMBDA LISTENER BELOW!
    // Check doc here: https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html
    // - TYPE: Date Consumer...
    // - LAMBDA: receive a 'date' argument and update myDate with the new date: 'myDate.setTime(date.getTime())'
    // TYPE listener = LAMBDA

    // => ONCE YOU HAVE WRITTEN YOUR LISTENER? UNCOMMENT NEXT LINE AND DO THE IMPLEMENTATION
    //observable.registerDateListener(listener);

    // when
    observable.dateEvent();

    // then
    assertThat(myDate).isNotEqualTo(new Date(0));
  }
}
