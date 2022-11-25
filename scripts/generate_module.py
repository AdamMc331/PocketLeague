import os
import shutil
import argparse

parser = argparse.ArgumentParser()
parser.add_argument("module_name", help = "The name of your module")
args = parser.parse_args()

root = os.getcwd()

module_name = args.module_name

print("Generating module: " + module_name)

module_type = module_name.split("_")[0]
module_sub_name = module_name.split("_")[1]
module_package = module_name.replace(":", ".").replace("_", ".").replace("-", ".")
module_root = module_type + "/" + module_sub_name

print("Writing files to folder: " + module_root)

# Copy basic structure
shutil.copytree(root + "/scripts/module_templates/", module_root)

# Generate source folders
os.makedirs(module_root + "/src/commonMain/kotlin/com/adammcneilly/pocketleague/" + "/".join(module_package.split(".")))
os.makedirs(module_root + "/src/androidMain/")

# Generate AndroidManifest
manifest = open(module_root + "/src/androidMain/AndroidManifest.xml", "w+")
manifest.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<manifest package=\"com.adammcneilly.pocketleague." + module_package + "\"/>")
manifest.close()

# Add to project
settings = open("settings.gradle.kts", "a")
settings.write("\n")
settings.write("include(\":" + module_type + ":" + module_sub_name + "\")")

print("Done!")