package lipartefacts.client.mixin;

import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.world.item.Item;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import lipartefacts.client.IncognitoSkinRenderState;

@Mixin(AvatarRenderState.class)
public class AvatarRenderStateMixin implements IncognitoSkinRenderState {

    @Unique
    private Item lipartefacts$incognitoItem;

    @Override
    public Item lipartefacts$getIncognitoItem() {
        return this.lipartefacts$incognitoItem;
    }

    @Override
    public void lipartefacts$setIncognitoItem(Item incognitoItem) {
        this.lipartefacts$incognitoItem = incognitoItem;
    }

}
