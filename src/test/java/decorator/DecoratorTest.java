package decorator;

import org.fest.assertions.Delta;
import org.junit.Test;

import java.util.function.DoubleUnaryOperator;
import java.util.stream.Stream;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Attach additional responsibilities to an object dynamically.
 * Decorators provide a flexible alternative to subclassing for extending functionality.
 */

public class DecoratorTest {

  @Test
  public void compute_salary_per_month() {
    // given
    float rawSalary = 30000;
    DoubleUnaryOperator computeSalaryPerMonth = salary -> salary / 12;

    // when
    double salaryPerMonth = computeSalaryPerMonth.applyAsDouble(rawSalary);

    // then
    assertThat(salaryPerMonth).isEqualTo(2500, Delta.delta(0.001f));
  }

  @Test
  public void general_tax_then_regional_tax_then_health_insurance_are_applied_on_salary() {
    // given
    float rawSalary = 30000;

    DoubleUnaryOperator computeSalaryPerMonth = salary -> salary / 12;
    DoubleUnaryOperator lessGeneralTax = salary -> salary * 0.8;
    DoubleUnaryOperator lessRegionalTax = salary -> salary * 0.95;
    DoubleUnaryOperator lessHealthInsurance = salary -> salary - 200;
    DoubleUnaryOperator lessTaxes =
      computeSalaryPerMonth
        .andThen(lessGeneralTax)
        .andThen(lessRegionalTax)
        .andThen(lessHealthInsurance);

    // when
    double salaryPerMonthLessTaxes = lessTaxes.applyAsDouble(rawSalary);

    // then
    assertThat(salaryPerMonthLessTaxes).isEqualTo(1700f, Delta.delta(0.001f));
  }

  @Test
  public void general_tax_then_regional_tax_then_health_insurance_are_applied_on_salary2() {
    // given
    float rawPrice = 30000;

    DoubleUnaryOperator computeSalaryPerMonth = salary -> salary / 12;
    DoubleUnaryOperator lessGeneralTax = salary -> salary * 0.8;
    DoubleUnaryOperator lessRegionalTax = salary -> salary * 0.95;
    DoubleUnaryOperator lessHealthInsurance = salary -> salary - 200;
    Stream<DoubleUnaryOperator> taxes =
      Stream.of(computeSalaryPerMonth, lessGeneralTax, lessRegionalTax, lessHealthInsurance);

    // when
    double salaryWithTaxes = taxes.reduce(
        DoubleUnaryOperator.identity(), DoubleUnaryOperator::andThen)
      .applyAsDouble(rawPrice);

    // then
    assertThat(salaryWithTaxes).isEqualTo(1700, Delta.delta(0.001f));
  }
}
