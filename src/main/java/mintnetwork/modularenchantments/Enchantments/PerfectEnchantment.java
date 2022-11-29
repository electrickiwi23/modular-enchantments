package mintnetwork.modularenchantments.Enchantments;

import mintnetwork.modularenchantments.ModularEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;

public class PerfectEnchantment extends Enchantment {
    public PerfectEnchantment(){
        super(Rarity.VERY_RARE, ModularEnchantments.shield, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    public int getMinCost(int enchantmentLevel){ return 20; }

    public int getMaxCost(int enchantmentLevel){ return 50; }  /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 1;
    }

    public boolean checkCompatibility(Enchantment ench){
        return !ench.category.equals(ModularEnchantments.shield);
    }

}

