package SimpleSearchEngine;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Elements {

    int count = 0;                                                      //lines counter for answ
    private final List<String> elements;                                //all lines for mapping are here
    private final Map<String, Integer> m = new HashMap<>();             //key - lineNum | value - foundCount
    private final StringBuilder answ = new StringBuilder();             //search results will be stored here

    public Elements(List<String> lines) {

        elements = lines;
    }

    public void searchFor(String[] words, String type) {

        elements.forEach(line -> {
            for (String word : words) {
                if (line.toLowerCase().contains(word)) {
                    m.put(line, m.getOrDefault(line, 0) + 1);
                }
                else {
                    m.put(line, m.getOrDefault(line, 0));
                }
            }
        });
        int wordsNum = words.length;

        switch (type) {
            case "ALL":
                allMatch(wordsNum);
                break;
            case "ANY":
                anyMatch();
                break;
            case "NONE":
                noMatch();
                break;
        }
        if (answ.length() == 0) {
            System.out.println("No elements found");
        }
        else {
            System.out.printf("Found %d elements:\n%s", count, answ);
        }
        answ.setLength(0);
        count = 0;
        m.clear();
    }

    private void allMatch(int wordsNum) {
        m.forEach((line, repeats) -> {
            if (repeats == wordsNum) {
                answ.append(line).append('\n');
                count++;
            }
        });
    }

    private void anyMatch() {
        m.forEach((line, repeats) -> {
            if (repeats > 0) {
                answ.append(line).append('\n');
                count++;
            }
        });
    }

    private void noMatch() {
        m.forEach((line, repeats) -> {
            if (repeats == 0) {
                answ.append(line).append('\n');
                count++;
            }
        });
    }

    public void printAll() { elements.forEach(System.out::println); }
}
