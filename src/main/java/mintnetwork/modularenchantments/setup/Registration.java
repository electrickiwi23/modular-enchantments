package mintnetwork.modularenchantments.setup;

import mintnetwork.modularenchantments.Blocks.CrustedMagma;
import mintnetwork.modularenchantments.Blocks.HoningTableBlock;
import mintnetwork.modularenchantments.Blocks.HoningTableContainer;
import mintnetwork.modularenchantments.Enchantments.*;
import mintnetwork.modularenchantments.Entities.EnchantMiteEntity;
import mintnetwork.modularenchantments.LootModifiers.AutoSmeltModifier;
import mintnetwork.modularenchantments.LootModifiers.BountifulModifier;
import mintnetwork.modularenchantments.LootModifiers.SiftingModifier;
import mintnetwork.modularenchantments.ModularEnchantments;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.gui.DisplayEffectsScreen;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registration {

    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, ModularEnchantments.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ModularEnchantments.MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, ModularEnchantments.MOD_ID);
    public static final DeferredRegister<GlobalLootModifierSerializer<?>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, ModularEnchantments.MOD_ID);
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS,ModularEnchantments.MOD_ID);

    public static final RegistryObject<EntityType<EnchantMiteEntity>> ENCHANTMITE = ENTITIES.register("enchantmite",() -> (EntityType.Builder.<EnchantMiteEntity>create(EnchantMiteEntity::new,EntityClassification.MONSTER).size(0.4F, 0.3F).trackingRange(8)).build("enchantmite"));

    public static final RegistryObject<GlobalLootModifierSerializer<AutoSmeltModifier>> AUTOSMELT = LOOT_MODIFIERS.register("auto_smelting", () -> new AutoSmeltModifier.Serializer(){});
    public static final RegistryObject<GlobalLootModifierSerializer<BountifulModifier>> BOUNTIFULMODIFIER = LOOT_MODIFIERS.register("bountiful", () -> new BountifulModifier.Serializer(){});
    public static final RegistryObject<GlobalLootModifierSerializer<SiftingModifier>> SIFTINGMODIFIER = LOOT_MODIFIERS.register("sifting_modifier", () -> new SiftingModifier.Serializer(){});

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
    public static final RegistryObject<Enchantment> MAGMAWALKING = ENCHANTMENTS.register("magma_walker", () -> new MagmaWalker(){});


    public static final RegistryObject<Enchantment> SHADOWFORGED = ENCHANTMENTS.register("shadowforged", () -> new ShadowforgedEnchantment(){});
    public static final RegistryObject<Enchantment> VAMPIRISM = ENCHANTMENTS.register("vampirism", () -> new VampirismEnchantment(){});
    public static final RegistryObject<Enchantment> VENOM = ENCHANTMENTS.register("venom", () -> new VenomEnchantment(){});
    public static final RegistryObject<Enchantment> FROSTASPECT = ENCHANTMENTS.register("frost_aspect", () -> new FrostAspectEnchantment(){});

    public static final RegistryObject<Enchantment> VOLLEY = ENCHANTMENTS.register("volley", () -> new VolleyEnchantment(){});
    public static final RegistryObject<Enchantment> SNIPER = ENCHANTMENTS.register("sniper", () -> new SniperEnchantment(){});
//    public static final RegistryObject<Enchantment> RICOCHET  = ENCHANTMENTS.register("ricochet", () -> new RicochetEnchantment(){});

    public static final RegistryObject<Enchantment> DAMPENED  = ENCHANTMENTS.register("suppression", () -> new SuppressionEnchantment(){});
    public static final RegistryObject<Enchantment> POTENCY  = ENCHANTMENTS.register("potency", () -> new PotencyEnchantment(){});

    public static final RegistryObject<Enchantment> REPULSION  = ENCHANTMENTS.register("repulsion", () -> new RepulsionEnchantment(){});
    public static final RegistryObject<Enchantment> MOBILITY  = ENCHANTMENTS.register("mobility", () -> new MobilityEnchantment(){});
    public static final RegistryObject<Enchantment> Recovery  = ENCHANTMENTS.register("recovery", () -> new RecoveryEnchantment(){});
    public static final RegistryObject<Enchantment> COUNTER  = ENCHANTMENTS.register("counter", () -> new CounterEnchantment(){});
    public static final RegistryObject<Enchantment> PERFECT  = ENCHANTMENTS.register("perfect", () -> new PerfectEnchantment(){});

    public static final RegistryObject<Enchantment> FRAGILITY = ENCHANTMENTS.register("fragile_curse", () -> new FragilityCurse(){});
    public static final RegistryObject<Enchantment> Heavy = ENCHANTMENTS.register("heavy_curse", () -> new HeavyCurse(){});
    public static final RegistryObject<Enchantment> DESTRUCTION = ENCHANTMENTS.register("destruction_curse", () -> new DestructionCurse(){});
    public static final RegistryObject<Enchantment> PAIN = ENCHANTMENTS.register("pain_curse", () -> new PainCurse(){});




    public static final RegistryObject<ContainerType<HoningTableContainer>> HONINGTABLECONTAINER = CONTAINERS.register("honing_table_container", () -> new ContainerType<>(HoningTableContainer::getClientContainer));

    public static final RegistryObject<Block> CRUSTEDMAGAMA = BLOCKS.register("crusted_magma_block", () -> new CrustedMagma(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.NETHERRACK).setRequiresTool().setLightLevel((state) -> {
        return 3;
    }).tickRandomly().hardnessAndResistance(0.5F).setAllowsSpawn((state, reader, pos, entity) -> {
        return entity.isImmuneToFire();
    }).setNeedsPostProcessing(Blocks::needsPostProcessing).setEmmisiveRendering(Blocks::needsPostProcessing)){});
//    public static final RegistryObject<Block> HONINGTABLE = BLOCKS.register("honing_table", () -> new HoningTableBlock(){});

    public static final DeferredRegister<Item> REPLACEITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "minecraft");

    public static final RegistryObject<Item> ENCHANTABLESHIELD = REPLACEITEMS.register("shield", () -> new CustomShield((new Item.Properties()).maxDamage(336).group(ItemGroup.COMBAT)));

    public void register(){
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(modEventBus);
        ENCHANTMENTS.register(modEventBus);
        REPLACEITEMS.register(modEventBus);
        ENTITIES.register(modEventBus);
        LOOT_MODIFIERS.register(modEventBus);
//        CONTAINERS.register(modEventBus);




    }
}
