public class CommandValidator {


    public boolean validate(String command) {
        if (!checkForExtraSpaces(command)) {
            return false;
        } else {
            return true;
        }
    }

    protected String getFirstWord(String command) {
        return command.substring(0, command.indexOf(' '));
    }

    protected boolean checkForExtraSpaces(String command) {
        String newString = command.replaceAll("\\s++$", "");
        if (newString.startsWith(" ") || newString.contains("  ")) {
            return false;
        } else {
            return true;
        }


    }


}
