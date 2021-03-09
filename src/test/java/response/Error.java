package response;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.List;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
        "Error"
})

public class Error {

    @JsonProperty("Error")
    private String error;

    @JsonProperty("Error")
    public String getError() {
        return error;
    }
}
