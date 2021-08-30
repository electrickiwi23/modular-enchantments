package mintnetwork.modularenchantments.Listeners;

import mintnetwork.modularenchantments.ModularEnchantments;
import mintnetwork.modularenchantments.setup.Registration;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = ModularEnchantments.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class CreateAttributeEvent {



    @SubscribeEvent
    public static void arrowShot(final EntityAttributeCreationEvent event) {
        event.put(Registration.ENCHANTMITE.get(), MonsterEntity.func_234295_eP_().createMutableAttribute(Attributes.MAX_HEALTH, 4.0D).createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 1.0D).create());
    }

}
