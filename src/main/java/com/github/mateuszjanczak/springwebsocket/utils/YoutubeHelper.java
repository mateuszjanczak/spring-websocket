package com.github.mateuszjanczak.springwebsocket.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YoutubeHelper {

    final static String youtubeRegex = "(?:https?:\\/\\/)?(?:(?:(?:www\\.?)?youtube\\.com(?:\\/(?:(?:watch\\?.*?(v=[^&\\s]+).*)|(?:v(\\/.*))|(channel\\/.+)|(?:user\\/(.+))|(?:results\\?(search_query=.+))))?)|(?:youtu\\.be(\\/.*)?))";

    public static String extractVideoIdFromUrl(String url) {
        Pattern pattern = Pattern.compile(youtubeRegex);
        Matcher matcher = pattern.matcher(url);
        while (matcher.find()) {
            return matcher.group(6);
        }
        return "";
    }
}