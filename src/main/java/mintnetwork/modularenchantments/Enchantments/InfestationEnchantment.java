package mintnetwork.modularenchantments.Enchantments;

import mintnetwork.modularenchantments.Entities.EnchantMiteEntity;
import mintnetwork.modularenchantments.setup.Registration;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.*;

import java.util.Map;
import java.util.Random;

public class InfestationEnchantment extends Enchantment {

    public InfestationEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentCategory.ARMOR, new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET});
    }

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
    public int getMinCost(int enchantmentLevel) {
        return 10 + 20 * (enchantmentLevel - 1);
    }

    public int getMaxCost(int enchantmentLevel) {
        return this.getMinCost(enchantmentLevel) + 50;
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 3;
    }

    /**
     * Calculates the damage protection of the enchantment based on level and damage source passed.
     */
    public void doPostHurt(LivingEntity user, Entity attacker, int level) {
        RandomSource random = user.getRandom();
        Map.Entry<EquipmentSlot, ItemStack> entry = EnchantmentHelper.getRandomItemWith(Registration.INFESTATION.get(), user);
        if (shouldHit(level, random)) {
            if (attacker != null) {
                if (attacker instanceof LivingEntity){

                EnchantMiteEntity mite =Registration.ENCHANTMITE.get().create(user.getLevel());
                mite.setOwner(user);
                mite.setTarget((LivingEntity) attacker);
                mite.moveTo(user.getRandomX(.5),user.getY()+.5,user.getRandomZ(.5));
                attacker.getLevel().addFreshEntity(mite);

                } else if (attacker instanceof Projectile &&((Projectile) attacker).getOwner()!=null&&((Projectile) attacker).getOwner()instanceof LivingEntity){
                    EnchantMiteEntity mite = Registration.ENCHANTMITE.get().create(user.getLevel());
                    mite.setOwner(user);
                    mite.setTarget((LivingEntity) ((Projectile) attacker).getOwner());
                    mite.moveTo(user.getRandomX(.5),user.getY()+.5,user.getRandomZ(.5));
                    attacker.getLevel().addFreshEntity(mite);


                }
            }

            if (entry != null) {
                entry.getValue().hurtAndBreak(2, user, (p_45208_) -> {
                    p_45208_.broadcastBreakEvent(entry.getKey());
                });
            }
        }

    }

    public static boolean shouldHit(int level, RandomSource rnd) {
        if (level <= 0) {
            return false;
        } else {
            return rnd.nextFloat() < 0.10F * (float)level;
        }
    }

    /**
     * Determines if the enchantment passed can be applyied together with this enchantment.
     */
    public boolean checkCompatibility(Enchantment ench) {
        return !(ench instanceof ThornsEnchantment) && super.checkCompatibility(ench);
    }

    /**
     * Gets the amount of ticks an entity should be set fire, adjusted for fire protection.
     */
//    public static int getPotionTimeForEntity(LivingEntity livingEntity, int level) {
//        int i = EnchantmentHelper.getMaxEnchantmentLevel(Enchantments, livingEntity);
//        if (i > 0) {
//            level -= MathHelper.floor((float)level * (float)i * 0.15F);
//        }
//
//        return level;
//    }

}