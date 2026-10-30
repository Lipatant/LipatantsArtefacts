package lipartefacts.client.mixin;

import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import lipartefacts.Lipartefacts;
import lipartefacts.artefacts.ArtefactList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.FirstPersonHandsAndItemsRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

@Mixin(FirstPersonHandsAndItemsRenderer.class)
public abstract class FirstPersonHandsMixin {

    private static final Identifier INCOGNITO_SKIN = Lipartefacts.id("textures/entity/player/wide/incognito.png");
    private static final Map<Item, Identifier> INCOGNITO_SKIN_LIST = Map.of(
        ArtefactList.ARMOR.INCOGNITO_PAPER_BAG_ALEX.HELMET, Lipartefacts.id("textures/entity/player/wide/incognito_paper_bag_alex.png"),
        ArtefactList.ARMOR.INCOGNITO_PAPER_BAG_CREEPER.HELMET, Lipartefacts.id("textures/entity/player/wide/incognito_paper_bag_creeper.png"),
        ArtefactList.ARMOR.INCOGNITO_PAPER_BAG_MENACING.HELMET, Lipartefacts.id("textures/entity/player/wide/incognito_paper_bag_menacing.png"),
        ArtefactList.ARMOR.INCOGNITO_PAPER_BAG_MUMBO.HELMET, Lipartefacts.id("textures/entity/player/wide/incognito_paper_bag_mumbo.png"),
        ArtefactList.ARMOR.INCOGNITO_PAPER_BAG_STEVE.HELMET, Lipartefacts.id("textures/entity/player/wide/incognito_paper_bag_steve.png")
    );

    @ModifyArg(
        at = @At(
            target = "Lnet/minecraft/client/renderer/entity/player/AvatarRenderer;renderLeftHand(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/resources/Identifier;Z)V",
            value = "INVOKE"
        ),
        index = 3,
        method = "renderPlayerHand"
    )
    private Identifier lipartefacts$renderLeftHandIncognito(Identifier original) {
        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.player == null) return original;

        ItemStack headItemStack = minecraft.player.getItemBySlot(EquipmentSlot.HEAD);

        for (var entry: INCOGNITO_SKIN_LIST.entrySet()) {
            if (headItemStack.is(entry.getKey())) {
                return entry.getValue();
            }
        }
        return original;
    }

    @ModifyArg(
        at = @At(
            target = "Lnet/minecraft/client/renderer/entity/player/AvatarRenderer;renderRightHand(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/resources/Identifier;Z)V",
            value = "INVOKE"
        ),
        index = 3,
        method = "renderPlayerHand"
    )
    private Identifier lipartefacts$renderRightHandIncognito(Identifier original) {
        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.player == null) return original;

        ItemStack headItemStack = minecraft.player.getItemBySlot(EquipmentSlot.HEAD);

        for (var entry: INCOGNITO_SKIN_LIST.entrySet()) {
            if (headItemStack.is(entry.getKey())) {
                return entry.getValue();
            }
        }
        return original;
    }

}
