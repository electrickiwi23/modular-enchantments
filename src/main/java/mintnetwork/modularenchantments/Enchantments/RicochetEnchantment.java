package mintnetwork.modularenchantments.Enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;


public class RicochetEnchantment extends Enchantment {

    public RicochetEnchantment() {

        super(Rarity.RARE, EnchantmentCategory.BOW, new EquipmentSlot[] {EquipmentSlot.MAINHAND});

    }


    public int getMinCost(int enchantmentLevel){return 1 + (enchantmentLevel) * 10; }

    public int getMaxCost(int enchantmentLevel){ return this.getMinCost(enchantmentLevel) + 15; }

    public int getMaxLevel()
    {
        return 3;
    }

    public boolean checkCompatibility(Enchantment ench){
        return !(ench instanceof VolleyEnchantment) && super.checkCompatibility(ench);
    }
}

