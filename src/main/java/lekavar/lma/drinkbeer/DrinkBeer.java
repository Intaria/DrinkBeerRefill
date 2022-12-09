package lekavar.lma.drinkbeer;

import lekavar.lma.drinkbeer.networking.NetWorking;
import lekavar.lma.drinkbeer.registries.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("drinkbeer")
public class DrinkBeer {
    // We don't need this logger now since there is no need at all.
    // Directly reference a log4j logger.

    public static final String MOD_ID = "drinkbeer";

    public DrinkBeer() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MobEffectRegistry.STATUS_EFFECTS.register(modEventBus);
        ItemRegistry.ITEMS.register(modEventBus);
        BlockRegistry.BLOCKS.register(modEventBus);
        BlockEntityRegistry.BLOKC_ENTITIES.register(modEventBus);
        SoundEventRegistry.SOUNDS.register(modEventBus);
        MenuTypeRegistry.MENUS.register(modEventBus);
        RecipeRegistry.RECIPE_TYPES.register(modEventBus);
        RecipeRegistry.RECIPE_SERIALIZERS.register(modEventBus);
        ParticleTypeRegistry.PARTICLES.register(modEventBus);

        modEventBus.addListener(NetWorking::init);

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> DrinkBeerClient::new);

    }

}
