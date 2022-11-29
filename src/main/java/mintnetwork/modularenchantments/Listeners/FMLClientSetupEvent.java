package mintnetwork.modularenchantments.Listeners;

import mintnetwork.modularenchantments.Entities.EnchantMiteRenderer;
import mintnetwork.modularenchantments.ModularEnchantments;
import mintnetwork.modularenchantments.setup.Registration;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = ModularEnchantments.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class FMLClientSetupEvent {

    @SubscribeEvent
    public static void arrowShot(final net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent event) {

        EntityRenderers.register(Registration.ENCHANTMITE.get(), EnchantMiteRenderer::new);
//        ScreenManager.registerFactory(Registration.HONINGTABLECONTAINER.get(), HoningTableScreen::new);
    }

}
