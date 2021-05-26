package com.acme.banking.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import org.openapitools.jackson.nullable.JsonNullable;

/**
 * Set of elements used to provide details of a generic date time for the statement resource.
 */
@ApiModel(description = "Set of elements used to provide details of a generic date time for the statement resource.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-05-25T16:20:36.388570+02:00[Europe/Paris]")
public class OBStatement2StatementDateTime   {
  @JsonProperty("DateAndTime")
  private OffsetDateTime dateAndTime = null;

  @JsonProperty("Type")
  private String type;

  public OBStatement2StatementDateTime dateAndTime(OffsetDateTime dateAndTime) {
    this.dateAndTime = dateAndTime;
    return this;
  }

  /**
   * Get dateAndTime
   * @return dateAndTime
  */
  @ApiModelProperty(required = true, value = "")
  public OffsetDateTime getDateAndTime() {
    return dateAndTime;
  }

  public void setDateAndTime(OffsetDateTime dateAndTime) {
    this.dateAndTime = dateAndTime;
  }

  public OBStatement2StatementDateTime type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Date time type, in a coded form.
   * @return type
  */
  @ApiModelProperty(required = true, value = "Date time type, in a coded form.")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OBStatement2StatementDateTime obStatement2StatementDateTime = (OBStatement2StatementDateTime) o;
    return Objects.equals(this.dateAndTime, obStatement2StatementDateTime.dateAndTime) &&
        Objects.equals(this.type, obStatement2StatementDateTime.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dateAndTime, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OBStatement2StatementDateTime {\n");
    
    sb.append("    dateAndTime: ").append(toIndentedString(dateAndTime)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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

