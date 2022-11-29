package mintnetwork.modularenchantments.Enchantments;

import mintnetwork.modularenchantments.ModularEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;

public class CounterEnchantment extends Enchantment {
    public CounterEnchantment(){
        super(Rarity.RARE, ModularEnchantments.shield, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    public int getMinCost(int enchantmentLevel){ return 10 + 20 * (enchantmentLevel - 1); }

    public int getMaxCost(int enchantmentLevel){ return super.getMinCost(enchantmentLevel) + 30; }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 2;
    }

    /**
     * Determines if the enchantment passed can be applyied together with this enchantment.
     */
    public boolean checkCompatibility(Enchantment ench) {

        return !(ench instanceof RepulsionEnchantment) &&  super.checkCompatibility(ench);
    }

}

