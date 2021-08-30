package mintnetwork.modularenchantments.Enchantments;

import mintnetwork.modularenchantments.ModularEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.LootBonusEnchantment;
import net.minecraft.enchantment.SilkTouchEnchantment;
import net.minecraft.inventory.EquipmentSlotType;

public class SiftingEnchantment extends Enchantment {
    public SiftingEnchantment(){
        super(Rarity.RARE, ModularEnchantments.shovel , new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});
    }

    public int getMinEnchantability(int enchantmentLevel){return 1 + (enchantmentLevel) * 8; }

    public int getMaxEnchantability(int enchantmentLevel){ return this.getMinEnchantability(enchantmentLevel) + 15; }

    public int getMaxLevel()
    {
        return 3;
    }

    public boolean canApplyTogether(Enchantment ench){
        return !(ench instanceof LootBonusEnchantment) && !(ench instanceof SilkTouchEnchantment) && super.canApplyTogether(ench);
    }
}

