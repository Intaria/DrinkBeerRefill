package lekavar.lma.drinkbeer.registries;

import lekavar.lma.drinkbeer.DrinkBeer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundEventRegistry {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, DrinkBeer.MOD_ID);
    public static final RegistryObject<SoundEvent> DRINKING_BEER = register("drinking_beer");
    public static final RegistryObject<SoundEvent> POURING = register("pouring");
    public static final RegistryObject<SoundEvent> POURING_CHRISTMAS = register("pouring_christmas");
    public static final RegistryObject<SoundEvent> IRON_CALL_BELL_TINKLING = register("iron_call_bell_tinkle");
    public static final RegistryObject<SoundEvent> GOLDEN_CALL_BELL_TINKLING = register("golden_call_bell_tinkle");
    public static final RegistryObject<SoundEvent> LEKAS_CALL_BELL_TINKLE = register("lekas_call_bell_tinkle");
    public static final RegistryObject<SoundEvent>[] NIGHT_HOWL = new RegistryObject[]{register("night_howl0"),register("night_howl1"),register("night_howl2"), register("night_howl3")};

    private static RegistryObject<SoundEvent> register(String name) {
        return SOUNDS.register(name, () -> new SoundEvent(new ResourceLocation("drinkbeer", name)));
    }
}
