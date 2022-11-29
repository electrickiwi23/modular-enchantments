package mintnetwork.modularenchantments.Enchantments;

import mintnetwork.modularenchantments.ModularEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.LootBonusEnchantment;
import net.minecraft.world.item.enchantment.UntouchingEnchantment;

public class SiftingEnchantment extends Enchantment {
    public SiftingEnchantment(){
        super(Rarity.RARE, ModularEnchantments.shovel , new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    public int getMinCost(int enchantmentLevel){return 1 + (enchantmentLevel) * 8; }

    public int getMaxCost(int enchantmentLevel){ return this.getMinCost(enchantmentLevel) + 15; }

    public int getMaxLevel()
    {
        return 3;
    }

    public boolean checkCompatibility(Enchantment ench){
        return !(ench instanceof LootBonusEnchantment) && !(ench instanceof UntouchingEnchantment) && super.checkCompatibility(ench);
    }
}

