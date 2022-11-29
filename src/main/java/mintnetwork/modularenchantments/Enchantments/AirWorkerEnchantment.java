package mintnetwork.modularenchantments.Enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.WaterWorkerEnchantment;

public class AirWorkerEnchantment extends Enchantment {
    public AirWorkerEnchantment() {
        super(Rarity.RARE, EnchantmentCategory.ARMOR_HEAD, new EquipmentSlot[] {EquipmentSlot.HEAD});
    }

    public int getMinCost(int p_45294_) {
        return 1;
    }

    public int getMaxCost(int p_45296_) {
        return this.getMinCost(p_45296_) + 40;
    }

    public int getMaxLevel() {
        return 1;
    }

    public boolean checkCompatibility(Enchantment ench) {
        return !(ench instanceof WaterWorkerEnchantment) &&  super.checkCompatibility(ench);
    }
}