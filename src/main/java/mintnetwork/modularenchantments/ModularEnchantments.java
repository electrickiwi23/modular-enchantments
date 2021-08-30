package mintnetwork.modularenchantments;

import mintnetwork.modularenchantments.Listeners.ArrowUpdateListener;
import mintnetwork.modularenchantments.Listeners.BlockDestroyListener;
import mintnetwork.modularenchantments.Listeners.BowShootListener;
import mintnetwork.modularenchantments.setup.Registration;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropsBlock;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.item.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Predicate;
import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ModularEnchantments.MOD_ID)
public class ModularEnchantments {


    public static final String MOD_ID = "modular_enchantments";

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public static EnchantmentType shield = EnchantmentType.create("SHIELD", new Predicate<Item>() {
        @Override
        public boolean test(Item item) {
            return (item instanceof ShieldItem);
        }

    });

    public static EnchantmentType shovel = EnchantmentType.create("SHOVEL", item -> (item instanceof ShovelItem));
    public static EnchantmentType hoe = EnchantmentType.create("HOE", item -> (item instanceof HoeItem));
    public static EnchantmentType axe = EnchantmentType.create("AXE", item -> (item instanceof AxeItem));
    public static EnchantmentType pickaxe = EnchantmentType.create("PICKAXE", item -> (item instanceof PickaxeItem));

    public ModularEnchantments() {
        int t = ItemGroup.TOOLS.getRelevantEnchantmentTypes().length;
        EnchantmentType[] toolsTypes =  new EnchantmentType[t+4];
        for (int i = 0; i < t; i++) {
            toolsTypes[i] = ItemGroup.TOOLS.getRelevantEnchantmentTypes()[i];
        }
        toolsTypes[t] = shovel;
        toolsTypes[t+1] = pickaxe;
        toolsTypes[t+2] = axe;
        toolsTypes[t+3] = hoe;

        ItemGroup.TOOLS.setRelevantEnchantmentTypes(toolsTypes);


        int n = ItemGroup.COMBAT.getRelevantEnchantmentTypes().length;
        EnchantmentType[] combatTypes =  new EnchantmentType[n+1];
        for (int i = 0; i < n; i++) {
            combatTypes[i] = ItemGroup.COMBAT.getRelevantEnchantmentTypes()[i];
        }
       combatTypes[n] = shield;
        ItemGroup.COMBAT.setRelevantEnchantmentTypes(combatTypes);

        new Registration().register();
        // Register the setup method for modloading
//        MinecraftForge.EVENT_BUS.register(new BowShootListener());
//        MinecraftForge.EVENT_BUS.register(new BlockDestroyListener());
//        MinecraftForge.EVENT_BUS.register(new ArrowUpdateListener());
//        MinecraftForge.EVENT_BUS.register(new (BlockDestroyListener));



        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("modular-enchantments", "helloworld", () -> {
            LOGGER.info("Hello world from the MDK");
            return "Hello world";
        });
    }

    private void processIMC(final InterModProcessEvent event) {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m -> m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
}
