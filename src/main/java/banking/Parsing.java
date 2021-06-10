package banking;

public class Parsing {

    public String getFirstWord(String command) {
        if (command.contains(" ")) {
            return command.substring(0, command.indexOf(' '));
        } else {
            return command;
        }
    }

    public String getSecondWord(String command) {
        int start = command.indexOf(" ") + 1;
        int end = command.indexOf(" ", start + 1);
        return command.substring(start, end);
    }

    public String getThirdWord(String command) {
        String[] sp = command.split(" ");
        return sp[2];
    }

    public String getFourthWord(String command) {
        String[] sp = command.split(" ");
        return sp[3];
    }

    public String getFifthWord(String command) {
        String[] sp = command.split(" ");
        return sp[4];

    }
}
