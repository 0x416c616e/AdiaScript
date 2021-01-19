package sample;

public class ScriptHalter {
    private boolean userWantsToHaltScript;
    public ScriptHalter(boolean userWantsToHaltScript) {
        setUserWantsToHaltScript(userWantsToHaltScript);
    }
    public ScriptHalter() {
        setUserWantsToHaltScript(false);
    }

    public void setUserWantsToHaltScript(boolean userWantsToHaltScript) {
        this.userWantsToHaltScript = userWantsToHaltScript;
    }

    public boolean isUserWantsToHaltScript() {
        return userWantsToHaltScript;
    }
}
