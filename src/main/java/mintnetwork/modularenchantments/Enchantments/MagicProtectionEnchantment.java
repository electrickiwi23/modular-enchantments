package mintnetwork.modularenchantments.Enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.ProtectionEnchantment;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.DamageSource;

public class MagicProtectionEnchantment extends Enchantment {

    public MagicProtectionEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentType.ARMOR, new EquipmentSlotType[]{EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET});
    }

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
    public int getMinEnchantability(int enchantmentLevel) {
        return 7 + (enchantmentLevel - 1) * 8;
    }

    public int getMaxEnchantability(int enchantmentLevel) {
        return this.getMinEnchantability(enchantmentLevel) + 8;
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 4;
    }

    /**
     * Calculates the damage protection of the enchantment based on level and damage source passed.
     */
    public int calcModifierDamage(int level, DamageSource source) {
        if (source.canHarmInCreative()) {
            return 0;
        } else {
            return source.isMagicDamage() ? (int) (level * 2.5) : 0;
        }
    }

    /**
     * Determines if the enchantment passed can be applyied together with this enchantment.
     */
    public boolean canApplyTogether(Enchantment ench) {
        return !(ench instanceof ProtectionEnchantment) && super.canApplyTogether(ench);
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