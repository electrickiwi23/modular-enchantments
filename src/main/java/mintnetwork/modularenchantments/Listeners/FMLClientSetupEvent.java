package mintnetwork.modularenchantments.Listeners;

import mintnetwork.modularenchantments.Blocks.HoningTableContainer;
import mintnetwork.modularenchantments.Entities.EnchantMiteRenderer;
import mintnetwork.modularenchantments.ModularEnchantments;
import mintnetwork.modularenchantments.client.gui.HoningTableScreen;
import mintnetwork.modularenchantments.setup.Registration;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = ModularEnchantments.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class FMLClientSetupEvent {

    @SubscribeEvent
    public static void arrowShot(final net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(Registration.ENCHANTMITE.get(), EnchantMiteRenderer::new);
//        ScreenManager.registerFactory(Registration.HONINGTABLECONTAINER.get(), HoningTableScreen::new);
    }

}
