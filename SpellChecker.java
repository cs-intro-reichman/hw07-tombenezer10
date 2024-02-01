
public class SpellChecker {

	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);

	}

	public static String tail(String str) {
		// Your code goes here
		return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) {
		// Your code goes here
		String a = word1.toLowerCase();
		String b = word2.toLowerCase();
		int lenA = a.length();
		int lenB = b.length();

		if (lenB == 0) {
			return lenA;
		} else if (lenA == 0) {
			return lenB;
		} else if (a.charAt(0) == b.charAt(0)) {
			return levenshtein(tail(a), tail(b));
		} else {
			int temp = Math.min(levenshtein(tail(a), b), levenshtein(a, tail(b)));
			return 1 + Math.min(temp, levenshtein(tail(a), tail(b)));
		}

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

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		// Your code goes here
		int minEditDistance = levenshtein(word, dictionary[0]);
		String res = "";
		for (int i = 1; i < dictionary.length; i++) {
			int editDistance = levenshtein(word, dictionary[i]);
			if (editDistance < minEditDistance) {
				minEditDistance = editDistance;
				res = dictionary[i];
			}
		}
		return (minEditDistance > threshold ? word : res);
	}

}
