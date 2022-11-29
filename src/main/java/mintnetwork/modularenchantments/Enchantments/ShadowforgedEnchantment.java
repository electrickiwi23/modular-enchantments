package mintnetwork.modularenchantments.Enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.DamageEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class ShadowforgedEnchantment extends Enchantment {
    public ShadowforgedEnchantment(){
        super(Rarity.VERY_RARE, EnchantmentCategory.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    public int getMinCost(int enchantmentLevel) { return 15; }

    public int getMaxCost(int enchantmentLevel) {        return 60;    }

    public int getMaxLevel()
    {
        return 1;
    }

    @Override
    public boolean isTreasureOnly() { return true; }

    public boolean checkCompatibility(Enchantment ench) {
        return !(ench instanceof DamageEnchantment)&!(ench instanceof VenomEnchantment)& super.checkCompatibility(ench);
    }
}
