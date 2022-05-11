package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.PetObject;

import java.util.Arrays;

public interface ToFromJsonGenerator {

    //generate json from Pet object
    default String ToJson(PetObject petObject) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(petObject);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }

    }

    // generate Pet object from json
    default PetObject ToJava(String s) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return objectMapper.readValue(s, PetObject.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    // generate array from json data
    default PetObject[] JsonToArray(String s) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return objectMapper.readValue(s, PetObject[].class);
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    //check if PetObject's array contains id
    default Boolean ifIdInArray(PetObject[] petArr, String id) {
        return Arrays.stream(petArr).anyMatch(s -> s.toString().contains(id));
    }
}
