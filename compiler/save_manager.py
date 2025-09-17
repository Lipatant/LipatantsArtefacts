ASSETSPACK_PATH = "../LipatantsArtefactsAssets/"
DATAPACK_PATH = "../LipatantsArtefactsData/"

DATAPACK_LIPARTEFACTS_PATH = DATAPACK_PATH + "data/lipartefacts/"
DATAPACK_MINECRAFT_PATH = DATAPACK_PATH + "data/minecraft/"

DATAPACK_ITEM_MODIFIER_PATH = DATAPACK_LIPARTEFACTS_PATH + "item_modifier/"

# Attemps to save a file in `file_path`.
def save(file_path: str, content: str) -> bool:
    global _file_count
    print("Saving file at %s." % file_path)
    item_file = open(file_path, "w")
    item_file.write(content)
    item_file.close()
    return True

# Attemps to save a file in `DATAPACK_ITEM_MODIFIER_PATH + file_path`.
def save_item_modifier(file_path: str, content: str) -> bool:
    return save(DATAPACK_ITEM_MODIFIER_PATH + file_path, content)