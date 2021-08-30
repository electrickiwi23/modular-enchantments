package mintnetwork.modularenchantments.setup;

import mintnetwork.modularenchantments.Enchantments.VolleyEnchantment;
import mintnetwork.modularenchantments.ModularEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

public class ModEnchantments
{

    @Mod.EventBusSubscriber(modid = ModularEnchantments.MOD_ID)
    public static class RegistrationHandler {
        @SubscribeEvent
        public void registerEnchantment(final RegistryEvent.Register<Enchantment> event) {
            // DEBUG

            final IForgeRegistry<Enchantment> registry = event.getRegistry();

            registry.register(new VolleyEnchantment());
        }
    }
}
