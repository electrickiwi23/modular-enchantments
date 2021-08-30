package mintnetwork.modularenchantments.Enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class SniperEnchantment extends Enchantment {

    public SniperEnchantment() {

        super(Rarity.RARE, EnchantmentType.BOW, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});

    }


    public int getMinEnchantability(int enchantmentLevel){return 20; }

    public int getMaxEnchantability(int enchantmentLevel){ return 50; }

    public int getMaxLevel()
    {
        return 1;
    }

    public boolean canApplyTogether(Enchantment ench){
        return !(ench instanceof VolleyEnchantment) && super.canApplyTogether(ench);
    }
}

