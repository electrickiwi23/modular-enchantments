package mintnetwork.modularenchantments.setup;

import mintnetwork.modularenchantments.Blocks.CrustedMagma;
import mintnetwork.modularenchantments.Enchantments.*;
import mintnetwork.modularenchantments.Entities.EnchantMiteEntity;
import mintnetwork.modularenchantments.LootModifiers.ModLootModifers;
import mintnetwork.modularenchantments.ModularEnchantments;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Registration {

    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, ModularEnchantments.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ModularEnchantments.MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ModularEnchantments.MOD_ID);




    public static final RegistryObject<EntityType<EnchantMiteEntity>> ENCHANTMITE = ENTITIES.register("enchantmite",() -> (EntityType.Builder.<EnchantMiteEntity>of(EnchantMiteEntity::new, MobCategory.MONSTER).sized(0.4F, 0.3F).setTrackingRange(8)).build("enchantmite"));


    public static final RegistryObject<Enchantment> UTILITY = ENCHANTMENTS.register("utility", () -> new UtilityEnchantment(){});
    public static final RegistryObject<Enchantment> EXPERIENCE = ENCHANTMENTS.register("experience", () -> new ExperienceEnchantment(){});
    public static final RegistryObject<Enchantment> BATTING = ENCHANTMENTS.register("batting", ()-> new BattingEnchantment(){});
    public static final RegistryObject<Enchantment> SIFTING = ENCHANTMENTS.register("sifting", ()-> new SiftingEnchantment(){});
    public static final RegistryObject<Enchantment> PUNCTURING = ENCHANTMENTS.register("puncturing", ()-> new PunctureingEnchantment(){});
    public static final RegistryObject<Enchantment> SMELTING = ENCHANTMENTS.register("smelting", ()-> new SmeltingEnchantment(){});
    public static final RegistryObject<Enchantment> GLORY = ENCHANTMENTS.register("glory", ()-> new GloryEnchantment(){});
    public static final RegistryObject<Enchantment> BOUNTIFUL = ENCHANTMENTS.register("bountiful", ()-> new BountifulEnchantment(){});

    public static final RegistryObject<Enchantment> MAGICPROT = ENCHANTMENTS.register("magic_protection", () -> new MagicProtectionEnchantment(){});
    public static final RegistryObject<Enchantment> INFESTATION  = ENCHANTMENTS.register("infestation", () -> new InfestationEnchantment(){});

    public static final RegistryObject<Enchantment> AIRAFFINITY = ENCHANTMENTS.register("air_affinity", () -> new AirWorkerEnchantment(){});
    public static final RegistryObject<Enchantment> MAGMAWALKING = ENCHANTMENTS.register("magma_walker", () -> new MagmaWalker(){});


    public static final RegistryObject<Enchantment> SHADOWFORGED = ENCHANTMENTS.register("shadowforged", () -> new ShadowforgedEnchantment(){});
    public static final RegistryObject<Enchantment> VAMPIRISM = ENCHANTMENTS.register("vampirism", () -> new VampirismEnchantment(){});
    public static final RegistryObject<Enchantment> VENOM = ENCHANTMENTS.register("venom", () -> new VenomEnchantment(){});
    public static final RegistryObject<Enchantment> FROSTASPECT = ENCHANTMENTS.register("frost_aspect", () -> new FrostAspectEnchantment(){});

    public static final RegistryObject<Enchantment> VOLLEY = ENCHANTMENTS.register("volley", () -> new VolleyEnchantment(){});
    public static final RegistryObject<Enchantment> SNIPER = ENCHANTMENTS.register("sniper", () -> new SniperEnchantment(){});
//    public static final RegistryObject<Enchantment> RICOCHET  = ENCHANTMENTS.register("ricochet", () -> new RicochetEnchantment(){});

    public static final RegistryObject<Enchantment> BARBED = ENCHANTMENTS.register("barbed", () -> new BarbedEnchantment(){});
    public static final RegistryObject<Enchantment> YANKING = ENCHANTMENTS.register("yanking", () -> new FishingRodPullEnchantment(){});

    public static final RegistryObject<Enchantment> DAMPENED  = ENCHANTMENTS.register("suppression", () -> new SuppressionEnchantment(){});
    public static final RegistryObject<Enchantment> POTENCY  = ENCHANTMENTS.register("potency", () -> new PotencyEnchantment(){});

    public static final RegistryObject<Enchantment> REPULSION  = ENCHANTMENTS.register("repulsion", () -> new RepulsionEnchantment(){});
    public static final RegistryObject<Enchantment> MOBILITY  = ENCHANTMENTS.register("mobility", () -> new MobilityEnchantment(){});
    public static final RegistryObject<Enchantment> RECOVERY  = ENCHANTMENTS.register("recovery", () -> new RecoveryEnchantment(){});
    public static final RegistryObject<Enchantment> COUNTER  = ENCHANTMENTS.register("counter", () -> new CounterEnchantment(){});
    public static final RegistryObject<Enchantment> PERFECT  = ENCHANTMENTS.register("perfect", () -> new PerfectEnchantment(){});

    public static final RegistryObject<Enchantment> FRAGILITY = ENCHANTMENTS.register("fragile_curse", () -> new FragilityCurse(){});
    public static final RegistryObject<Enchantment> Heavy = ENCHANTMENTS.register("heavy_curse", () -> new HeavyCurse(){});
    public static final RegistryObject<Enchantment> DESTRUCTION = ENCHANTMENTS.register("destruction_curse", () -> new DestructionCurse(){});
    public static final RegistryObject<Enchantment> PAIN = ENCHANTMENTS.register("pain_curse", () -> new PainCurse(){});



    public static final RegistryObject<Block> CRUSTEDMAGAMA = BLOCKS.register("crusted_magma_block", () -> new CrustedMagma(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.NETHER).requiresCorrectToolForDrops().lightLevel((state) -> {
        return 3;
    }).randomTicks().strength(0.5F).isValidSpawn((p_187421_, p_187422_, p_187423_, p_187424_) -> p_187424_.fireImmune()).hasPostProcess((p_61036_, p_61037_, p_61038_) -> true).emissiveRendering((p_61036_, p_61037_, p_61038_) -> true)));
//    public static final RegistryObject<Block> HONINGTABLE = BLOCKS.register("honing_table", () -> new HoningTableBlock(){});

    public static final DeferredRegister<Item> REPLACEITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "minecraft");

    public static final RegistryObject<Item> ENCHANTABLESHIELD = REPLACEITEMS.register("shield", () -> new CustomShield((new Item.Properties()).durability(336).tab(CreativeModeTab.TAB_COMBAT)));
    public void register(){
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(modEventBus);
        ENCHANTMENTS.register(modEventBus);
        REPLACEITEMS.register(modEventBus);
        ENTITIES.register(modEventBus);
        ModLootModifers.register(modEventBus);
    }
}
