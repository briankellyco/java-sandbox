package co.bk.javaskills.springrelated;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;
import lombok.Value;


import java.io.Serializable;

/**
 * Model class to hold ContactDetails Information.
 */
@Value
@JsonDeserialize(builder = Translation.TranslationBuilder.class)
@JsonSerialize(using = TranslationSerializer.class)
public class Translation implements Serializable {
    private static final long serialVersionUID = 2716673948165345541L;

    private String language; //en,de,fr
    private String text;

    public Translation(String language, String text) {
        this.language = language;
        this.text = text;
    }


    @JsonPOJOBuilder(withPrefix = "")
    public static final class TranslationBuilder {
    }

    public String getLanguage() {
        return this.language;
    }

    public String getText() {
        return this.text;
    }
}
