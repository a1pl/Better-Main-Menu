package mitzingdash.better_main_menu.client;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import mitzingdash.better_main_menu.config.BmmConfig;
import net.fabricmc.api.ClientModInitializer;

public class BetterMainMenuClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        AutoConfig.register(BmmConfig.class, GsonConfigSerializer::new);
    }
}
