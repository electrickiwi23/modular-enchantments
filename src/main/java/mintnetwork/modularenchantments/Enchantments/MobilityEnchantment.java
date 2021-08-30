package mintnetwork.modularenchantments.Enchantments;

import mintnetwork.modularenchantments.ModularEnchantments;
import net.minecraft.enchantment.*;
import net.minecraft.inventory.EquipmentSlotType;

public class MobilityEnchantment extends Enchantment {
    public MobilityEnchantment(){
        super(Rarity.COMMON, ModularEnchantments.shield, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});
    }

    public int getMinEnchantability(int enchantmentLevel){return (enchantmentLevel - 1) * 11;}

    public int getMaxEnchantability(int enchantmentLevel){ return this.getMinEnchantability(enchantmentLevel) + 11; }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 4;
    }

    /**
     * Determines if the enchantment passed can be applyied together with this enchantment.
     */
    public boolean canApplyTogether(Enchantment ench) {
        return super.canApplyTogether(ench);
    }

}

