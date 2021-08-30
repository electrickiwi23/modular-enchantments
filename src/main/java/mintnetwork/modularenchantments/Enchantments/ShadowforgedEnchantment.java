package mintnetwork.modularenchantments.Enchantments;

import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class ShadowforgedEnchantment extends Enchantment {
    public ShadowforgedEnchantment(){
        super(Rarity.VERY_RARE, EnchantmentType.WEAPON, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});
    }

    public int getMinEnchantability(int enchantmentLevel) { return 15; }

    public int getMaxEnchantability(int enchantmentLevel) {        return 60;    }

    public int getMaxLevel()
    {
        return 1;
    }

    @Override
    public boolean isTreasureEnchantment() { return true; }

    public boolean canApplyTogether(Enchantment ench) {
        return !(ench instanceof DamageEnchantment)&!(ench instanceof VenomEnchantment)& super.canApplyTogether(ench);
    }
}
