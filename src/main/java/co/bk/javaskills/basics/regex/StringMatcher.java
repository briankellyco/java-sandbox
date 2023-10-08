package co.bk.javaskills.basics.regex;

import static java.lang.String.format;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Reference
 *   http://www.vogella.com/tutorials/JavaRegularExpressions/article.html
 */
public class StringMatcher {

    protected static final String regexUUID = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";

    /**
     * Method extracts the content of a pair of matching HTML tags. Or content of repeating tags.
     *
     * For example Searching for "innerTag" will return "my my" text.
     * <outerTag>
     *     heh heh
     *     <innerTag>my my</innerTag>
     * </outerTag>
     *
     * @param tagNameNoBracket name of tag without its brackets
     * @param text the string to be searched
     * @return list of items found.
     */
    protected static List<String> extractHtmlTagContent(final String tagNameNoBracket, final String text) {

        final String regex = format("<%s>(.+?)</%s>", tagNameNoBracket, tagNameNoBracket);
        final Pattern PATTERN_TAG_REGEX = Pattern.compile(regex);

        final List<String> items = new ArrayList<String>();
        final Matcher matcher = PATTERN_TAG_REGEX.matcher(text);
        while (matcher.find()) {
            items.add(matcher.group(1));
        }
        return items;
    }


    /**
     * Method recursively replaces a set of strings in a target text. A regular recursive function
     * will have a method signature similar to:
     *   public String recursiveReplace(String in, String target, String replacement)
     *
     * This method combines the search target and replacement value to insert in a Map.
     *
     * @param in
     * @param targets
     * @return
     */
    public String recursiveReplace(String in, Map<String, String> targets) {
        if (targets.isEmpty()) {
            return in;
        }

        Map.Entry<String, String> entry = targets.entrySet().iterator().next();
        String updated = in.replaceAll(Pattern.quote("###" + entry.getKey() + "###"), entry.getValue());
        targets.remove(entry.getKey());
        return recursiveReplace(updated, targets);
    }



    // returns true if the string matches exactly "true"
    public boolean isTrue(String s){
        return s.matches("true");
    }
    // returns true if the string matches exactly "true" or "True"
    public boolean isTrueVersion2(String s){
        return s.matches("[tT]rue");
    }

    // returns true if the string matches exactly "true" or "True"
    // or "yes" or "Yes"
    public boolean isTrueOrYes(String s){
        return s.matches("[tT]rue|[yY]es");
    }

    // returns true if the string contains exactly "true"
    public boolean containsTrue(String s){
        return s.matches(".*true.*");
    }


    // returns true if the string contains of three letters
    public boolean isThreeLetters(String s){
        return s.matches("[a-zA-Z]{3}");
    }



    // returns true if the string does not have a number at the beginning
    public boolean isNoNumberAtBeginning(String s){
        return s.matches("^[^\\d].*");
    }
    // returns true if the string contains a arbitrary number of characters except b
    public boolean isIntersection(String s){
        return s.matches("([\\w&&[^b]])*");
    }
    // returns true if the string contains a number less then 300
    public boolean isLessThenThreeHundred(String s){
        return s.matches("[^0-9]*[12]?[0-9]{1,2}[^0-9]*");
    }

}