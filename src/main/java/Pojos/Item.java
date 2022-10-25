package Pojos;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "cookie",
        "id",
        "prod_id"
})
public class Item {
    @JsonProperty("cookie")
    private String cookie;
    @JsonProperty("id")
    private String id;
    @JsonProperty("prod_id")
    private Integer prodId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("cookie")
    public String getCookie() {
        return cookie;
    }

    @JsonProperty("cookie")
    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("prod_id")
    public Integer getProdId() {
        return prodId;
    }

    @JsonProperty("prod_id")
    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
