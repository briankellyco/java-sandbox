package co.bk.javaskills.basics.regex;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringMatcherTest {
    private StringMatcher m;

    @BeforeEach
    public void setup(){
        m = new StringMatcher();
    }


    @Test
    public void testRecursiveReplace() {

        String s = "hehheh###toDate###mymy###toDate###hehheh###fromDate###mymy";

        Map targets = new HashMap<String, String>();
        targets.put("fromDate", "2017-01-01");
        targets.put("toDate", "2018-12-12");

        String replaced = m.recursiveReplace(s, targets);
        assertEquals("hehheh2018-12-12mymy2018-12-12hehheh2017-01-01mymy", replaced);
    }


    @Test
    public void testStringIsUUID() {

        String s = "007827c3-8cfa-4d79-a5c3-8802a9b84581";

        assertTrue(s.matches(StringMatcher.regexUUID));
    }

    @Test
    public void testExtractHtmlTag() {


        String s = "<outerTag> heh heh <innerTag>my my</innerTag> </outerTag>";

        List<String> result = m.extractHtmlTagContent("innerTag", s);

        assertEquals("my my", result.get(0));
    }

    @Test
    public void testRegex() {

        //assertTrue(s.matches(".-{4}"));
        //assertTrue(s.matches(".*(-).*")); // simple works
        //assertTrue(s.matches(".*([\\w]){5}")); // wrong!

        //assertTrue(s.matches("(?i)\\w-")); // js style /-/gi NOPE
    }



//    @Test
//    public void testIsTrue() {
//        assertTrue(m.isTrue("true"));
//        assertFalse(m.isTrue("true2"));
//        assertFalse(m.isTrue("True"));
//    }

//    @Test
//    public void testIsTrue() {
//        assertTrue(m.isTrue("true"));
//        assertFalse(m.isTrue("true2"));
//        assertFalse(m.isTrue("True"));
//    }
//
//    @Test
//    public void testIsTrueVersion2() {
//        assertTrue(m.isTrueVersion2("true"));
//        assertFalse(m.isTrueVersion2("true2"));
//        assertTrue(m.isTrueVersion2("True"));;
//    }
//
//    @Test
//    public void testIsTrueOrYes() {
//        assertTrue(m.isTrueOrYes("true"));
//        assertTrue(m.isTrueOrYes("yes"));
//        assertTrue(m.isTrueOrYes("Yes"));
//        assertFalse(m.isTrueOrYes("no"));
//    }
//
//    @Test
//    public void testContainsTrue() {
//        assertTrue(m.containsTrue("thetruewithin"));
//    }
//
//    @Test
//    public void testIsThreeLetters() {
//        assertTrue(m.isThreeLetters("abc"));
//        assertFalse(m.isThreeLetters("abcd"));
//    }
//
//    @Test
//    public void testisNoNumberAtBeginning() {
//        assertTrue(m.isNoNumberAtBeginning("abc"));
//        assertFalse(m.isNoNumberAtBeginning("1abcd"));
//        assertTrue(m.isNoNumberAtBeginning("a1bcd"));
//        assertTrue(m.isNoNumberAtBeginning("asdfdsf"));
//    }
//
//    @Test
//    public void testisIntersection() {
//        assertTrue(m.isIntersection("1"));
//        assertFalse(m.isIntersection("abcksdfkdskfsdfdsf"));
//        assertTrue(m.isIntersection("skdskfjsmcnxmvjwque484242"));
//    }
//
//    @Test
//    public void testLessThenThreeHundred() {
//        assertTrue(m.isLessThenThreeHundred("288"));
//        assertFalse(m.isLessThenThreeHundred("3288"));
//        assertFalse(m.isLessThenThreeHundred("328 8"));
//        assertTrue(m.isLessThenThreeHundred("1"));
//        assertTrue(m.isLessThenThreeHundred("99"));
//        assertFalse(m.isLessThenThreeHundred("300"));
//    }

}