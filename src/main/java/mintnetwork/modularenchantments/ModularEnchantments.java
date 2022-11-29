package mintnetwork.modularenchantments;

import mintnetwork.modularenchantments.setup.Registration;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Predicate;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ModularEnchantments.MOD_ID)
public class ModularEnchantments {


    public static final String MOD_ID = "modular_enchantments";

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public static EnchantmentCategory shield = EnchantmentCategory.create("SHIELD", new Predicate<Item>() {
        @Override
        public boolean test(Item item) {
            return (item instanceof ShieldItem);
        }

    });

    public static EnchantmentCategory shovel = EnchantmentCategory.create("SHOVEL", item -> (item instanceof ShovelItem));
    public static EnchantmentCategory hoe = EnchantmentCategory.create("HOE", item -> (item instanceof HoeItem));
    public static EnchantmentCategory axe = EnchantmentCategory.create("AXE", item -> (item instanceof AxeItem));
    public static EnchantmentCategory pickaxe = EnchantmentCategory.create("PICKAXE", item -> (item instanceof PickaxeItem));

    public ModularEnchantments() {
        //adds the new enchantment types to the correct inventory tabs
        int t = CreativeModeTab.TAB_TOOLS.getEnchantmentCategories().length;
        EnchantmentCategory[] toolsTypes =  new EnchantmentCategory[t+4];
        for (int i = 0; i < t; i++) {
            toolsTypes[i] = CreativeModeTab.TAB_TOOLS.getEnchantmentCategories()[i];
        }
        toolsTypes[t] = shovel;
        toolsTypes[t+1] = pickaxe;
        toolsTypes[t+2] = axe;
        toolsTypes[t+3] = hoe;

        CreativeModeTab.TAB_TOOLS.setEnchantmentCategories(toolsTypes);


        int n = CreativeModeTab.TAB_COMBAT.getEnchantmentCategories().length;
        EnchantmentCategory[] combatTypes = new EnchantmentCategory[n+1];
        for (int i = 0; i < n; i++) {
            combatTypes[i] = CreativeModeTab.TAB_COMBAT.getEnchantmentCategories()[i];
        }
       combatTypes[n] = shield;
        CreativeModeTab.TAB_COMBAT.setEnchantmentCategories(combatTypes);
        //------------------------------------------------------------------


        //registers the new content
        (new Registration()).register();


        MinecraftForge.EVENT_BUS.register(this);

    }
}
