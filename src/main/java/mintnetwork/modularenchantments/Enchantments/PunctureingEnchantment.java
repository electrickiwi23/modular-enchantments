package mintnetwork.modularenchantments.Enchantments;

import mintnetwork.modularenchantments.ModularEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.DiggingEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;

public class PunctureingEnchantment extends Enchantment {
    public PunctureingEnchantment(){
        super(Rarity.RARE, ModularEnchantments.pickaxe, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    public int getMinCost(int enchantmentLevel){ return 15; }

    public int getMaxCost(int enchantmentLevel){ return 50; }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 1; }

    /**
     * Determines if the enchantment passed can be applyied together with this enchantment.
     */
    public boolean checkCompatibility(Enchantment ench) {
        return !(ench instanceof DiggingEnchantment) &&  super.checkCompatibility(ench);
    }

}

