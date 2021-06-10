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
}
