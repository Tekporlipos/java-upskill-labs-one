package com.amalitech.upskilling;

public class OutPut {
    private OutPut() {
    }

    // ANSI escape codes for text colors
    public static class Colors {
        private Colors() {
        }

        public static final String RESET = "\u001B[0m";
        public static final String RED = "\u001B[31m";
        public static final String GREEN = "\u001B[32m";
        public static final String YELLOW = "\u001B[33m";
        public static final String BLUE = "\u001B[34m";
        public static final String PURPLE = "\u001B[35m";
        public static final String CYAN = "\u001B[36m";
        public static final String WHITE = "\u001B[37m";
        public static final String MAGENTA = "\033[035m";
    }

    // Example method to print colored text
    public static void printColoredTextBlock(String text, String color) {
        System.out.println(color + text + Colors.RESET);
    }

    public static void printColoredTextInline(String text, String color) {
        System.out.print(color + text + Colors.RESET);
    }
}
