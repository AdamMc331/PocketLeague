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

# Path to the build.gradle.kts file in the newly copied module
build_gradle_path = os.path.join(module_root, "build.gradle.kts")

try:
    # Read the content of the copied build.gradle.kts
    with open(build_gradle_path, 'r') as file:
        file_content = file.read()

    # Replace the placeholder text
    modified_content = file_content.replace("REPLACEME", f"com.adammcneilly.pocketleague.{module_package}")

    # Write the modified content back to the build.gradle.kts file
    with open(build_gradle_path, 'w') as file:
        file.write(modified_content)

    print(f"Updated namespace in {build_gradle_path}")

except FileNotFoundError:
    print(f"Error: {build_gradle_path} not found after copy.")
except Exception as e:
    print(f"An error occurred while modifying {build_gradle_path}: {e}")

# Generate source folders
os.makedirs(module_root + "/src/commonMain/kotlin/com/adammcneilly/pocketleague/" + "/".join(module_package.split(".")))

# Add to project
settings = open("settings.gradle.kts", "a")
settings.write("include(\":" + ":".join(module_parts) + "\")")
settings.write("\n")

print("Done!")