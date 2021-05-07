public class CreateValidator {
    public String getSecondWord(String command) {
        int start = command.indexOf(" ") + 1;
        int end = command.indexOf(" ", start + 1);
        return command.substring(start, end);
    }

    public String getThirdWord(String command) {
        String[] sp = command.split(" ");
        return sp[2];
    }

    public boolean checkIdLength(String command) {
        if (getThirdWord(command).length() != 8) {
            return false;
        } else {
            return true;
        }
    }

    public boolean validateCreate(String command) {
        String accountType = getSecondWord(command);
        if (accountType.equalsIgnoreCase("checking") || accountType.equalsIgnoreCase("savings") || accountType.equalsIgnoreCase("cd")) {
            if (checkIdLength(command)) {
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }
    }
}
