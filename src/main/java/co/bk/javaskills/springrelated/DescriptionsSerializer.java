package co.bk.javaskills.springrelated;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;


import java.io.IOException;


class DescriptionsSerializer extends StdSerializer<SerializeToJsonUsingDescriptions.Descriptions> {

    public DescriptionsSerializer() {
        this(null);
    }

    public DescriptionsSerializer(Class<SerializeToJsonUsingDescriptions.Descriptions> t) {
        super(t);
    }

    @Override
    public void serialize(
            SerializeToJsonUsingDescriptions.Descriptions value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException {

        jgen.writeStartObject();

        jgen.writeRawValue("{");
        if (value.getUser() != null && value.getUser().getTranslations() != null) {

            StringBuilder json = new StringBuilder();
            json.append("\"user\":");
            for (Translation translation : value.getUser().getTranslations()) {
                json.append("{\"").append(translation.getLanguage()).append("\": { \"text\":\"").append(translation.getText()).append("\"}");
                jgen.writeRawValue(json.toString());
                jgen.writeRawValue("}");
            }
        }
        jgen.writeRawValue("}");
        jgen.writeEndObject();
    }
}
