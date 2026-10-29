package lipartefacts.artefacts.enchantments;

import java.util.ArrayList;
import java.util.Collection;

import lipartefacts.Lipartefacts;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.UniformContainerBase;
import net.minecraft.world.level.storage.loot.functions.SetComponentsFunction;
import net.minecraft.world.level.storage.loot.functions.SetEnchantmentsFunction;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

abstract public class ArtefactEnchantment {

    public final ResourceKey<Enchantment> ENCHANTMENT_ID = ResourceKey.create(Registries.ENCHANTMENT, Lipartefacts.id(getName()));

    public ItemStack getEnchantedBook(HolderLookup.Provider registries) {
        ItemStack enchantedBook = new ItemStack(Items.ENCHANTED_BOOK);
        Rarity rarity = getRarity();
        ItemEnchantments.Mutable storedEnchantments = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);

        storedEnchantments.set(registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ENCHANTMENT_ID), 1);
        if (rarity != null) enchantedBook.set(DataComponents.RARITY, rarity);
        enchantedBook.set(DataComponents.ITEM_MODEL, Lipartefacts.id("enchanted_book_" + getName()));
        enchantedBook.set(DataComponents.STORED_ENCHANTMENTS, storedEnchantments.toImmutable());
        return enchantedBook;
    }

    public UniformContainerBase.Builder<?> getEnchantedBookLoot(HolderLookup.Provider registries) {
        UniformContainerBase.Builder<?> enchantedBook = LootItem.lootTableItem(Items.ENCHANTED_BOOK);
        Rarity rarity = getRarity();

        if (rarity != null) enchantedBook.apply(SetComponentsFunction.setComponent(DataComponents.RARITY, rarity));
        enchantedBook.apply(new SetEnchantmentsFunction.Builder().withEnchantment(registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ENCHANTMENT_ID), ContextIntProviders.exactly(1)));
        enchantedBook.apply(SetComponentsFunction.setComponent(DataComponents.ITEM_MODEL, Lipartefacts.id("enchanted_book_" + getName())));
        return enchantedBook;
    }

    public Enchantment.Builder getEnchantmentDefinition(BootstrapContext<Enchantment> context) {
        return null;
    }

    public Collection<TagKey<Enchantment>> getEnchantmentTagList() {
        ArrayList<TagKey<Enchantment>> list = new ArrayList<TagKey<Enchantment>>();

        list.add(EnchantmentTags.TREASURE);
        return list;
    }

    public Collection<ResourceKey<Item>> getIdList() {
        return new ArrayList<ResourceKey<Item>>();
    }

    public Collection<ItemStack> getItemStackList(HolderLookup.Provider registries) {
        Collection<ItemStack> list = new ArrayList<ItemStack>();

        list.add(getEnchantedBook(registries));
        return list;
    }

    public Collection<Item> getList() {
        return new ArrayList<Item>();
    }

    public String getName() {
        return "unknown";
    };

    public Rarity getRarity() {
        return null;
    };

}
