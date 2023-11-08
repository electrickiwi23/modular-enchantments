package mintnetwork.modularenchantments.Events;

import mintnetwork.modularenchantments.ModularEnchantments;
import mintnetwork.modularenchantments.setup.Registration;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = ModularEnchantments.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class CreateAttributeEvent {



    @SubscribeEvent
    public static void CreateAttributes(final EntityAttributeCreationEvent event) {
        event.put(Registration.ENCHANTMITE.get(), Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 4.0D).add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.ATTACK_DAMAGE, 1.0D).build());
    }

}
