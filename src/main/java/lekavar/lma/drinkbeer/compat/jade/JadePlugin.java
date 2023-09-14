package lekavar.lma.drinkbeer.compat.jade;

import lekavar.lma.drinkbeer.blockentities.BeerBarrelBlockEntity;
import lekavar.lma.drinkbeer.blocks.BeerBarrelBlock;
import lekavar.lma.drinkbeer.blocks.MixedBeerBlock;
import lekavar.lma.drinkbeer.compat.jade.provider.BeerBarrelComponentProvider;
import lekavar.lma.drinkbeer.compat.jade.provider.MixedBeerComponentProvider;
import snownee.jade.api.*;

@WailaPlugin
public class JadePlugin implements IWailaPlugin {
	@Override
	public void registerClient(IWailaClientRegistration registration) {
		registration.registerBlockComponent(BeerBarrelComponentProvider.INSTANCE, BeerBarrelBlock.class);
		registration.registerBlockComponent(MixedBeerComponentProvider.INSTANCE, MixedBeerBlock.class);

		registration.registerBlockIcon(MixedBeerComponentProvider.INSTANCE, MixedBeerBlock.class);
	}

	@Override
	public void register(IWailaCommonRegistration registration) {
		registration.registerBlockDataProvider(BeerBarrelComponentProvider.INSTANCE, BeerBarrelBlockEntity.class);
	}
}
