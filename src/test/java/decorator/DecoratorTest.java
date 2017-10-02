package decorator;

import org.fest.assertions.Assertions;
import org.fest.assertions.Delta;
import org.junit.Test;

import java.util.function.DoubleUnaryOperator;
import java.util.stream.Stream;

public class DecoratorTest {

  @Test
  public void compute_price_including_general_tax() {
    // given
    float rawPrice = 10;
    DoubleUnaryOperator addGeneralTax = price -> price * (1 + 0.2f);

    // when
    double priceWithGeneralTax = addGeneralTax.applyAsDouble(rawPrice);

    // then
    Assertions.assertThat(priceWithGeneralTax).isEqualTo(12, Delta.delta(0.001f));
  }

  @Test
  public void general_tax_then_regional_tax_then_health_insurance_are_applied_on_price() {
    // given
    float rawPrice = 10;

    DoubleUnaryOperator addGeneralTax = price -> price * (1 + 0.2f);
    DoubleUnaryOperator addRegionalTax = price -> price * (1 + 0.1f);
    DoubleUnaryOperator addHealthInsurance = price -> price * (1 + 0.05f);
    DoubleUnaryOperator addTaxes =
      addGeneralTax
        .andThen(addRegionalTax)
        .andThen(addHealthInsurance);

    // when
    double priceWithTaxes = addTaxes.applyAsDouble(rawPrice);

    // then
    Assertions.assertThat(priceWithTaxes).isEqualTo(13.86f, Delta.delta(0.001f));
  }

  @Test
  public void general_tax_then_regional_tax_then_health_insurance_are_applied_on_price2() {
    // given
    float rawPrice = 10;

    DoubleUnaryOperator addGeneralTax = price -> price * (1 + 0.2f);
    DoubleUnaryOperator addRegionalTax = price -> price * (1 + 0.1f);
    DoubleUnaryOperator addHealthInsurance = price -> price * (1 + 0.05f);
    Stream<DoubleUnaryOperator> taxes =
      Stream.of(addGeneralTax, addRegionalTax, addHealthInsurance);

    // when
    double priceWithTaxes = taxes.reduce(
        DoubleUnaryOperator.identity(), DoubleUnaryOperator::andThen)
      .applyAsDouble(rawPrice);

    // then
    Assertions.assertThat(priceWithTaxes).isEqualTo(13.86f, Delta.delta(0.001f));
  }
}
