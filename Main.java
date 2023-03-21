package bullscows;
import java.util.*;
import java.util.HashSet;
class cal {
    private StringBuilder db;
    cal(StringBuilder db) {
        this.db = db;
    }
    public StringBuilder getdb() {
        return db;
    }
    boolean sol(String s, int numDigit) {
        int cows = 0;
        int bulls = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < db.length() ; j++) {
                if (s.charAt(i) == db.charAt(j)) {
                    if(i == j) {
                        bulls++;
                    } else {
                        cows++;
                    }
                }
            }
        }
        if (cows == 0 && bulls == 0 ) {
            System.out.println("Grade: None");
            System.out.println();
        } else if (cows != 0 && bulls != 0) {
            if (cows > 1 && bulls > 1) {
                System.out.printf("Grade: %d bulls and %d cows", bulls, cows);
            } else if (cows > 1) {
                System.out.printf("Grade: %d bull and %d cows", bulls, cows);
            } else {
                System.out.printf("Grade: %d bulls and %d cow", bulls, cows);
            }
            System.out.println();
        } else if (cows != 0 && bulls ==0) {
            if (cows > 1) {
                System.out.printf("Grade: %d cows", cows);
            } else {
                System.out.printf("Grade: %d cow", cows);
            }

            System.out.println();
        } else {
            if (bulls > 1) {
                System.out.printf("Grade: %d bulls", bulls);
            } else {
                System.out.printf("Grade: %d bull", bulls);
            }

            System.out.println();
        }
        if (numDigit == bulls) {
            return true;
        } else {
            return false;
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the length of the secret code:");
        String numd = scanner.nextLine();
        int numDigit;
        boolean d = true;
        int t = 1;
        for (int i = 0; i < numd.length(); i++) {
            if (numd.charAt(i) < '0' || numd.charAt(i) > '9') {
                System.out.printf("Error: \"%s\" isn't a valid number.", numd);
                return;
            }
        }
         numDigit = Integer.parseInt(numd);
        if (numDigit > 36) {
            System.out.println("Error: can't generate a secret number with a length of 11 because there aren't enough unique digits.");
        } else if (numDigit == 0) {
            System.out.printf("Error: \"%d\" isn't a valid number.", numDigit);
        } else {
            Random random = new Random();
            System.out.println("Input the number of possible symbols in the code:");
            String template = "0123456789abcdefghijklmnopqrstuvwxyz";
            int pNum = scanner.nextInt();
            if (pNum < numDigit) {
                System.out.printf("Error: it's not possible to generate a code with a length of %d with %d unique symbols.", numDigit, pNum);
                return;
            }
            if (pNum > 36) {
                System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
                return;
            }
            String star = "";
            for (int i = 0; i < numDigit; i++) {
                star += "*";
            }
            if (pNum > 10) {
                System.out.printf("The secret is prepared: %s (0-9, a-%c).", star, template.charAt(pNum-=1));
            } else {
                System.out.printf("The secret is prepared: %s (0-%c).", star, template.charAt(pNum-=1));
            }

            System.out.println();
            System.out.println("Okay, let's start a game!");
            int upper = 1;
            int lower = 1;
            for (int i = 0; i < numDigit - 1; i++) {
                upper *= 10;
                lower *= 10;
                upper++;
            }
           upper *= 9;
//            System.out.println(upper);
            long pseudoRandomNumber = random.nextInt(upper - lower) + lower;
            StringBuilder sb = new StringBuilder();
            sb.append(pseudoRandomNumber);
            StringBuilder ans = new StringBuilder();
            for (int i = 0; i < sb.length(); i++) {
                boolean check = true;
                for (int j = 0; j < ans.length(); j++) {
                    if (sb.charAt(i) == ans.charAt(j)) {
                        check = false;
                    }
                }
                if (check) {
                    ans.append(sb.charAt(i));
                }
                if (ans.length() == numDigit) {
                    break;
                }
            }
//            System.out.println(ans);
            while (ans.length() < numDigit) {
//                System.out.println("vl");
                int rd = random.nextInt(pNum);
                boolean check = true;
                for (int i = 0; i < ans.length(); i++) {
                    if (ans.charAt(i) == template.charAt(rd)) {
                        check = false;
                    }
                }
                if (check == true) {
                    int rid = random.nextInt(numDigit);
                    ans.insert(rid ,template.charAt(rd));
                }
            }
//            System.out.println(ans);

            while(t > 0) {
                System.out.println("Turn " + t + ":");

                cal k = new cal(ans);
//                System.out.println(k.getdb());
                String s = scanner.next();
                if(k.sol(s,numDigit)) {
                    break;
                }
                t++;
            }
            System.out.println("Congratulations! You guessed the secret code.");
        }


    }
}
