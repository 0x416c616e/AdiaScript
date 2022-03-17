//all settings will be stored here in the future
//it will be read from a configuration file, then put into a Config object
//and then things can do stuff depending on what they get from the Config's getters
public class Configuration {
    private boolean darkMode;
    private boolean esotericMode;
    //todo: add more settings here later

    private String settingsFileName; //todo: make a json file that has all the settings

    //no args = default settings
    public Configuration () {
        this.darkMode = true;
        this.settingsFileName = "config.json";
        this.esotericMode = false;
    }

    public boolean isDarkMode() {
        return darkMode;
    }

    public void setDarkMode(boolean darkMode) {
        this.darkMode = darkMode;
    }

    public String getSettingsFileName() {
        return settingsFileName;
    }

    public void setSettingsFileName(String settingsFileName) {
        this.settingsFileName = settingsFileName;
    }

    public boolean isEsotericMode() {
        return esotericMode;
    }

    public void setEsotericMode(boolean esotericMode) {
        this.esotericMode = esotericMode;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "darkMode=" + darkMode +
                ", esotericMode=" + esotericMode +
                ", settingsFileName='" + settingsFileName + '\'' +
                '}';
    }
}
