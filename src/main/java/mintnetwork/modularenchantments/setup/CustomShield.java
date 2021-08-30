package mintnetwork.modularenchantments.setup;

import net.minecraft.item.Item;
import net.minecraft.item.ShieldItem;

public class CustomShield extends ShieldItem {
    CustomShield(Item.Properties builder){
        super(builder);
    }

    public int getItemEnchantability() {
        return 1;
    }
}
