package spring.util;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class SlugUtil {

	public static String makeSlug(String title) {
		String slug = Normalizer.normalize(title, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombining DiacriticalMarks}+");
		slug = pattern.matcher(slug).replaceAll("");
		slug = slug.toLowerCase();
		// Replace 'd' to 'd'
		slug = slug.replaceAll("đ", "d");
		// Delete special
		slug = slug.replaceAll("([^0-9a-z-\\s])", "");
		// Replace 'space' to '-'
		slug = slug.replaceAll("[\\s]", "-");
		// Converts multiple characters' consecutively to 1 character '-'
		slug = slug.replaceAll("(-+)", "-");
		// Delete characters '-' at the begin and the end
		slug = slug.replaceAll("^-+", "");
		slug = slug.replaceAll("-+$", "");
		return slug;

	}
}
