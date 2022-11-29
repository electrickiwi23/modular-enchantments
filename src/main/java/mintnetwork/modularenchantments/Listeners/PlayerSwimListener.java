package mintnetwork.modularenchantments.Listeners;

import mintnetwork.modularenchantments.Enchantments.HeavyCurse;
import mintnetwork.modularenchantments.ModularEnchantments;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;


@Mod.EventBusSubscriber(modid = ModularEnchantments.MOD_ID)
public class PlayerSwimListener {

//    private static List<Entity> hasSniped = new ArrayList<>();

    @SubscribeEvent
    public static void tickPlayer(TickEvent.PlayerTickEvent event) {

        Player player = event.player;

        if (event.phase.equals(TickEvent.Phase.START)) {


            if (event.player.isInWater()) {
                for (ItemStack item : player.getArmorSlots()) {
                    Map<Enchantment, Integer> enchants = EnchantmentHelper.getEnchantments(item);
                    for (Enchantment e : enchants.keySet()) {
                        if (e instanceof HeavyCurse) {
                            player.setJumping(false);
                            if (player.getDeltaMovement().y() > 0) {
                                player.setDeltaMovement(player.getDeltaMovement().add(0, player.getDeltaMovement().y() / -2, 0));
                            } else {
                                player.setDeltaMovement(player.getDeltaMovement().add(0, -.06, 0));
                            }
                        }
                    }
                }
            }
        }
    }

}
