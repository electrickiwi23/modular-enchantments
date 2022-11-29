package mintnetwork.modularenchantments.Enchantments;

import mintnetwork.modularenchantments.ModularEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;

public class MobilityEnchantment extends Enchantment {
    public MobilityEnchantment(){
        super(Rarity.COMMON, ModularEnchantments.shield, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    public int getMinCost(int enchantmentLevel){return (enchantmentLevel - 1) * 11;}

    public int getMaxCost(int enchantmentLevel){ return this.getMinCost(enchantmentLevel) + 11; }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 4;
    }

    /**
     * Determines if the enchantment passed can be applyied together with this enchantment.
     */
    public boolean checkCompatibility(Enchantment ench) {
        return super.checkCompatibility(ench);
    }


}

