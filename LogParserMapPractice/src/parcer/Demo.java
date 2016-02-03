package parcer;

import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo {

	public static void main(String[] args) {

		LinkedHashMap<String, ArrayList<ArrayList<String>>> logs = 
				new LinkedHashMap<String, ArrayList<ArrayList<String>>>();//Name -> List critical errors, List warnings
		
		Scanner scanner = new Scanner(System.in);
		
		String line = scanner.nextLine();
		Pattern pattern = Pattern.compile(
				"\\{\"Project\": \\[\"(.+?)\"\\], \"Type\": \\[\"(Critical|Warning)\"\\], \"Message\": \\[\"(.+?)\"\\]\\}");
		
		while (!line.equals("END")) {
			Matcher matcher = pattern.matcher(line);
			
			if (matcher.find()) {
				String projectName = matcher.group(1);
				String errorType = matcher.group(2);
				String errorMessage = matcher.group(3);
				
				if (!logs.containsKey(projectName)) {
					logs.put(projectName, new ArrayList<ArrayList<String>>());
					logs.get(projectName).add(new ArrayList<String>());
					logs.get(projectName).add(new ArrayList<String>());
				}
			
				if (errorType.equals("Critical")) {
					logs.get(projectName).get(0).add(errorMessage);
				} else if (errorType.equals("Warning")) {
					logs.get(projectName).get(1).add(errorMessage);
				}
			}
			
			line = scanner.nextLine();
		}

		scanner.close();
		
		sortLogs(logs);

		printLogs(logs);
		
	}

	private static void printLogs(LinkedHashMap<String, ArrayList<ArrayList<String>>> logs) {
//		ProjectName:
//		Total Errors: {total number of errors}
//		Critical: {total number of critical errors}
//		Warnings: {total number of warnings}
//		Critical Messages:
//		--->{message of critical error No. 1}
//		--->{message of critical error No.2}
//			…
//		Warning Messages:
//		--->{message of warning No. 1}
//		--->{message of warning No. 2}
		for (Map.Entry<String, ArrayList<ArrayList<String>>> pair : logs.entrySet()) {
			System.out.println(pair.getKey() + ":");
			System.out.println("Total Errors: " + (pair.getValue().get(0).size() + pair.getValue().get(1).size()));
			System.out.println("Critical: " + pair.getValue().get(0).size());
			System.out.println("Warnings: " + pair.getValue().get(1).size());
			
			System.out.println("Critical Messages:");
			if (pair.getValue().get(0).size() == 0) {
				System.out.println("--->None");
			}
			
			for (int i = 0; i < pair.getValue().get(0).size(); i++) {
				System.out.println("--->" + pair.getValue().get(0).get(i));
			}
			
			System.out.println("Warning Messages:");
			if (pair.getValue().get(1).size() == 0) {
				System.out.println("--->None");
			}
			
			for (int i = 0; i < pair.getValue().get(1).size(); i++) {
				System.out.println("--->" + pair.getValue().get(1).get(i));
			}
			
			if (logs.entrySet().iterator().hasNext()) {
				System.out.println();
			}
		}
		
	}

	private static void sortLogs(LinkedHashMap<String, ArrayList<ArrayList<String>>> logs) {
		//Sort the errors by their name length, then by alphabetical
		for (Map.Entry<String, ArrayList<ArrayList<String>>> pair : logs.entrySet()) {
			for (int i = 0; i < pair.getValue().size(); i++) {
				Collections.sort((pair.getValue().get(i)), (String o1, String o2) -> {
					if (o1.length() == o2.length()) {
						return o1.compareTo(o2);
					}
					return ((Integer)o1.length()).compareTo(o2.length());
				});
			}
		}
		
		ArrayList<Map.Entry<String, ArrayList<ArrayList<String>>>> enryes = new ArrayList<>(logs.entrySet());
		//sort the entries by total number of errors, then by alphabetical
		enryes.sort((o1, o2) -> {
			if (((Integer)(o1.getValue().get(0).size() + o1.getValue().get(1).size())).compareTo(
					o2.getValue().get(0).size() + o2.getValue().get(1).size()) == 0) {
				return o1.getKey().compareTo(o2.getKey());
			}
			
			return - ((Integer)(o1.getValue().get(0).size() + o1.getValue().get(1).size())).compareTo(
					o2.getValue().get(0).size() + o2.getValue().get(1).size());
			});
		//prepare the map for the sorted data
		logs.clear();
		//put the entries in the LinkedHashMap to save the sorting
		for (Entry<String, ArrayList<ArrayList<String>>> entry : enryes) {
			logs.put(entry.getKey(), entry.getValue());
		}
	}

}
