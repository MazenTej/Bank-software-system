package banking;

public class Parsing {

    public String getFirstWord(String command) {
        if (command.contains(" ")) {
            String[] words = command.split("\\s+");
            return words[0];
        } else {
            return command;
        }


    }

    public String getSecondWord(String command) {
        String[] words = command.split("\\s+");
        return words[1];
    }

    public String getThirdWord(String command) {
        String[] words = command.split(" ");
        return words[2];
    }

    public String getFourthWord(String command) {
        String[] words = command.split(" ");
        return words[3];
    }

    public String getFifthWord(String command) {
        String[] words = command.split(" ");
        return words[4];
    }

    public boolean checkIdLength(String id) {
        if (id.length() != 8) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkIdHasOnlyNumbers(String id) {
        if (id.matches(".*[^0-9]+.*")) {
            return false;
        } else {
            return true;
        }

    }

    public boolean checkStringDouble(String string) {
        boolean result;
        try {
            Double.parseDouble(string);
            result = true;

        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    public boolean checkAprRange(String apr) {
        double doubleApr = Double.parseDouble(apr);
        if (doubleApr < 0 || doubleApr > 10) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkCommandLength(String command, Integer length) {
        boolean result = true;
        String[] words = command.split("\\s+");
        if (words.length != length) {
            result = false;
        }
        return result;
    }


}
