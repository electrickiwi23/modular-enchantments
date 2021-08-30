package mintnetwork.modularenchantments.Enchantments;

import mintnetwork.modularenchantments.Entities.EnchantMiteEntity;
import mintnetwork.modularenchantments.setup.Registration;
import net.minecraft.enchantment.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

import java.util.Map;
import java.util.Random;

public class InfestationEnchantment extends Enchantment {

    public InfestationEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentType.ARMOR, new EquipmentSlotType[]{EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET});
    }

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
    public int getMinEnchantability(int enchantmentLevel) {
        return 10 + 20 * (enchantmentLevel - 1);
    }

    public int getMaxEnchantability(int enchantmentLevel) {
        return this.getMinEnchantability(enchantmentLevel) + 50;
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
    public void onUserHurt(LivingEntity user, Entity attacker, int level) {
        Random random = user.getRNG();
        Map.Entry<EquipmentSlotType, ItemStack> entry = EnchantmentHelper.getRandomItemWithEnchantment(Enchantments.THORNS, user);
        if (shouldHit(level, random)) {
            if (attacker != null) {
                if (attacker instanceof LivingEntity){
                EnchantMiteEntity mite = new EnchantMiteEntity(Registration.ENCHANTMITE.get(),user.getEntityWorld(),user);
                mite.setTarget((LivingEntity) attacker);
                mite.setLocationAndAngles(user.getPosXRandom(.5),user.getPosY()+.5,user.getPosZRandom(.5),0,0);
                attacker.getEntityWorld().addEntity(mite);


                } else if (attacker instanceof ProjectileEntity&&((ProjectileEntity) attacker).getShooter()!=null&&((ProjectileEntity) attacker).getShooter()instanceof LivingEntity){
                    EnchantMiteEntity mite = new EnchantMiteEntity(Registration.ENCHANTMITE.get(),user.getEntityWorld(),user);
                    mite.setTarget((LivingEntity) ((ProjectileEntity) attacker).getShooter());
                    mite.setLocationAndAngles(user.getPosXRandom(.5),user.getPosY()+.5,user.getPosZRandom(.5),0,0);
                    attacker.getEntityWorld().addEntity(mite);


                }
            }

            if (entry != null) {
                entry.getValue().damageItem(2, user, (livingEntity) -> {
                    livingEntity.sendBreakAnimation(entry.getKey());
                });
            }
        }

    }

    public static boolean shouldHit(int level, Random rnd) {
        if (level <= 0) {
            return false;
        } else {
            return rnd.nextFloat() < 0.10F * (float)level;
        }
    }

    /**
     * Determines if the enchantment passed can be applyied together with this enchantment.
     */
    public boolean canApplyTogether(Enchantment ench) {
        return !(ench instanceof ThornsEnchantment) && super.canApplyTogether(ench);
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