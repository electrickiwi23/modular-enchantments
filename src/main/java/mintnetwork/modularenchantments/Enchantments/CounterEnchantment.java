package mintnetwork.modularenchantments.Enchantments;

import mintnetwork.modularenchantments.ModularEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;

public class CounterEnchantment extends Enchantment {
    public CounterEnchantment(){
        super(Rarity.RARE, ModularEnchantments.shield, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});
    }

    public int getMinEnchantability(int enchantmentLevel){ return 10 + 20 * (enchantmentLevel - 1); }

    public int getMaxEnchantability(int enchantmentLevel){ return super.getMinEnchantability(enchantmentLevel) + 30; }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 2;
    }

    /**
     * Determines if the enchantment passed can be applyied together with this enchantment.
     */
    public boolean canApplyTogether(Enchantment ench) {

        return !(ench instanceof RepulsionEnchantment) &&  super.canApplyTogether(ench);
    }

}

