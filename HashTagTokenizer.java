
public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		String[] dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		// Your code here
		for (int i = 0; i < dictionary.length; i++) {
			dictionary[i] = in.readString();
		}

		return dictionary;
	}

	public static boolean existInDictionary(String word, String[] dictionary) {
		// Your code here
		if (word.length() != 0) {
			for (int i = 0; i < dictionary.length; i++) {
				// not sure if we are allowed to use word.eqauls()
				// if (word.equals(dictionary[i])) 
				if(stringEquals(word, dictionary[i])) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean stringEquals(String str1, String str2) {
		if (str1.length() != str2.length()) {
			return false;
		}
		if (str1.length() == 0) {
			return true;
		}
		if (str1.charAt(0) != str2.charAt(0)) {
			return false;
		}
		return stringEquals(str1.substring(1), str2.substring(1));
	}

	public static void breakHashTag(String hashtag, String[] dictionary) {

		// Base case: do nothing (return) if hashtag is an empty string.
		if (hashtag.isEmpty()) {
			return;
		}

		// hashtag = hashtag.toLowerCase();

		int N = hashtag.length();

		for (int i = 1; i <= N; i++) {
			if (existInDictionary(hashtag.substring(0, i), dictionary)) {
				System.out.println(hashtag.substring(0, i));
				breakHashTag(hashtag.substring(i), dictionary);
			}
		}
	}

}
