package mintnetwork.modularenchantments.Listeners;

import mintnetwork.modularenchantments.Enchantments.ExperienceEnchantment;
import mintnetwork.modularenchantments.ModularEnchantments;
import mintnetwork.modularenchantments.setup.Registration;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber(modid = ModularEnchantments.MOD_ID)
public class ExperienceDropListener {
    @SubscribeEvent
    public static void onEntityDrop(LivingExperienceDropEvent event) {
        if (event.getAttackingPlayer() !=null) {
            int experianceEnchantLevel = EnchantmentHelper.getMaxEnchantmentLevel(Registration.EXPERIENCE.get(), event.getAttackingPlayer());
            if (experianceEnchantLevel > 0) {
                event.setDroppedExperience(ExperienceEnchantment.calculateExp(event.getDroppedExperience(), experianceEnchantLevel));
            }
        }


    }
}
