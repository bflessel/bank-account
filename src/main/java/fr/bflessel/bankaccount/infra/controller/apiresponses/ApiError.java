package fr.bflessel.bankaccount.infra.controller.apiresponses;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(name = "Erreur")
public class ApiError {

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
  private final LocalDateTime timestamp;

  private final String message;

  private final String debugMessage;

  public ApiError(String message, Throwable ex) {
    this.timestamp = LocalDateTime.now();
    this.message = message;
    this.debugMessage = ex.getLocalizedMessage();
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public String getMessage() {
    return message;
  }

  public String getDebugMessage() {
    return debugMessage;
  }
}