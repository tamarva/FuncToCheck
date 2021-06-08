import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) {
        System.out.println(breakToLines("i am having fun being in an interview with Dima", 10));
    }

    /**
     * parse the given string into lines efficiently such that - a length of line <= k and you don't cut a word
     * in the middle
     */

    public static List<String> breakToLines(String input, int k) {
        List<String> lines = new ArrayList<>();
        int validLineIndexToRemember = 0;
        int startOfValidLine = 0;
        int counter = 0;
        int length = input.length();

        for (int i = 0; i < length; i++) {
            counter++;
            if (counter >= k || i == length - 1) {
                if (validLineIndexToRemember < 0 && !isSpaceOrEndOfWord(input, length, i)){
                    return new ArrayList<>();
                }
                Line line = new Line(input, startOfValidLine, validLineIndexToRemember, length, i);
                lines.add(line.getNewLine());
                counter = line.getCounter();
                startOfValidLine = line.getStartOfNextValidLine();
                validLineIndexToRemember = -1;
                continue;
            }

            if (input.charAt(i) == ' ') {
                validLineIndexToRemember = i;
            }
        }
        return lines;
    }

    private static boolean isSpaceOrEndOfWord(String input, int length, int i) {
        return input.charAt(i) == ' ' || i < length - 1 && input.charAt(i + 1) == ' ';
    }

    private static class Line {
        private String input;
        private int validLineIndexToRemember;
        private int length;
        private int i;
        private int startOfValidLine;

        public Line(String input,int startOfValidLine ,int validLineIndexToRemember, int length, int i) {
            this.input = input;
            this.startOfValidLine = startOfValidLine;
            this.validLineIndexToRemember = validLineIndexToRemember;
            this.length = length;
            this.i = i;
        }

        public int getEndIndex() {
            return (isSpaceOrEndOfWord(input, length, i) || i == length - 1) ? i : validLineIndexToRemember;
        }


        public int getStartOfNextValidLine(){
            return getEndIndex() + 1;

        }

        public int getCounter(){
            return isSpaceOrEndOfWord(input, length, i) ? 0 : i - validLineIndexToRemember;
        }

        public String getNewLine() {
            return input.substring(startOfValidLine, getEndIndex()+ 1);
        }
    }
}