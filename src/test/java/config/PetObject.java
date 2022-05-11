package config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PetObject {
    @JsonProperty("id")
    public String id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("status")
    public String status;


    public PetObject()
    {

    }
    public PetObject(String id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;

    }




}
