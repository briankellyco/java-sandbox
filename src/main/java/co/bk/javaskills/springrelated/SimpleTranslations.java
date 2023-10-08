package co.bk.javaskills.springrelated;


import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Desired JSON format returned to client:
 *   const TRANSLATIONS = {
       en_US: {
         title: 'Multilingual React Application',
         table_caption: 'Translation Example',
       },
       fr_BE: {
         title: 'Application Multilingue React',
         table_caption: 'Exemple de traduction',
       },
       nl_BE: {
         title: 'Meertalige React Applicatie',
         table_caption: 'Vertaling Voorbeeld',
       },
       zh_CN: {
         title: '多语言 React 申请',
         table_caption: '翻译实例',
       }
     };
 * Frontend needs to easily merge multiple backend calls into an associative array (makes loading of
 * language messages simple):
 *   const EXTRA_TRANSLATION = {
 *     en_UK: {
         title: 'hello',
         table_caption: 'hello',
       }
     };
 * const ALL_TRANSLATIONS = Object.assign({}, TRANSLATIONS, EXTRA_TRANSLATION );
 * console.log(ALL_TRANSLATIONS["en_UK"].title);
 *
 * @see https://stackoverflow.com/questions/14515994/convert-json-string-to-pretty-print-json-output-using-jackson
 */
public class SimpleTranslations {

    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();

        User user = createDummyUser();

        try {
            //Convert object to JSON string and save into file directly
            mapper.writeValue(new File("/tmp/user.json"), user);

            //Convert object to JSON string
            String jsonInString = mapper.writeValueAsString(user);
            System.out.println(jsonInString);

            //Convert object to JSON string and pretty print
            jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);

            System.out.println(jsonInString);


        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static User createDummyUser(){

        SimpleTranslations stj = new SimpleTranslations();
        User user = stj.new User();

        user.setName("bkco");
        user.setAge(29);

        List<String> msg = new ArrayList<>();
        msg.add("item 1");
        msg.add("item 2");
        msg.add("item 3");

        user.setMessages(msg);

        Map<String, Map<String, String>> translations = new HashMap<>();

        Map<String, String> keysEnUS = new HashMap<String, String>() {{
            put("title", "Multilingual React Application");
            put("table_caption", "Translation Example");
        }};
        Map<String, String> keysFrBE = new HashMap<String, String>() {{
            put("title", "Application Multilingue React");
            put("table_caption", "Exemple de traduction");
        }};
        translations.put("en_US", keysEnUS);

        translations.put("fr_BE", keysFrBE);
        user.setTranslations(translations);

        return user;

    }

    public class User {
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

        private String name;

        public Map<String, Map<String,String>> getTranslations() {
            return translations;
        }

        public void setTranslations(Map<String, Map<String,String>> translations) {
            this.translations = translations;
        }

        private int age;
        private List<String> messages;

        private Map<String, Map<String,String>> translations;
    }
}