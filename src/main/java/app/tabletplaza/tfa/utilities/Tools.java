package app.tabletplaza.tfa.utilities;

import com.orhanobut.logger.Logger;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by SolbadguyKY on 21-Jan-17.
 */

public class Tools {

    // Pattern for recognizing a URL, based off RFC 3986
    private static final Pattern urlPattern = Pattern.compile(
            "(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)"
                    + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
                    + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)",
            Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

    /**
     * Returns a list with all links contained in the input
     */
    public static List<String> extractUrls(String text) throws NullPointerException {
        if (text == null) {
            throw new NullPointerException();
        }
        List<String> containedUrls = new ArrayList<String>();
        String urlRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
        Matcher urlMatcher = pattern.matcher(text);

        while (urlMatcher.find()) {
            containedUrls.add(text.substring(urlMatcher.start(0),
                    urlMatcher.end(0)));
        }

        return containedUrls;
    }

    public static final ArrayList<String> ImageSupportedTypes = new ArrayList<>(Arrays.asList("jpg", "jpeg", "png"));

    public static String md5(String s) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.update(s.getBytes(Charset.forName("US-ASCII")), 0, s.length());
            byte[] magnitude = digest.digest();
            BigInteger bi = new BigInteger(1, magnitude);
            return String.format("%0" + (magnitude.length << 1) + "x", bi);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getImageFromString(String rawString) {
        try {
            List<String> urlList = Tools.extractUrls(rawString);
            Logger.d(urlList);
            int count = 0;
            while (count < urlList.size()) {
                for (String supportedType : Tools.ImageSupportedTypes) {
                    if (urlList.get(count).contains(supportedType)) {
                        return urlList.get(count);
                    }
                }
                count++;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return null;
    }
}
