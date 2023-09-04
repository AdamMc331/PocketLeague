import os
import shutil
import argparse

parser = argparse.ArgumentParser()
parser.add_argument("module_name", help = "The name of your module")
args = parser.parse_args()

root = os.getcwd()

module_name = args.module_name

print("Generating module: " + module_name)

module_parts = module_name.split("_")
module_package = module_name.replace(":", ".").replace("_", ".").replace("-", ".")
module_root = "/".join(module_parts)

print("Writing files to folder: " + module_root)

# Copy basic structure
shutil.copytree(root + "/scripts/module_templates/", module_root)

# Generate source folders
os.makedirs(module_root + "/src/commonMain/kotlin/com/adammcneilly/pocketleague/" + "/".join(module_package.split(".")))

# Add to project
settings = open("settings.gradle.kts", "a")
settings.write("\n")
settings.write("include(\":" + ":".join(module_parts) + "\")")

print("Done!")