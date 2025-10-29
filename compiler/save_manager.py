import os

ASSETSPACK_PATH = "../LipatantsArtefactsAssets/"
DATAPACK_PATH = "../LipatantsArtefactsData/"

DATAPACK_LIPARTEFACTS_PATH = DATAPACK_PATH + "data/lipartefacts/"
DATAPACK_MINECRAFT_PATH = DATAPACK_PATH + "data/minecraft/"

DATAPACK_ITEM_MODIFIER_PATH = DATAPACK_LIPARTEFACTS_PATH + "item_modifier/"
DATAPACK_LOOT_TABLE_PATH = DATAPACK_LIPARTEFACTS_PATH + "loot_table/"
DATAPACK_RECIPE_PATH = DATAPACK_LIPARTEFACTS_PATH + "recipe/"

# Attemps to save a file in `file_path`.
def save(file_path: str, content: str) -> bool:
    global _file_count
    print("Saving file at %s." % file_path)
    os.makedirs(os.path.dirname(file_path), exist_ok=True)
    item_file = open(file_path, "w")
    item_file.write(content)
    item_file.close()
    return True

# Attemps to save a file in `DATAPACK_ITEM_MODIFIER_PATH + file_path`.
def save_item_modifier(file_path: str, content: str) -> bool:
    return save(DATAPACK_ITEM_MODIFIER_PATH + file_path, content)

# Attemps to save a file in `DATAPACK_LOOT_TABLE_PATH + file_path`.
def save_loot_table(file_path: str, content: str) -> bool:
    return save(DATAPACK_LOOT_TABLE_PATH + file_path, content)

# Attemps to save a file in `DATAPACK_RECIPE_PATH + file_path`.
def save_recipe(file_path: str, content: str) -> bool:
    return save(DATAPACK_RECIPE_PATH + file_path, content)