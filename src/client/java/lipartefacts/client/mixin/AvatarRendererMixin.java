package lipartefacts.client.mixin;

import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import lipartefacts.Lipartefacts;
import lipartefacts.artefacts.ArtefactList;
import lipartefacts.client.IncognitoSkinRenderState;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Avatar;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.PlayerModelType;
import net.minecraft.world.entity.player.PlayerSkin;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

@Mixin(AvatarRenderer.class)
public abstract class AvatarRendererMixin {

    private static final Identifier INCOGNITO_SKIN = Lipartefacts.id("textures/entity/player/wide/incognito.png");
    private static final Map<Item, Identifier> INCOGNITO_SKIN_LIST = Map.of(
        ArtefactList.ARMOR.INCOGNITO_PAPER_BAG_ALEX.HELMET, Lipartefacts.id("textures/entity/player/wide/incognito_paper_bag_alex.png"),
        ArtefactList.ARMOR.INCOGNITO_PAPER_BAG_CREEPER.HELMET, Lipartefacts.id("textures/entity/player/wide/incognito_paper_bag_creeper.png"),
        ArtefactList.ARMOR.INCOGNITO_PAPER_BAG_MENACING.HELMET, Lipartefacts.id("textures/entity/player/wide/incognito_paper_bag_menacing.png"),
        ArtefactList.ARMOR.INCOGNITO_PAPER_BAG_MUMBO.HELMET, Lipartefacts.id("textures/entity/player/wide/incognito_paper_bag_mumbo.png"),
        ArtefactList.ARMOR.INCOGNITO_PAPER_BAG_STEVE.HELMET, Lipartefacts.id("textures/entity/player/wide/incognito_paper_bag_steve.png")
    );

    @Inject(
        at = @At("TAIL"),
        method = "extractRenderState"
    )
    private void lipartefacts$checkHelmet(
        Avatar entity,
        AvatarRenderState state,
        float partialTicks,
        CallbackInfo callbackInfo
    ) {
        ItemStack headItemStack = entity.getItemBySlot(EquipmentSlot.HEAD);

        for (var entry: INCOGNITO_SKIN_LIST.entrySet()) {
            if (headItemStack.is(entry.getKey())) {
                ((IncognitoSkinRenderState) state).lipartefacts$setIncognitoItem(entry.getKey());
                state.skin = new PlayerSkin(state.skin.body(), null, null, PlayerModelType.WIDE, state.skin.secure());
                return;
            }
        }
    }

    @Inject(
        at = @At("HEAD"),
        cancellable = true,
        method = "getTextureLocation"
    )
    private void liaprtefacts$replaceSkin(
        AvatarRenderState state,
        CallbackInfoReturnable<Identifier> callbackInfoReturnable
    ) {
        Item item = ((IncognitoSkinRenderState) state).lipartefacts$getIncognitoItem();

        if (item != null) callbackInfoReturnable.setReturnValue(INCOGNITO_SKIN_LIST.getOrDefault(item, INCOGNITO_SKIN));
    }

}
