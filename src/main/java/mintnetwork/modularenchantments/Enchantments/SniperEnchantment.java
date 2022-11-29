package mintnetwork.modularenchantments.Enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class SniperEnchantment extends Enchantment {

    public SniperEnchantment() {

        super(Rarity.RARE, EnchantmentCategory.BOW, new EquipmentSlot[] {EquipmentSlot.MAINHAND});

    }


    public int getMinCost(int enchantmentLevel){return 20; }

    public int getMaxCost(int enchantmentLevel){ return 50; }

    public int getMaxLevel()
    {
        return 1;
    }

    public boolean checkCompatibility(Enchantment ench){
        return !(ench instanceof VolleyEnchantment) && super.checkCompatibility(ench);
    }
}

