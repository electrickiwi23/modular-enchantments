package mintnetwork.modularenchantments.Listeners;

import mintnetwork.modularenchantments.Enchantments.*;
import mintnetwork.modularenchantments.ModularEnchantments;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraftforge.common.data.ForgeRecipeProvider;
import net.minecraftforge.event.entity.living.LivingDestroyBlockEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Mod.EventBusSubscriber(modid = ModularEnchantments.MOD_ID)
public class BlockDestroyListener {

    public static List<BlockPos> explode = new ArrayList<>();



    @SubscribeEvent
    public static void arrowShot(final BlockEvent.BreakEvent event) {
        LivingEntity entity = event.getPlayer();
        Map<Enchantment, Integer> enchants = EnchantmentHelper.getEnchantments(entity.getHeldItem(Hand.MAIN_HAND));
        for (Enchantment e : enchants.keySet()) {
            if (e instanceof DestructionCurse) {
                BlockPos pos = event.getPos();
                explode.add(pos);
            }
            if (e instanceof ExperienceEnchantment){
                event.setExpToDrop(ExperienceEnchantment.calculateExp(event.getExpToDrop(), enchants.get(e)));
            }
        }
    }

}
