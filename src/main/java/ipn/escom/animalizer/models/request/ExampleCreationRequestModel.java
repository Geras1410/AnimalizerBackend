package ipn.escom.animalizer.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExampleCreationRequestModel {
    @JsonProperty("imageName")
    @NotEmpty
    private String imageName;

    @JsonProperty("FPred")
    @NotEmpty
    private String FPred;

    @JsonProperty("SPred")
    @NotEmpty
    private String SPred;

    @JsonProperty("TPred")
    @NotEmpty
    private String TPred;
}
