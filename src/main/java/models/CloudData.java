package models;

import java.util.Map;
import java.util.Optional;

public class CloudData {
    private Map<String, String> fields;

    public CloudData(Map<String, String> fields) {
        this.fields = fields;
    }

    public Map<String, String> getFields() {
        return fields;
    }

    public void setFields(Map<String, String> fields) {
        this.fields = fields;
    }

    public String getField(String field) {
        return Optional.of(this.fields.get(field)).orElse(null);
    }
}
