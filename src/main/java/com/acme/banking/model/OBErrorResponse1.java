package com.acme.banking.model;

import java.util.Objects;
import com.acme.banking.model.OBError1;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;

/**
 * An array of detail error codes, and messages, and URLs to documentation to help remediation.
 */
@ApiModel(description = "An array of detail error codes, and messages, and URLs to documentation to help remediation.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-05-25T16:20:36.388570+02:00[Europe/Paris]")
public class OBErrorResponse1   {
  @JsonProperty("Code")
  private String code;

  @JsonProperty("Id")
  private String id;

  @JsonProperty("Message")
  private String message;

  @JsonProperty("Errors")
  
  private List<OBError1> errors = new ArrayList<>();

  public OBErrorResponse1 code(String code) {
    this.code = code;
    return this;
  }

  /**
   * High level textual error code, to help categorize the errors.
   * @return code
  */
  @ApiModelProperty(required = true, value = "High level textual error code, to help categorize the errors.")
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public OBErrorResponse1 id(String id) {
    this.id = id;
    return this;
  }

  /**
   * A unique reference for the error instance, for audit purposes, in case of unknown/unclassified errors.
   * @return id
  */
  @ApiModelProperty(value = "A unique reference for the error instance, for audit purposes, in case of unknown/unclassified errors.")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public OBErrorResponse1 message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Brief Error message, e.g., 'There is something wrong with the request parameters provided'
   * @return message
  */
  @ApiModelProperty(required = true, value = "Brief Error message, e.g., 'There is something wrong with the request parameters provided'")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public OBErrorResponse1 errors(List<OBError1> errors) {
    this.errors = errors;
    return this;
  }

  public OBErrorResponse1 addErrorsItem(OBError1 errorsItem) {
    this.errors.add(errorsItem);
    return this;
  }

  /**
   * Get errors
   * @return errors
  */
  @ApiModelProperty(required = true, value = "")
  public List<OBError1> getErrors() {
    return errors;
  }

  public void setErrors(List<OBError1> errors) {
    this.errors = errors;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OBErrorResponse1 obErrorResponse1 = (OBErrorResponse1) o;
    return Objects.equals(this.code, obErrorResponse1.code) &&
        Objects.equals(this.id, obErrorResponse1.id) &&
        Objects.equals(this.message, obErrorResponse1.message) &&
        Objects.equals(this.errors, obErrorResponse1.errors);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, id, message, errors);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OBErrorResponse1 {\n");
    
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    errors: ").append(toIndentedString(errors)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

