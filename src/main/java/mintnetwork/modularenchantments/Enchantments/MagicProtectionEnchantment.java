package mintnetwork.modularenchantments.Enchantments;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.ProtectionEnchantment;

public class MagicProtectionEnchantment extends Enchantment {

    public MagicProtectionEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentCategory.ARMOR, new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET});
    }

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
    public int getMinCost(int enchantmentLevel) {
        return 7 + (enchantmentLevel - 1) * 8;
    }

    public int getMaxCost(int enchantmentLevel) {
        return this.getMinCost(enchantmentLevel) + 8;
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
    public int getDamageProtection(int level, DamageSource source) {
        if (source.isBypassInvul()) {
            return 0;
        } else {
            return source.isMagic() ? (int) (level * 2.5) : 0;
        }
    }

    /**
     * Determines if the enchantment passed can be applyied together with this enchantment.
     */
    public boolean checkCompatibility(Enchantment ench) {
        return !(ench instanceof ProtectionEnchantment) && super.checkCompatibility(ench);
    }


}