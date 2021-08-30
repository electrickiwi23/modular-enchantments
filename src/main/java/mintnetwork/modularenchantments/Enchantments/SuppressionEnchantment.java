package mintnetwork.modularenchantments.Enchantments;

import net.minecraft.enchantment.*;
import net.minecraft.inventory.EquipmentSlotType;

public class SuppressionEnchantment extends Enchantment {
    public SuppressionEnchantment(){
        super(Rarity.RARE, EnchantmentType.CROSSBOW, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});
    }

    public int getMinEnchantability(int enchantmentLevel) {
        return 20;
    }

    public int getMaxEnchantability(int enchantmentLevel) {
        return 50;
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 1;
    }

    public boolean isTreasureEnchantment() {
        return true;
    }

    /**
     * Determines if the enchantment passed can be applyied together with this enchantment.
     */
    public boolean canApplyTogether(Enchantment ench) {
        return super.canApplyTogether(ench);
    }

}
