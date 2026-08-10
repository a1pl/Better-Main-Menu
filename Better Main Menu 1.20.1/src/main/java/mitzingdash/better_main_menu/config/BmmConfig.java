package mitzingdash.better_main_menu.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "better_main_menu")
public class BmmConfig implements ConfigData {

    @ConfigEntry.Category("panel")
    @ConfigEntry.Gui.TransitiveObject
    public PanelConfig panel = new PanelConfig();

    @ConfigEntry.Category("logo")
    @ConfigEntry.Gui.TransitiveObject
    public LogoConfig logo = new LogoConfig();

    @ConfigEntry.Category("buttons")
    @ConfigEntry.Gui.TransitiveObject
    public ButtonsConfig buttons = new ButtonsConfig();

    @ConfigEntry.Category("misc")
    @ConfigEntry.Gui.TransitiveObject
    public MiscConfig misc = new MiscConfig();

    public static class PanelConfig {
        public int panelWidth = 100;
        public int panelColor = 0x77000000;
    }

    public static class LogoConfig {
        public int logoX = 10;
        public int logoY = 5;
        public int logoWidth = 80;
        public int logoHeight = 80;
    }

    public static class ButtonsConfig {
        public int buttonPanelYOffset = -40;
        public int buttonPanelHeight = 100;
        public int mainButtonWidth = 90;
        public int mainButtonHeight = 20;
        public int mainButtonMargin = 2;
    }

    public static class MiscConfig {
        public int quitButtonWidth = 45;
        public int quitButtonHeight = 20;
        public int quitButtonXOffset = -50;
        public int quitButtonYOffset = -25;

        public int accessibilityButtonWidth = 20;
        public int accessibilityButtonHeight = 20;
        public int accessibilityButtonXOffset = -73;
        public int accessibilityButtonYOffset = -25;

        public int languageButtonWidth = 20;
        public int languageButtonHeight = 20;
        public int languageButtonXOffset = 5;
        public int languageButtonYOffset = -25;

        public int realmsButtonWidth = 45;
        public int realmsButtonHeight = 20;
        public int realmsButtonXOffset = -50;
        public int realmsButtonYOffset = -48;
        
        public int creditXOffset = 5;
        public int creditYOffset = -12;
        public int creditWidth = 152;
        public int creditHeight = 10;
        
        public int socialXOffset = 5;
        public int socialYOffset = -20;
        public int socialWidth = 50;
        public int socialHeight = 5;
    }
}
