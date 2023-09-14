package lekavar.lma.drinkbeer.registries;

import lekavar.lma.drinkbeer.DrinkBeer;
import lekavar.lma.drinkbeer.gui.*;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType ;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

// Register Container & ContainerScreen in one class.
// Automatically Registering Static Event Handlers, see https://mcforge.readthedocs.io/en/1.16.x/events/intro/#automatically-registering-static-event-handlers
@Mod.EventBusSubscriber(modid = DrinkBeer.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MenuTypeRegistry {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, DrinkBeer.MOD_ID);
    public static final RegistryObject<MenuType<BeerBarrelMenu>> beerBarrelContainer = MENUS.register("beer_barrel_container", () -> IForgeMenuType.create(BeerBarrelMenu::new));
    
    @SubscribeEvent
    public static void registerContainerScreen(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(MenuTypeRegistry.beerBarrelContainer.get(), BeerBarrelScreen::new);
        });
    }
}