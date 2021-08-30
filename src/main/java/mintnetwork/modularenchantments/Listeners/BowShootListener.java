package mintnetwork.modularenchantments.Listeners;

import mintnetwork.modularenchantments.Enchantments.RicochetEnchantment;
import mintnetwork.modularenchantments.Enchantments.SniperEnchantment;
import mintnetwork.modularenchantments.Enchantments.VolleyEnchantment;
import mintnetwork.modularenchantments.ModularEnchantments;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



@Mod.EventBusSubscriber(modid = ModularEnchantments.MOD_ID)
public class BowShootListener {

    public static Map<Entity,Integer> volleyLevel = new HashMap<>();
    public static Map<Entity,Integer> ricochetLevel = new HashMap<>();
    public static Map<Entity,Integer> sniperLevel = new HashMap<>();

    public static ArrayList<AbstractArrowEntity> volleyArrows = new ArrayList<>();

    @SubscribeEvent
    public static void arrowShot(ArrowLooseEvent event) {
        if (event.getCharge()!=0){
            if (event.hasAmmo()) {
                PlayerEntity player = event.getPlayer();
                ItemStack bow = event.getBow();
                Map<Enchantment, Integer> enchants = EnchantmentHelper.getEnchantments(bow);
                for (Enchantment e : enchants.keySet()) {
                    if (e instanceof RicochetEnchantment){
                        ricochetLevel.put(player, enchants.get(e));
                    }
                    if (e instanceof VolleyEnchantment) {
                        volleyLevel.put(player, enchants.get(e));
                    }
                }
            }
        }
    }

}
