package mintnetwork.modularenchantments.setup;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShieldItem;

public class CustomShield extends ShieldItem {
    CustomShield(Item.Properties builder){
        super(builder);
    }

    public int getEnchantmentValue() {
        return 1;
    }
}
