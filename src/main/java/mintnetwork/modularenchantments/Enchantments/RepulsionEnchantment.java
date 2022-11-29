package mintnetwork.modularenchantments.Enchantments;

import mintnetwork.modularenchantments.ModularEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;


public class RepulsionEnchantment extends Enchantment {
    public RepulsionEnchantment(){
        super(Rarity.RARE, ModularEnchantments.shield, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    public int getMinCost(int enchantmentLevel){return (enchantmentLevel) * 8; }

    public int getMaxCost(int enchantmentLevel){ return this.getMinCost(enchantmentLevel) + 16; }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 3;
    }

    /**
     * Determines if the enchantment passed can be applyied together with this enchantment.
     */
    public boolean checkCompatibility(Enchantment ench) {
        return !(ench instanceof CounterEnchantment) &&  super.checkCompatibility(ench);
    }

}

