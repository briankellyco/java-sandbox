package co.bk.javaskills.springrelated;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;


import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://stackoverflow.com/questions/14515994/convert-json-string-to-pretty-print-json-output-using-jackson
 *
 */
public class SerializeToJsonUsingDescriptions {

    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addSerializer(Descriptions.class, new DescriptionsSerializer());
        mapper.registerModule(module);

        Descriptions descriptions = createDummyDescriptions();

        try {
            //Convert object to JSON string and save into file directly
            mapper.writeValue(new File("/tmp/descriptions.json"), descriptions);

            //Convert object to JSON string
            String jsonInString = mapper.writeValueAsString(descriptions);
            System.out.println(jsonInString);

            //databind...
            jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(descriptions);

            System.out.println(jsonInString);


        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static Descriptions createDummyDescriptions(){
        SerializeToJsonUsingDescriptions stj = new SerializeToJsonUsingDescriptions();
        Descriptions descriptions = stj.new Descriptions();
        User user = createDummyUser();
        descriptions.setUser(user);
        return descriptions;
    }

    private static User createDummyUser(){

        SerializeToJsonUsingDescriptions stj = new SerializeToJsonUsingDescriptions();
        User user = stj.new User();

        user.setName("mkyong");
        user.setAge(33);

        List<String> msg = new ArrayList<>();
        msg.add("hello jackson 1");
        msg.add("hello jackson 2");
        msg.add("hello jackson 3");

        user.setMessages(msg);

        Map<String, String> translationsMap = new HashMap<>();
        translationsMap.put("en", "bye!");
        translationsMap.put("de", "ciao!");
        user.setTranslationsMap(translationsMap);

        List<Translation> translations = new ArrayList<>();
        translations.add(new Translation("en", "english translation"));
        translations.add(new Translation("de", "deutsch Ubersetzung"));
        translations.add(new Translation("es", "spainish Ubersetzung"));
        user.setTranslations(translations);

        return user;

    }

    public class User {

        private int age;
        private List<String> messages;

        private String name;

        private Map<String, String> translationsMap;

        private List<Translation> translations;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public List<String> getMessages() {
            return messages;
        }

        public void setMessages(List<String> messages) {
            this.messages = messages;
        }


        public Map<String, String> getTranslationsMap() {
            return translationsMap;
        }

        public void setTranslationsMap(Map<String, String> translationsMap) {
            this.translationsMap = translationsMap;
        }



        public List<Translation> getTranslations() {
            return translations;
        }

        public void setTranslations(List<Translation> translations) {
            this.translations = translations;
        }
    }


    public class Descriptions implements Serializable {

        private static final long serialVersionUID = 3716673948165344321L;

        private User user;

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }
}