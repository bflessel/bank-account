package fr.bflessel.bankaccount.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import fr.bflessel.bankaccount.domain.exception.OperationException;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SumTest {

  private static Stream<Arguments> sumValues() {
    return Stream.of(
        Arguments.of(10.0, 0.0),
        Arguments.of(5000.0, 250.0),
        Arguments.of(10.0, -200.0)
    );
  }

  @ParameterizedTest
  @MethodSource("sumValues")
  void should_deposit_Amounts(Double deposit, Double baseSum) throws OperationException {
    // GIVEN
    Sum sum = Sum.of(baseSum);
    // WHEN + THEN
    assertThat(sum.add(deposit)).isEqualTo(new Sum(baseSum + deposit));
  }

  @Test
  void should_make_a_negative_substract() {
    // WHEN + THEN
    assertThatThrownBy(() -> Sum.of(0.0).subtract(-1.0))
        .isExactlyInstanceOf(OperationException.class)
        .hasMessage("Error while making a withdrawal : Can't make a negative operation as "+ -1.0);
  }

  @ParameterizedTest
  @MethodSource("sumValues")
  void should_substract_Amounts(Double deposit, Double baseSum) throws OperationException {
    // GIVEN
    Sum sum = Sum.of(baseSum);
    // WHEN + THEN
    assertThat(sum.subtract(deposit)).isEqualTo(new Sum(baseSum - deposit));
  }

  @Test
  void should_make_a_negative_deposit() {
    // WHEN + THEN
    assertThatThrownBy(() -> Sum.of(0.0).add(-1.0))
        .isExactlyInstanceOf(OperationException.class)
        .hasMessage("Error while making a deposit : Can't make a negative operation as "+ -1.0);
  }

}