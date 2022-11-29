package mintnetwork.modularenchantments.Enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.ArrowDamageEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class VolleyEnchantment extends Enchantment {

    public VolleyEnchantment() {


        super(Rarity.UNCOMMON, EnchantmentCategory.BOW, new EquipmentSlot[] {EquipmentSlot.MAINHAND});

    }


    public int getMinCost(int enchantmentLevel){return (enchantmentLevel) * 10; }

    public int getMaxCost(int enchantmentLevel){ return this.getMinCost(enchantmentLevel) + 20; }

    public int getMaxLevel()
    {
        return 3;
    }

    public boolean checkCompatibility(Enchantment ench){
        return !(ench instanceof ArrowDamageEnchantment) && super.checkCompatibility(ench);
    }
}

