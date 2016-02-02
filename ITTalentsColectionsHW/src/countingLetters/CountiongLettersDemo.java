package countingLetters;

import java.util.*;
import java.util.Map.Entry;


public class CountiongLettersDemo {

	public static void main(String[] args) {
		
		String text = readInput();
		
		HashMap<Character, Integer> repetitions = countReperitions(text.toUpperCase());//Letter -> Repetitions
		
		LinkedHashMap<Character, Integer> sortedRepetitions = sortRepetitions(repetitions);//Letter -> Repetitions
		
		printOutput(sortedRepetitions);

	}
	
	private static void printOutput(LinkedHashMap<Character, Integer> sortedRepetitions) {
		final int maxSymbols = 20;
		//get the biggest value
		int maxRepetitions = sortedRepetitions.values().stream().max(
				(o1, o2) -> o1.compareTo(o2)).get();
		
		for (Entry<Character, Integer> pair : sortedRepetitions.entrySet()) {
			//calculate the trailing symbols count to match the expected output
			//the most common value has the maxSymbols count,
			//others are percentage from the repetitions of the most common value
			double fraction = 1.0 * pair.getValue() / maxRepetitions;
			int symbolCount = (int) (Math.round(fraction * maxSymbols));
			
			System.out.print(pair.getKey() + ": " + pair.getValue() + " ");
			//there must be at least one symbol
			if (symbolCount == 0) {
				symbolCount = 1;
			}
			
			for (int i = 0; i < symbolCount; i++) {
				System.out.print("#");
			}
			
			System.out.println();
		}
		
	}

	private static LinkedHashMap<Character, Integer> sortRepetitions(HashMap<Character, Integer> repetitions) {
		ArrayList<Map.Entry<Character, Integer>> pairs = new ArrayList<>(repetitions.entrySet());
		//Sorting the Entry set with custom comparator
		Collections.sort(pairs,
				(Comparator<? super Map.Entry<Character, Integer>>) (o1, o2) -> {
					return - o1.getValue().compareTo(o2.getValue());});
		//migrating to LinkedHashSet in order to save the sort
		LinkedHashMap<Character, Integer> sortedValues = new LinkedHashMap<>();//Letter -> Repetitions
		
		for (Entry<Character, Integer> entry : pairs) {
			sortedValues.put(entry.getKey(), entry.getValue());
		}
		
		return sortedValues;		
	}

	private static HashMap<Character, Integer> countReperitions(String text) {
		HashMap<Character, Integer> repetitions = new HashMap<>();//Letter -> Repetitions
		//iterating the text char by char to count the repetitions
		for (int letter = 0; letter < text.length(); letter++) {
			char currentLetter = text.charAt(letter);
			if (Character.isAlphabetic(currentLetter)) {
				if (repetitions.containsKey(currentLetter)) {
					repetitions.put(currentLetter, repetitions.get(currentLetter) + 1);
				} else {
					repetitions.put(currentLetter, 1);
				}
			}
		}
		
		return repetitions;
	}

	private static String readInput(){
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Please enter text on single line");
		String text = scanner.nextLine();
		
		scanner.close();
		
		return text;
	}

}
