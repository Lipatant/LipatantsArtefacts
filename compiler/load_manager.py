TEMPLATES_PATH = "./templates/"

TEMPLATES_ITEM_MODIFIER_PATH = TEMPLATES_PATH + "item_modifier/"

# Attemps to reads a `file_path` file.
def load(file_path: str) -> str:
    print("Loading file from %s." % file_path)
    item_file = open(file_path, "r")
    content = item_file.read()
    item_file.close()
    return content

# Attemps to reads a `TEMPLATES_ITEM_MODIFIER_PATH + file_path` file.
def load_template_item_modifier(file_path: str) -> str:
    return load(TEMPLATES_ITEM_MODIFIER_PATH + file_path)