package lipartefacts;

import lipartefacts.artefacts.ArtefactList;
import net.fabricmc.fabric.api.loot.v3.FabricLootTableBuilder;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.advancements.predicates.LocationPredicate;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.UniformContainerBase;
import net.minecraft.world.level.storage.loot.functions.EnchantWithLevelsFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemDamageFunction;
import net.minecraft.world.level.storage.loot.predicates.LocationCheck;
import net.minecraft.world.level.storage.loot.providers.number.floats.ContextFloatProviders;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

public class LootTableList {

    private static final ResourceKey<LootTable> ARCHAEOLOGY_TRAIL_DESERT_PYRAMID = createResourceKey("minecraft", "archaeology/desert_pyramid");
    private static final ResourceKey<LootTable> ARCHAEOLOGY_TRAIL_RUINS_RARE_ID = createResourceKey("minecraft", "archaeology/trail_ruins_rare");
    private static final ResourceKey<LootTable> CHESTS_ABANDONED_MINESHAFT = createResourceKey("minecraft", "chests/abandoned_mineshaft");
    private static final ResourceKey<LootTable> CHESTS_ANCIENT_CITY = createResourceKey("minecraft", "chests/ancient_city");
    private static final ResourceKey<LootTable> CHESTS_BASTION_HOLGIN_STABLE = createResourceKey("minecraft", "chests/bastion_hoglin_stable");
    private static final ResourceKey<LootTable> CHESTS_BASTION_TREASURE = createResourceKey("minecraft", "chests/bastion_treasure");
    private static final ResourceKey<LootTable> CHESTS_DESERT_PYRAMID = createResourceKey("minecraft", "chests/desert_pyramid");
    private static final ResourceKey<LootTable> CHESTS_END_CITY_TREASURE = createResourceKey("minecraft", "chests/end_city_treasure");
    private static final ResourceKey<LootTable> CHESTS_IGLOO_CHEST = createResourceKey("minecraft", "chests/igloo_chest");
    private static final ResourceKey<LootTable> CHESTS_JUNGLE_TEMPLE = createResourceKey("minecraft", "chests/jungle_temple");
    private static final ResourceKey<LootTable> CHESTS_PILLAGER_OUTPOST = createResourceKey("minecraft", "chests/pillager_outpost");
    private static final ResourceKey<LootTable> CHESTS_SHIPWRECK_TREASURE = createResourceKey("minecraft", "chests/shipwreck_treasure");
    private static final ResourceKey<LootTable> CHESTS_SIMPLE_DUNGEON = createResourceKey("minecraft", "chests/simple_dungeon");
    private static final ResourceKey<LootTable> CHESTS_STRONGHOLD_LIBRARY = createResourceKey("minecraft", "chests/stronghold_library");
    private static final ResourceKey<LootTable> CHESTS_TRIAL_CHAMBERS_REWARD_OMINOUS_RARE = createResourceKey("minecraft", "chests/trial_chambers/reward_ominous_rare");
    private static final ResourceKey<LootTable> CHESTS_TRIAL_CHAMBERS_REWARD_RARE = createResourceKey("minecraft", "chests/trial_chambers/reward_rare");
    private static final ResourceKey<LootTable> CHESTS_TRIAL_CHAMBERS_REWARD_UNIQUE = createResourceKey("minecraft", "chests/trial_chambers/reward_unique");
    private static final ResourceKey<LootTable> CHESTS_UNDERWATER_RUIN_BIG = createResourceKey("minecraft", "chests/underwater_ruin_big");
    private static final ResourceKey<LootTable> CHESTS_UNDERWATER_RUIN_SMALL = createResourceKey("minecraft", "chests/underwater_ruin_small");
    private static final ResourceKey<LootTable> CHESTS_VILLAGE_VILLAGE_ARMORER = createResourceKey("minecraft", "chests/village/village_armorer");
    private static final ResourceKey<LootTable> CHESTS_VILLAGE_VILLAGE_TANNERY = createResourceKey("minecraft", "chests/village/village_tannery");
    private static final ResourceKey<LootTable> CHESTS_VILLAGE_VILLAGE_TOOLSMITH = createResourceKey("minecraft", "chests/village/village_toolsmith");
    private static final ResourceKey<LootTable> CHESTS_VILLAGE_VILLAGE_WEAPONSMITH = createResourceKey("minecraft", "chests/village/village_weaponsmith");
    private static final ResourceKey<LootTable> CHESTS_WOODLAND_MANSION = createResourceKey("minecraft", "chests/woodland_mansion");
    private static final ResourceKey<LootTable> GAMEPLAY_CAT_MORNING_GIFT = createResourceKey("minecraft", "gameplay/cat_morning_gift");
    private static final ResourceKey<LootTable> GAMEPLAY_FISHING_TREASURE = createResourceKey("minecraft", "gameplay/fishing/treasure");
    private static final ResourceKey<LootTable> GAMEPLAY_PIGLIN_BARTERING = createResourceKey("minecraft", "gameplay/piglin_bartering");

    public static ResourceKey<LootTable> createResourceKey(Identifier location) {
        return ResourceKey.create(Registries.LOOT_TABLE, location);
    }

    public static ResourceKey<LootTable> createResourceKey(String path) {
        return createResourceKey(Lipartefacts.id(path));
    }

    public static ResourceKey<LootTable> createResourceKey(String namespace, String path) {
        return createResourceKey(Identifier.fromNamespaceAndPath(namespace, path));
    }

    @SuppressWarnings("null")
    public static void initialize() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (!source.isBuiltin()) return;
            if (ARCHAEOLOGY_TRAIL_RUINS_RARE_ID.equals(key)) {
                ((FabricLootTableBuilder) tableBuilder).modifyPools((pool) -> {
                    pool
                        .add(LootItem.lootTableItem(ArtefactList.ARMOR.SNIFFER.UPGRADE_SMITHING_TEMPLATE).setWeight(4))
                    ;
                });
            }
            if (CHESTS_TRIAL_CHAMBERS_REWARD_OMINOUS_RARE.equals(key)) {
                ((FabricLootTableBuilder) tableBuilder).modifyPools((pool) -> {
                    pool
                        .add(LootItem.lootTableItem(ArtefactList.ARMOR.COPPER_GOLEM.UPGRADE_SMITHING_TEMPLATE).setWeight(4))
                        // DRILLING PICKAXE w2
                        .add(ArtefactList.ENCHANTMENT.VAMPIRISM.getEnchantedBookLoot(registries))
                    ;
                });
            }
            if (CHESTS_TRIAL_CHAMBERS_REWARD_UNIQUE.equals(key)) {
                ((FabricLootTableBuilder) tableBuilder).modifyPools((pool) -> {
                    pool
                        // DRILLING PICKAXE w2
                        .add(ArtefactList.ENCHANTMENT.DOUBLE_SHOT.getEnchantedBookLoot(registries))
                        .add(ArtefactList.ENCHANTMENT.FRACTURE.getEnchantedBookLoot(registries))
                    ;
                });
            }
            if (CHESTS_BASTION_TREASURE.equals(key)) {
                tableBuilder.withPool(
                    LootPool.lootPool()
                        .add(ArtefactList.ENCHANTMENT.FRACTURE.getEnchantedBookLoot(registries))
                        .add(ArtefactList.ENCHANTMENT.IMPERILMENT.getEnchantedBookLoot(registries).setWeight(2))
                );
                tableBuilder.withPool(
                    LootPool.lootPool()
                        // NETHERITE BATTLE SHIELD w1
                        .add(LootItem.lootTableItem(ArtefactList.ARMOR.PIGLIN.UPGRADE_SMITHING_TEMPLATE).setWeight(4))
                );
            }
            if (CHESTS_IGLOO_CHEST.equals(key)) {
                tableBuilder.withPool(
                    LootPool.lootPool()
                        .add(ArtefactList.ENCHANTMENT.CALL_TO_CHAOS.getEnchantedBookLoot(registries))
                );
            }
            if (CHESTS_JUNGLE_TEMPLE.equals(key)) {
                tableBuilder.withPool(
                    ArtefactList.MUSIC_DISC.getLootPool()
                );
                tableBuilder.withPool(
                    LootPool.lootPool()
                        .add(LootItem.lootTableItem(ArtefactList.ARMOR.BOGGED.CHESTPLATE))
                        .add(ArtefactList.ENCHANTMENT.DOUBLE_SHOT.getEnchantedBookLoot(registries))
                );
            }
            if (CHESTS_STRONGHOLD_LIBRARY.equals(key)) {
                // CRYSTALS
                tableBuilder.withPool(
                    LootPool.lootPool()
                        .add(ArtefactList.ENCHANTMENT.DOUBLE_SHOT.getEnchantedBookLoot(registries))
                        .add(ArtefactList.ENCHANTMENT.FRACTURE.getEnchantedBookLoot(registries))
                        .add(ArtefactList.ENCHANTMENT.IMPERILMENT.getEnchantedBookLoot(registries).setWeight(2))
                );
            }
            if (CHESTS_VILLAGE_VILLAGE_TANNERY.equals(key)) {
                tableBuilder.withPool(
                    LootPool.lootPool()
                        .add(EmptyLootItem.emptyItem())
                        .add(LootItem.lootTableItem(ArtefactList.ARMOR.CAT.HELMET))
                );
            }
            if (CHESTS_WOODLAND_MANSION.equals(key)) {
                tableBuilder.withPool(
                    ArtefactList.MUSIC_DISC.getLootPool()
                );
                tableBuilder.withPool(
                    LootPool.lootPool()
                        .add(ArtefactList.ENCHANTMENT.FRACTURE.getEnchantedBookLoot(registries))
                        .add(ArtefactList.ENCHANTMENT.IMPERILMENT.getEnchantedBookLoot(registries).setWeight(2))
                );
                tableBuilder.withPool(
                    LootPool.lootPool()
                        .add(ArtefactList.ENCHANTMENT.ILLAGER_BANE.getEnchantedBookLoot(registries))
                );
                tableBuilder.withPool(
                    LootPool.lootPool()
                        .add(LootItem.lootTableItem(ArtefactList.CHARM.RAVAGER.CHARM))
                );
            }
            if (GAMEPLAY_CAT_MORNING_GIFT.equals(key)) {
                ((FabricLootTableBuilder) tableBuilder).modifyPools((pool) -> {
                    pool
                        .add(LootItem.lootTableItem(ArtefactList.ARMOR.CAT.HELMET).setWeight(2))
                        .add(LootItem.lootTableItem(ArtefactList.CHARM.CHICKEN.CHARM).setWeight(2))
                    ;
                });
            }
            if (GAMEPLAY_FISHING_TREASURE.equals(key)) {
                ((FabricLootTableBuilder) tableBuilder).modifyPools((pool) -> {
                    pool
                        .add(lootTableItemOnRandomLoot(LootItem.lootTableItem(ArtefactList.ARMOR.CAT.HELMET)))
                        .add(lootTableItemOnRandomLoot(LootItem.lootTableItem(ArtefactList.ARMOR.FISHERMAN.CHESTPLATE)))
                        .add(lootTableItemOnRandomLoot(LootItem.lootTableItem(ArtefactList.ARMOR.FISHERMAN.HELMET)))
                        .add(lootTableItemOnRandomLoot(LootItem.lootTableItem(ArtefactList.ARMOR.BOGGED.CHESTPLATE))
                            .when(LocationCheck.checkLocation(LocationPredicate.Builder.location().setBiomes(HolderSet.direct(
                                registries.lookupOrThrow(Registries.BIOME).getOrThrow(Biomes.MANGROVE_SWAMP),
                                registries.lookupOrThrow(Registries.BIOME).getOrThrow(Biomes.SWAMP)
                            ))))
                        )
                        .add(lootTableItemOnRandomLoot(LootItem.lootTableItem(ArtefactList.ARMOR.PARCHED.CHESTPLATE))
                            .when(LocationCheck.checkLocation(LocationPredicate.Builder.location().setBiomes(HolderSet.direct(
                                registries.lookupOrThrow(Registries.BIOME).getOrThrow(Biomes.DESERT)
                            ))))
                        )
                        .add(lootTableItemOnRandomLoot(LootItem.lootTableItem(ArtefactList.ARMOR.STRAY.CHESTPLATE))
                            .when(LocationCheck.checkLocation(LocationPredicate.Builder.location().setBiomes(HolderSet.direct(
                                registries.lookupOrThrow(Registries.BIOME).getOrThrow(Biomes.SNOWY_PLAINS),
                                registries.lookupOrThrow(Registries.BIOME).getOrThrow(Biomes.ICE_SPIKES),
                                registries.lookupOrThrow(Registries.BIOME).getOrThrow(Biomes.FROZEN_RIVER)
                            ))))
                        )
                    ;
                });
            }
            if (GAMEPLAY_PIGLIN_BARTERING.equals(key)) {
                ((FabricLootTableBuilder) tableBuilder).modifyPools((pool) -> {
                    pool
                        .add(LootItem.lootTableItem(ArtefactList.CHARM.STRIDER.CHARM).setWeight(5))
                    ;
                });
            }
        });

    }

    private static UniformContainerBase.Builder<?> lootTableItemOnRandomLoot(UniformContainerBase.Builder<?> item) {
        item.apply(new EnchantWithLevelsFunction.Builder(ContextIntProviders.exactly(30)));
        item.apply(SetItemDamageFunction.setDamage(ContextFloatProviders.between(0.5F, 0.95F)));
        return item;
    }

}
