package co.bk.javaskills.springrelated;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;


import java.io.IOException;


class TranslationSerializer extends StdSerializer<Translation> {

    public TranslationSerializer() {
        this(null);
    }

    public TranslationSerializer(Class<Translation> t) {
        super(t);
    }

    @Override
    public void serialize(
            Translation value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException {

        /*
          "translations" : [
              {
                "en" : {"text":english translation}
              }, {
                "de" : {"text":deutsch Ubersetzung}
              }
           ]
         */
        String profileString = "{\"text\":%s}";
        jgen.writeStartObject();
        jgen.writeFieldName(value.getLanguage());
        jgen.writeRawValue(String.format(profileString,value.getText()));
        jgen.writeEndObject();
    }
}
