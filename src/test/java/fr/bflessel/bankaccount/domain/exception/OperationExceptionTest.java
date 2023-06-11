package fr.bflessel.bankaccount.domain.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class OperationExceptionTest {

  @Test
  void injectionException_withSerializationError() {
    // GIVEN

    // WHEN
    OperationException injectionException = new OperationException(OperationErrorCode.DEPOSIT_ERROR, "message");

    // THEN
    assertThat(injectionException.getCode()).isEqualTo(500);
    assertThat(injectionException.getMessage()).isEqualTo("Error while making a deposit : message");
  }

  @Test
  void injectionException_withMessageSendError() {
    // GIVEN

    // WHEN
    OperationException injectionException = new OperationException(OperationErrorCode.WITHDRAWAL_ERROR, "message");

    // THEN
    assertThat(injectionException.getCode()).isEqualTo(500);
    assertThat(injectionException.getMessage()).isEqualTo("Error while making a withdrawal : message");
  }

}