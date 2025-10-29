import json
import os

SOURCES_PATH = "../src/"
SOURCES_ITEM_PATH = SOURCES_PATH + "item/"
SOURCES_ITEM_SET_PATH = SOURCES_PATH + "item_set/"
SOURCES_ITEM_TEMPLATE_PATH = SOURCES_PATH + "item_template/"
SOURCES_RARITY_PATH = SOURCES_PATH + "rarity/"

# Attemps to read a `file_path` file.
def load(file_path: str) -> str:
    print("Loading file from %s." % file_path)
    item_file = open(file_path, "r")
    content = item_file.read()
    item_file.close()
    return content

# Attemps to read all files in the `file_path` directory.
def load_all(file_path: str, as_json: bool = True) -> dict:
    content_dict = {}
    files = os.listdir(file_path)
    for file in files:
        if not os.path.isfile(file_path + file):
            continue
        if as_json:
            content_dict[file] = json.loads(load(file_path + file))
        else:
            content_dict[file] = load(file_path + file)
    return content_dict

# Attemps to read all files in the `SOURCES_ITEM_PATH` directory.
def load_all_items() -> dict:
    return load_all(SOURCES_ITEM_PATH)

# Attemps to read all files in the `SOURCES_ITEM_SET_PATH` directory.
def load_all_item_sets() -> dict:
    return {
        "armor": load_all(SOURCES_ITEM_SET_PATH + "armor/"),
        "generic": load_all(SOURCES_ITEM_SET_PATH),
    }

# Attemps to read all files in the `SOURCES_ITEM_TEMPLATE_PATH` directory.
def load_all_item_templates() -> dict:
    return load_all(SOURCES_ITEM_TEMPLATE_PATH)

# Attemps to read all files in the `SOURCES_RARITY_PATH` directory.
def load_all_rarities() -> dict:
    return load_all(SOURCES_RARITY_PATH)