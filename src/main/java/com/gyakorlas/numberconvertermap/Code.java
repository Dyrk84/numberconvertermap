import java.util.*;
import java.util.stream.Collectors;

public class Code {

    final String RED = "\u001B[31m";
    final String BLUE = "\u001B[34m";
    final String CR = "\u001B[0m";

    List<String> allRomanNumberCombinations = new ArrayList<>();
    final HashMap<String, Integer> NUMBERS = new HashMap<>();

    public void code() {
        allRomanNumberCombinationsIterator();
        mapCreator();
        menu();
    }

    public void menu() {
        int chosenNumber = chosenNumber();
        switch (chosenNumber) {
            case 1:
                arabicToRoman();
                break;
            case 2:
                romanToArabic();
                break;
            case 3:
                exit();
                break;
            default:
                yourChooseIsNotAppropriate();
                menu();
        }
    }

    private int chosenNumber() {
        do {
            printMenu();
            Scanner scanner = new Scanner(System.in);
            try {
                return scanner.nextInt();
            } catch (InputMismatchException hibafogo) {
                yourChooseIsNotAppropriate();
            }
        } while (true);
    }

    private void printMenu() {
        System.out.println("*********************** Menu *****************************");
        System.out.println("*" + RED + " Choose one of the following possibilities:            " + CR +
                " *");
        System.out.println("* " + RED + "1." + CR + " I want to convert Arabic numerals to Roman numerals *");
        System.out.println("* " + RED + "2." + CR + " I want to convert Roman numerals to Arabic numerals *");
        System.out.println("* " + RED + "3." + CR + " Exit                                                *");
        System.out.println("**********************************************************");
    }

    private void yourChooseIsNotAppropriate() {
        System.out.println(RED + "Your choose is not appropriate!" + CR);
    }

    public void exit() {
        System.out.println("Good bye!");
    }

    public void allRomanNumberCombinationsIterator() {
        String[] single = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        String[] decimal = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] thousands = {"", "M", "MM", "MMM"};

        for (int i = 0; i < thousands.length; i++) {
            for (int j = 0; j < hundreds.length; j++) {
                for (int k = 0; k < decimal.length; k++) {
                    for (int l = 0; l < single.length; l++) {
                        allRomanNumberCombinations.add(thousands[i] + hundreds[j] + decimal[k] + single[l]);
                    }
                }
            }
        }
        //test
//        System.out.println("ALL_ROMAN_NUMBER_COMBINATIONS test");
//        for (int i = 0; i < ALL_ROMAN_NUMBER_COMBINATIONS.size(); i++) {
//            System.out.print(ALL_ROMAN_NUMBER_COMBINATIONS.get(i) + ", ");
//        }
//        System.out.println();
    }

    public void mapCreator() {
        for (int i = 0; i < allRomanNumberCombinations.size(); i++) {
            NUMBERS.put(allRomanNumberCombinations.get(i), i);
        }
        //test
//        System.out.println("numbers test");
//        for (int i = 0; i < NUMBERS.size(); i++) {
//            System.out.print(NUMBERS + ", ");
//        }
//        System.out.println();
    }

    public void arabicToRoman() {
        short inputArabicNumber = scannerAR();
        List<String> keysOfNUMBERS = NUMBERS.entrySet().stream().sorted(Map.Entry.comparingByValue()).map(Map.Entry::getKey).collect(Collectors.toList()); //a NUMBERS.adatait alakítsa.stream-é.rendezze sorba(a map.adataiból.az értékeket figyelembe véve).mapelje.(a NUMBERS MAP.adatai közül :: minden kulcs adatot).rendezze(a Collectors-t használva.listává)
        String resultAR = keysOfNUMBERS.get(inputArabicNumber);
        resultTextAR(resultAR);
        again();
    }

    public short scannerAR() {
        while (true) {
            try {
                Scanner reader = new Scanner(System.in);
                System.out.println("Enter a number between from 1 to 3999:");
                short inputArabicNumber = reader.nextShort();
                if (inputArabicNumber < 1 || inputArabicNumber > 3999) {
                    System.out.println(RED + "The number not between from 1 to 3999! Try again!" + CR);
                } else {
                    return inputArabicNumber;
                }
            } catch (InputMismatchException hibafogo) {
                System.out.println(RED + "wrong input data!" + CR);
            }
        }
    }

    public void resultTextAR(String resultAR) {
        System.out.println("The number you entered is in Roman numerals: " + BLUE + resultAR + CR);
    }

    public void romanToArabic() {
        String inputRomanNumber = scannerRA();
        int resultRA = NUMBERS.get(inputRomanNumber);
        resultTextRA(resultRA);
        again();
    }

    public String scannerRA() {
        while (true) {
            try {
                Scanner reader = new Scanner(System.in);
                System.out.println("Enter a Roman number between from I to MMMCMXCIX:");
                String x = reader.nextLine();
                if (allRomanNumberCombinations.contains(x)) {
                    return x;
                } else {
                    System.out.println("\u001B[31m" + "Not allowed roman number!" + "\u001B[0m");
                }
            } catch (InputMismatchException hibafogo) {
                System.out.println(RED + "wrong input data!" + CR);
            }
        }
    }

    public void resultTextRA(int resultRA) {
        System.out.println("The number you entered is in Arabic numerals: " + BLUE + resultRA + CR);
    }

    public void again() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to convert a another number? If yes, type " +
                RED + "\"yes\" " + CR + "or just " + RED + "\"y\"" + CR + "!");
        String answer = scanner.next();
        if (answer.equals("yes") || answer.equals("y")) {
            menu();
        } else exit();
    }
}