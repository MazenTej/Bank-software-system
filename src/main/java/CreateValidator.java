public class CreateValidator {
    public static boolean validateCreate(String command) {
        String accountType = getSecondWord(command);
        if (accountType.equalsIgnoreCase("checking") || accountType.equalsIgnoreCase("savings") || accountType.equalsIgnoreCase("cd")) {
            return true;

        } else {
            return false;
        }
    }

    public static String getSecondWord(String command) {
        int start = command.indexOf(" ") + 1;
        int end = command.indexOf(" ", start + 1);
        return command.substring(start, end);
    }
}
