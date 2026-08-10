package mitzingdash.better_main_menu.client.gui.screen;

import com.terraformersmc.modmenu.api.ModMenuApi;

import io.github.thecsdev.tcdcommons.api.client.gui.layout.UIListLayout;
import io.github.thecsdev.tcdcommons.api.client.gui.other.TFillColorElement;
import io.github.thecsdev.tcdcommons.api.client.gui.other.TLabelElement;
import io.github.thecsdev.tcdcommons.api.client.gui.other.TTextureElement;
import io.github.thecsdev.tcdcommons.api.client.gui.screen.TScreenPlus;
import io.github.thecsdev.tcdcommons.api.client.gui.util.GuiUtils;
import io.github.thecsdev.tcdcommons.api.client.gui.util.TDrawContext;
import io.github.thecsdev.tcdcommons.api.client.gui.util.UITexture;
import io.github.thecsdev.tcdcommons.api.util.enumerations.Axis2D;
import io.github.thecsdev.tcdcommons.api.util.enumerations.HorizontalAlignment;
import io.github.thecsdev.tcdcommons.api.util.enumerations.VerticalAlignment;
import mitzingdash.better_main_menu.client.gui.widget.CreditButtonWidget;
import mitzingdash.better_main_menu.client.gui.widget.MButtonWidget;
import mitzingdash.better_main_menu.config.BmmConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerWarningScreen;
import net.minecraft.client.gui.screen.option.AccessibilityOptionsScreen;
import net.minecraft.client.gui.screen.option.LanguageOptionsScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.realms.gui.screen.RealmsMainScreen;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

public class BmmScreen extends TScreenPlus {

	public static final UITexture TEX_BACKGROUND = new UITexture(new Identifier("better_main_menu", "textures/gui/background.png"));
	
	public BmmScreen() {
		super(Text.translatable("narrator.screen.title"));
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("resource")
	@Override
	protected void init() {
		BmmConfig config = AutoConfig.getConfigHolder(BmmConfig.class).getConfig();
		
		var panel = new TFillColorElement(getWidth()-config.panel.panelWidth, 0, config.panel.panelWidth, getHeight());
		panel.setColor(config.panel.panelColor);
		addChild(panel);
		
		var logo = new TTextureElement(config.logo.logoX, config.logo.logoY, config.logo.logoWidth, config.logo.logoHeight);
		logo.setTexture(new UITexture(new Identifier("better_main_menu", "textures/gui/logo.png")));
		panel.addChild(logo);
		
		var button_panel = new TFillColorElement(0, panel.getHeight()/2 + config.buttons.buttonPanelYOffset, panel.getWidth(), config.buttons.buttonPanelHeight);
		button_panel.setColor(0x00000000);
		panel.addChild(button_panel);
		
		var singleplayer = new MButtonWidget(5, 5, config.buttons.mainButtonWidth, config.buttons.mainButtonHeight);
		singleplayer.setText(Text.translatable("menu.singleplayer"));
		singleplayer.setOnClick(__ -> {
			getClient().setScreen(new SelectWorldScreen(getAsScreen()));
		});
		button_panel.addChild(singleplayer);
		
		var multiplayer = new MButtonWidget(5, 27, config.buttons.mainButtonWidth, config.buttons.mainButtonHeight);
		multiplayer.setText(Text.translatable("menu.multiplayer"));
		multiplayer.setOnClick(__ -> {
			final boolean smw = getClient().options.skipMultiplayerWarning;
			final var screen = smw ? new MultiplayerScreen(getAsScreen()) : new MultiplayerWarningScreen(getAsScreen());
			getClient().setScreen(screen);
		});
		button_panel.addChild(multiplayer);
		
		var options = new MButtonWidget(5, 49, config.buttons.mainButtonWidth, config.buttons.mainButtonHeight);
		options.setText(Text.translatable("menu.options"));
		options.setOnClick(__ -> {
			getClient().setScreen(new OptionsScreen(getAsScreen(), getClient().options));
		});
		button_panel.addChild(options);
		
		if(FabricLoader.getInstance().isModLoaded("modmenu")) {
			var mod_btn = new MButtonWidget(0, 0, config.buttons.mainButtonWidth, config.buttons.mainButtonHeight);
			mod_btn.setText(Text.translatable("modmenu.title"));
			mod_btn.setOnClick(__ -> {
				getClient().setScreen(ModMenuApi.createModsScreen(getAsScreen()));
			});
			button_panel.addChild(mod_btn);
		}
		
		new UIListLayout(Axis2D.Y, VerticalAlignment.CENTER, HorizontalAlignment.CENTER, config.buttons.mainButtonMargin).apply(button_panel);
		
		var btn_quit = new MButtonWidget(panel.getWidth() + config.misc.quitButtonXOffset, panel.getHeight() + config.misc.quitButtonYOffset, config.misc.quitButtonWidth, config.misc.quitButtonHeight);
		btn_quit.setText(Text.literal("Quit"));
		btn_quit.setTooltip(Tooltip.of(Text.translatable("menu.quit")));
		btn_quit.setOnClick(__ -> {
			getClient().scheduleStop();
		});
		panel.addChild(btn_quit);
		
		var accesibility = new MButtonWidget(panel.getWidth() + config.misc.accessibilityButtonXOffset, panel.getHeight() + config.misc.accessibilityButtonYOffset, config.misc.accessibilityButtonWidth, config.misc.accessibilityButtonHeight);
		accesibility.setIcon(new UITexture(new Identifier("better_main_menu", "textures/gui/accessibility.png")));
		accesibility.setTooltip(Tooltip.of(Text.translatable("narrator.button.accessibility")));
		accesibility.setOnClick(__ -> {
			getClient().setScreen(new AccessibilityOptionsScreen(getAsScreen(), getClient().options));
		});
		panel.addChild(accesibility);
		
		var language = new MButtonWidget(config.misc.languageButtonXOffset, panel.getHeight() + config.misc.languageButtonYOffset, config.misc.languageButtonWidth, config.misc.languageButtonHeight);
		language.setIcon(new UITexture(new Identifier("better_main_menu", "textures/gui/language.png")));
		language.setTooltip(Tooltip.of(Text.translatable("narrator.button.language")));
		language.setOnClick(__ -> {
			getClient().setScreen(new LanguageOptionsScreen(getAsScreen(), getClient().options, getClient().getLanguageManager()));
		});
		panel.addChild(language);
		
		var realms = new MButtonWidget(panel.getWidth() + config.misc.realmsButtonXOffset, panel.getHeight() + config.misc.realmsButtonYOffset, config.misc.realmsButtonWidth, config.misc.realmsButtonHeight);
		realms.setText(Text.literal("Realms"));
		realms.setTooltip(Tooltip.of(Text.translatable("menu.online")));
		realms.setOnClick(__ -> {
			getClient().setScreen(new RealmsMainScreen(getAsScreen()));
		});
		panel.addChild(realms);
		
		var credits = new CreditButtonWidget(config.misc.creditXOffset, getHeight() + config.misc.creditYOffset, config.misc.creditWidth, config.misc.creditHeight);
		credits.setText(Text.literal("Main Menu forked by a1pl, made by Mitzingdash").formatted(Formatting.GREEN));
		credits.setOnClick(__ -> {
			// change it back if mitzingdash accepts pr
			GuiUtils.showUrlPrompt("https://github.com/a1pl", true);
		});
		addChild(credits);
		
		var txt_social = new TLabelElement(config.misc.socialXOffset, getHeight() + config.misc.socialYOffset, config.misc.socialWidth, config.misc.socialHeight, Text.literal("Not affiliated with MOJANG").formatted(Formatting.GOLD));
		addChild(txt_social);
		
		var settings = new MButtonWidget(5, getHeight()-45, 20, 20);
		settings.setIcon(new UITexture(new Identifier("better_main_menu", "textures/gui/settings.png")));
		settings.setTooltip(Tooltip.of(Text.literal("Bmm Settings")));
		settings.setOnClick(__ -> {
			// change it back if mitzingdash accepts pr
			GuiUtils.showUrlPrompt("https://github.com/a1pl", true);
		});
		//addChild(settings);
		
		
	}

	@Override
	public void renderBackground(TDrawContext pencil) {
		// TODO Auto-generated method stub
		TEX_BACKGROUND.drawTexture(pencil, 0, 0, getWidth(), getHeight());
	}
	
	
}
