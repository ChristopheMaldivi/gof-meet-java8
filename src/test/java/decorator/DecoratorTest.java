package decorator;

import org.fest.assertions.Delta;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
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
    float annualSalary = 30000;

    // => FILL TYPE AND LAMBDA FUNCTION BELOW!
    // ? computeSalaryPerMonth = ?

    // when
    double salaryPerMonth = 0; // => UNCOMMENT! computeSalaryPerMonth.applyAsDouble(annualSalary);

    // then
    assertThat(salaryPerMonth).isEqualTo(2500, Delta.delta(0.001f));
  }

  @Test
  public void general_tax_then_regional_tax_then_health_insurance_are_applied_on_salary() {
    // given
    float annualSalary = 30000;

    // => FILL TYPES AND LAMBDA FUNCTIONS BELOW!
    // ? computeSalaryPerMonth = ?
    // ? lessGeneralTax = // tax *0.8;
    // ? lessRegionalTax = // tax *0.95;
    // ? lessHealthInsurance = // tax -200;
    /* ? lessTaxes =
      computeSalaryPerMonth
        ? then lessGeneralTax
        ? then lessRegionalTax
        ? then lessHealthInsurance */

    // when
    double salaryPerMonthLessTaxes = 0;// => UNCOMMENT! lessTaxes.applyAsDouble(annualSalary);

    // then
    assertThat(salaryPerMonthLessTaxes).isEqualTo(1700f, Delta.delta(0.001f));
  }


  class MutableDouble {
    double d;
    public MutableDouble(double d) {
      this.d = d;
    }
  }

  @Test
  public void general_tax_then_regional_tax_then_health_insurance_are_applied_on_salary2() {
    // given
    double annualSalary = 30000;

    // => FILL TYPES AND LAMBDA FUNCTIONS BELOW!
    // ? computeSalaryPerMonth = ?
    // ? lessGeneralTax = // tax *0.8;
    // ? lessRegionalTax = // tax *0.95;
    // ? lessHealthInsurance = // tax -200;
    //List<?> taxes = // List of lambdas

    // when
    final MutableDouble salaryPerMonthLessTaxes = new MutableDouble(annualSalary);
    //taxes.forEach(?);

    // then
    assertThat(salaryPerMonthLessTaxes.d).isEqualTo(1700, Delta.delta(0.001f));
  }

  @Test
  public void general_tax_then_regional_tax_then_health_insurance_are_applied_on_salary3() {
    // given
    float annualSalary = 30000;

    // => FILL TYPES AND LAMBDA FUNCTIONS BELOW!
    // ? computeSalaryPerMonth = ?
    // ? lessGeneralTax = // tax *0.8;
    // ? lessRegionalTax = // tax *0.95;
    // ? lessHealthInsurance = // tax -200;
    //Stream<?> taxes =
      //Stream.of(computeSalaryPerMonth, lessGeneralTax, lessRegionalTax, lessHealthInsurance);

    // when
    double salaryPerMonthLessTaxes = 0; /* taxes.reduce(
         ?, ?)
      .?(annualSalary); */

    // then
    assertThat(salaryPerMonthLessTaxes).isEqualTo(1700, Delta.delta(0.001f));
  }
}
