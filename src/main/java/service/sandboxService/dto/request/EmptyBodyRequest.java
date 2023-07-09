package service.sandboxService.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(buildMethodName = "please", setterPrefix = "with")
@JsonIgnoreProperties
public class EmptyBodyRequest {

}