package mintnetwork.modularenchantments.LootModifiers;

import com.mojang.serialization.Codec;
import mintnetwork.modularenchantments.ModularEnchantments;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModLootModifers {
    public static final DeferredRegister<Codec<?extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, ModularEnchantments.MOD_ID);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> AUTO_SMELT = LOOT_MODIFIER_SERIALIZERS.register("auto_smelting", AutoSmeltModifier.CODEC);
    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> BOUNTIFUL = LOOT_MODIFIER_SERIALIZERS.register("bountiful", BountifulModifier.CODEC);
    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> SIFTING = LOOT_MODIFIER_SERIALIZERS.register("sifting_modifier", SiftingModifier.CODEC);



    public static void register(IEventBus bus){
        LOOT_MODIFIER_SERIALIZERS.register(bus);
    }

}
