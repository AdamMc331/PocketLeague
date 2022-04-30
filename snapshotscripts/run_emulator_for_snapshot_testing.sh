if [ ! -d "$ANDROID_HOME" ] ; then
  echo "Cannot find \$ANDROID_HOME."
  exit
fi

"$ANDROID_HOME/emulator/emulator" "@snappy" "-verbose" "-no-window" "-no-audio" "-no-boot-anim" "-netdelay" "none" "-gpu" "swiftshader_indirect" "-camera-back" "none" "-camera-front" "none"