package org.cloudbus.cloudsim.api;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexConstant {
    public static Matcher getMatcher(String regex, String input) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }

    public static boolean hasPattern(String regex, String input) {
        Matcher matcher = getMatcher(regex, input);
        return matcher.find();
    }
}
