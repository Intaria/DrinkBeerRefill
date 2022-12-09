package lekavar.lma.drinkbeer.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import lekavar.lma.drinkbeer.blockentities.BartendingTableBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.item.ItemStack;

public class BartendingTableBlockEntityRenderer implements BlockEntityRenderer<BartendingTableBlockEntity> {

    public BartendingTableBlockEntityRenderer(BlockEntityRendererProvider.Context context) {

    }
    @Override
    public void render(BartendingTableBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        pPoseStack.pushPose();

        ItemStack beerStack = pBlockEntity.takeBeer(true);
        if(!beerStack.isEmpty()){
            pPoseStack.translate(0.5, 1.25, 0.5);
            //Get light at the beer block
            int lightAbove = LevelRenderer.getLightColor(pBlockEntity.getLevel(), pBlockEntity.getBlockPos().above());
            //Render beer
            Minecraft.getInstance().getItemRenderer().renderStatic(beerStack, ItemTransforms.TransformType.GROUND ,lightAbove, pPackedOverlay,pPoseStack,pBufferSource,0);
        }

        pPoseStack.popPose();
    }
}
