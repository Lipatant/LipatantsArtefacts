package lipartefacts.artefacts.armors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.mojang.datafixers.util.Pair;

import lipartefacts.ItemIdList;
import lipartefacts.ItemList;
import lipartefacts.Lipartefacts;
import lipartefacts.artefacts.Artefact;
import net.minecraft.ChatFormatting;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Util;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SmithingTemplateItem;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

abstract public class ArtefactArmorSet {

	private static final ChatFormatting UPGRADE_SMITHING_TEMPLATE_DESCRIPTION_FORMAT = ChatFormatting.BLUE;

	protected static final Identifier EMPTY_SLOT_BOOTS = Identifier.withDefaultNamespace("container/slot/boots");
	protected static final Identifier EMPTY_SLOT_CHESTPLATE = Identifier.withDefaultNamespace("container/slot/chestplate");
	protected static final Identifier EMPTY_SLOT_HELMET = Identifier.withDefaultNamespace("container/slot/helmet");
	protected static final Identifier EMPTY_SLOT_INGOT = Identifier.withDefaultNamespace("container/slot/ingot");
	protected static final Identifier EMPTY_SLOT_LEGGINGS = Identifier.withDefaultNamespace("container/slot/leggings");

    private final ResourceKey<EquipmentAsset> EQUIPMENT_ASSET = createEquipmentAsset();
    private final ResourceKey<EquipmentAsset> EQUIPMENT_ASSET_ELYTRA = createEquipmentAssetElytra();

    private final ArtefactArmorAttribute ARMOR_ATTRIBUTE = createArmorAttribute();

    public final ResourceKey<Item> BOOTS_ID = ItemIdList.create(getName() + "_boots");
    public final ResourceKey<Item> CHESTPLATE_ID = ItemIdList.create(getName() + "_chestplate");
    public final ResourceKey<Item> ELYTRA_ID = ItemIdList.create(getName() + "_elytra");
    public final ResourceKey<Item> HELMET_ID = ItemIdList.create(getName() + "_helmet");
    public final ResourceKey<Item> LEGGINGS_ID = ItemIdList.create(getName() + "_leggings");
    public final ResourceKey<Item> UPGRADE_SMITHING_TEMPLATE_ID = ItemIdList.create(getName() + "_upgrade_smithing_template");

    public final Item BOOTS = ItemList.register(BOOTS_ID, getItemFactory(ArtefactArmorSetType.BOOTS), ARMOR_ATTRIBUTE.createProperties(ArmorType.BOOTS, createProperties()));
    public final Item CHESTPLATE = ItemList.register(CHESTPLATE_ID, getItemFactory(ArtefactArmorSetType.CHESTPLATE), ARMOR_ATTRIBUTE.createProperties(ArmorType.CHESTPLATE, createProperties()));
    public final Item ELYTRA = ItemList.register(ELYTRA_ID, getItemFactory(ArtefactArmorSetType.ELYTRA), ARMOR_ATTRIBUTE.createPropertiesElytra(createProperties()));
    public final Item HELMET = ItemList.register(HELMET_ID, getItemFactory(ArtefactArmorSetType.HELMET), ARMOR_ATTRIBUTE.createProperties(ArmorType.HELMET, createProperties()));
    public final Item LEGGINGS = ItemList.register(LEGGINGS_ID, getItemFactory(ArtefactArmorSetType.LEGGINGS), ARMOR_ATTRIBUTE.createProperties(ArmorType.LEGGINGS, createProperties()));
    public final Item UPGRADE_SMITHING_TEMPLATE = ItemList.register(UPGRADE_SMITHING_TEMPLATE_ID, getItemFactory(ArtefactArmorSetType.UPGRADE_SMITHING_TEMPLATE), createProperties());

    public void buildRecipes(RecipeProvider provider, RecipeOutput output) {
        Ingredient addition = getUpgradeSmithingTemplateAddition();
        List<Pair<Ingredient, Item>> smithingPairList = List.of(
            Pair.of(Ingredient.of(Items.DIAMOND_BOOTS), BOOTS),
            Pair.of(Ingredient.of(Items.DIAMOND_CHESTPLATE), CHESTPLATE),
            Pair.of(Ingredient.of(Items.ELYTRA), ELYTRA),
            Pair.of(Ingredient.of(Items.DIAMOND_HELMET), HELMET),
            Pair.of(Ingredient.of(Items.DIAMOND_LEGGINGS), LEGGINGS)
        );

        if (addition.isEmpty()) {
            return;
        }
        smithingPairList.forEach((pair) -> {
            SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(UPGRADE_SMITHING_TEMPLATE),
                pair.getFirst(),
                addition,
                RecipeCategory.COMBAT,
                pair.getSecond()
            )
                .unlocks(RecipeProvider.getHasName(UPGRADE_SMITHING_TEMPLATE), provider.has(UPGRADE_SMITHING_TEMPLATE))
                .save(output, RecipeProvider.getItemName(pair.getSecond()) + "_smithing")
            ;
        });
    }

    public ArtefactArmorAttribute createArmorAttribute() {
        return new ArtefactArmorAttribute()
            .equipmentAsset(EQUIPMENT_ASSET)
            .equipmentAssetElytra(EQUIPMENT_ASSET_ELYTRA)
        ;
    }

    public ResourceKey<EquipmentAsset> createEquipmentAsset() {
        return createEquipmentAssetId();
    }

    public ResourceKey<EquipmentAsset> createEquipmentAssetElytra() {
        return createEquipmentAssetElytraId();
    }

    public ResourceKey<EquipmentAsset> createEquipmentAssetId() {
        return ResourceKey.create(EquipmentAssets.ROOT_ID, Lipartefacts.id(getName()));
    }

    public ResourceKey<EquipmentAsset> createEquipmentAssetElytraId() {
        return ResourceKey.create(EquipmentAssets.ROOT_ID, Lipartefacts.id(getName() + "_elytra"));
    }

    public Item.Properties createProperties() {
        return new Item.Properties();
    }

    public Map<TagKey<Item>, Collection<ResourceKey<Item>>> getItemTagList() {
        HashMap<TagKey<Item>, Collection<ResourceKey<Item>>> list = new HashMap<TagKey<Item>, Collection<ResourceKey<Item>>>();

        list.put(ItemTags.CHEST_ARMOR, getIdList(ArtefactArmorSetType.CHESTPLATE));
        list.put(ItemTags.FOOT_ARMOR, getIdList(ArtefactArmorSetType.BOOTS));
        list.put(ItemTags.HEAD_ARMOR, getIdList(ArtefactArmorSetType.HELMET));
        list.put(ItemTags.LEG_ARMOR, getIdList(ArtefactArmorSetType.LEGGINGS));
        return list;
    }

    public List<ArtefactArmorSetType> getRegisteredList() {
        return List.of(
            ArtefactArmorSetType.HELMET,
            ArtefactArmorSetType.CHESTPLATE,
            ArtefactArmorSetType.LEGGINGS,
            ArtefactArmorSetType.BOOTS,
            ArtefactArmorSetType.ELYTRA,
            ArtefactArmorSetType.UPGRADE_SMITHING_TEMPLATE
        );
    }

    public Collection<ResourceKey<Item>> getIdList() {
        ArrayList<ResourceKey<Item>> list = new ArrayList<ResourceKey<Item>>();

        getRegisteredList().forEach((t) -> {
            list.addAll(getIdList(t));
        });
        return list;
    }

    public Collection<ResourceKey<Item>> getIdList(ArtefactArmorSetType type) {
        ArrayList<ResourceKey<Item>> list = new ArrayList<ResourceKey<Item>>();

        switch (type) {
            case ArtefactArmorSetType.ARMOR:
                list.addAll(getIdList(ArtefactArmorSetType.BOOTS));
                list.addAll(getIdList(ArtefactArmorSetType.CHESTPLATE));
                list.addAll(getIdList(ArtefactArmorSetType.HELMET));
                list.addAll(getIdList(ArtefactArmorSetType.LEGGINGS));
                break;
            case ArtefactArmorSetType.BOOTS:
                list.add(BOOTS_ID);
                break;
            case ArtefactArmorSetType.CHESTPLATE:
                list.add(CHESTPLATE_ID);
                break;
            case ArtefactArmorSetType.ELYTRA:
                list.add(ELYTRA_ID);
                break;
            case ArtefactArmorSetType.HELMET:
                list.add(HELMET_ID);
                break;
            case ArtefactArmorSetType.LEGGINGS:
                list.add(LEGGINGS_ID);
                break;
            case ArtefactArmorSetType.UPGRADE_SMITHING_TEMPLATE:
                list.add(UPGRADE_SMITHING_TEMPLATE_ID);
                break;
            default:
                break;
        }
        return list;
    }

	public Function<Item.Properties, Item> getItemFactory(ArtefactArmorSetType type) {
        if (type == ArtefactArmorSetType.UPGRADE_SMITHING_TEMPLATE) {
            return getItemFactoryUpgradeSmithingTemplate();
        }
        return (p) -> {
            Rarity rarity = (type == ArtefactArmorSetType.ELYTRA) ? Rarity.EPIC : getRarity();

            if (rarity != null) p.rarity(rarity);
            return new Artefact(p);
        };
	}

	public Function<Item.Properties, Item> getItemFactoryUpgradeSmithingTemplate() {
        return (p) -> {
            Rarity rarity = getRarity();

            if (rarity != null) p.rarity(rarity);
            return new SmithingTemplateItem(
                Component.translatable(
                    Util.makeDescriptionId("item", Lipartefacts.id("smithing_template." + getName() + "_upgrade.applies_to"))
                ).withStyle(UPGRADE_SMITHING_TEMPLATE_DESCRIPTION_FORMAT),
                Component.translatable(
                    Util.makeDescriptionId("item", Lipartefacts.id("smithing_template." + getName() + "_upgrade.ingredients"))
                ).withStyle(UPGRADE_SMITHING_TEMPLATE_DESCRIPTION_FORMAT),
                Component.translatable(
                    Util.makeDescriptionId("item", Lipartefacts.id("smithing_template." + getName() + "_upgrade.base_slot_description"))
                ),
                Component.translatable(
                    Util.makeDescriptionId("item", Lipartefacts.id("smithing_template." + getName() + "_upgrade.additions_slot_description"))
                ),
                List.of(
                    EMPTY_SLOT_BOOTS,
                    EMPTY_SLOT_CHESTPLATE,
                    EMPTY_SLOT_HELMET,
                    EMPTY_SLOT_LEGGINGS
                ),
                getUpgradeSmithingTemplateAdditionalSlotEmptyIcons(),
                p.rarity(getRarity())
            );
        };
	}

    public Collection<Item> getList() {
        ArrayList<Item> list = new ArrayList<Item>();

        getRegisteredList().forEach((t) -> {
            list.addAll(getList(t));
        });
        return list;
    }

    public Collection<Item> getList(ArtefactArmorSetType type) {
        ArrayList<Item> list = new ArrayList<Item>();

        switch (type) {
            case ArtefactArmorSetType.ARMOR:
                list.addAll(getList(ArtefactArmorSetType.BOOTS));
                list.addAll(getList(ArtefactArmorSetType.CHESTPLATE));
                list.addAll(getList(ArtefactArmorSetType.HELMET));
                list.addAll(getList(ArtefactArmorSetType.LEGGINGS));
                break;
            case ArtefactArmorSetType.BOOTS:
                list.add(BOOTS);
                break;
            case ArtefactArmorSetType.CHESTPLATE:
                list.add(CHESTPLATE);
                break;
            case ArtefactArmorSetType.ELYTRA:
                list.add(ELYTRA);
                break;
            case ArtefactArmorSetType.HELMET:
                list.add(HELMET);
                break;
            case ArtefactArmorSetType.LEGGINGS:
                list.add(LEGGINGS);
                break;
            case ArtefactArmorSetType.UPGRADE_SMITHING_TEMPLATE:
                list.add(UPGRADE_SMITHING_TEMPLATE);
                break;
            default:
                break;
        }
        return list;
    }

    public String getName() {
        return "unknown";
    };

    public Rarity getRarity() {
        return null;
    };

    protected Ingredient getUpgradeSmithingTemplateAddition() {
        return Ingredient.of();
    }

    protected List<Identifier> getUpgradeSmithingTemplateAdditionalSlotEmptyIcons() {
        return List.of(
            EMPTY_SLOT_INGOT
        );
    }

}
