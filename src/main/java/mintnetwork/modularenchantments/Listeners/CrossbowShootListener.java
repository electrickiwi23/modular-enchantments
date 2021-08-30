package mintnetwork.modularenchantments.Listeners;

import mintnetwork.modularenchantments.Enchantments.PotencyEnchantment;
import mintnetwork.modularenchantments.Enchantments.RicochetEnchantment;
import mintnetwork.modularenchantments.Enchantments.SuppressionEnchantment;
import mintnetwork.modularenchantments.ModularEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;

    @Mod.EventBusSubscriber(modid = ModularEnchantments.MOD_ID)
    public class CrossbowShootListener {

        public static Map<Entity,Integer> supressesedlevel = new HashMap<>();

        public static Map<Entity,Integer> PotencyLevel = new HashMap<>();


        @SubscribeEvent
        public static void arrowShot(PlayerInteractEvent event) {
            ItemStack itemStack = (event.getItemStack());
            if (itemStack.getItem().isCrossbow(itemStack)) {
                if (CrossbowItem.isCharged(itemStack)){
                    Map<Enchantment, Integer> enchants = EnchantmentHelper.getEnchantments(itemStack);
                    for (Enchantment e : enchants.keySet()) {
                        if (e instanceof SuppressionEnchantment) {
                            supressesedlevel.put(event.getEntity(),1);
                        }
                        if (e instanceof PotencyEnchantment) {
                            PotencyLevel.put(event.getEntity(),1);
                        }

                    }
                }
            }
        }
}
