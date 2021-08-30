package mintnetwork.modularenchantments.Enchantments;

import mintnetwork.modularenchantments.ModularEnchantments;
import net.minecraft.enchantment.*;
import net.minecraft.inventory.EquipmentSlotType;

public class RepulsionEnchantment extends Enchantment {
    public RepulsionEnchantment(){
        super(Rarity.RARE, ModularEnchantments.shield, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});
    }

    public int getMinEnchantability(int enchantmentLevel){return (enchantmentLevel) * 8; }

    public int getMaxEnchantability(int enchantmentLevel){ return this.getMinEnchantability(enchantmentLevel) + 16; }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 3;
    }

    /**
     * Determines if the enchantment passed can be applyied together with this enchantment.
     */
    public boolean canApplyTogether(Enchantment ench) {
        return !(ench instanceof CounterEnchantment) &&  super.canApplyTogether(ench);
    }

}

