#!/bin/bash  
PREBUILT=D://AndroidTools64/android-ndk64-r10-windows-x86_64/android-ndk-r10/toolchains/arm-linux-androideabi-4.4.3/prebuilt/linux-x86  
PLATFORM=D://AndroidTools64/android-ndk64-r10-windows-x86_64/android-ndk-r10/platforms/android-9/arch-arm  
./configure --target-os=linux \  
--arch=arm \  
--enable-version3 \  
--enable-gpl \  
--enable-nonfree \  
--enable-shared \  
--disable-stripping \  
--disable-ffmpeg \  
--disable-ffplay \  
--disable-ffserver \  
--disable-ffprobe \  
--disable-symver \  
--enable-encoders \  
--enable-muxers \  
--disable-devices \  
--disable-protocols \  
--enable-protocol=file \  
--enable-avfilter \  
--disable-network \  
--disable-avdevice \  
--disable-asm \  
--enable-cross-compile \  
--cc=$PREBUILT/bin/arm-linux-androideabi-gcc \  
--cross-prefix=$PREBUILT/bin/arm-linux-androideabi- \  
--strip=$PREBUILT/bin/arm-linux-androideabi-strip \  
--extra-cflags="-fPIC -DANDROID" \  
--extra-ldflags="-Wl,-T,$PREBUILT/arm-linux-androideabi/lib/ldscripts/armelf_linux_eabi.x -Wl,-rpath-link=$PLATFORM/usr/lib -L$PLATFORM/usr/lib -nostdlib $PREBUILT/lib/gcc/arm-linux-androideabi/4.4.3/crtbegin.o $PREBUILT/lib/gcc/arm-linux-androideabi/4.4.3/crtend.o -lc -lm -ldl" \  
sed -i 's/HAVE_LRINT 0/HAVE_LRINT 1/g' config.h  
sed -i 's/HAVE_LRINTF 0/HAVE_LRINTF 1/g' config.h  
sed -i 's/HAVE_ROUND 0/HAVE_ROUND 1/g' config.h  
sed -i 's/HAVE_ROUNDF 0/HAVE_ROUNDF 1/g' config.h  
sed -i 's/HAVE_TRUNC 0/HAVE_TRUNC 1/g' config.h  
sed -i 's/HAVE_TRUNCF 0/HAVE_TRUNCF 1/g' config.h  
sed -i 's/HAVE_CBRT 0/HAVE_CBRT 1/g' config.h  
sed -i 's/HAVE_CBRTF 0/HAVE_CBRTF 1/g' config.h  
sed -i 's/HAVE_ISINF 0/HAVE_ISINF 1/g' config.h  
sed -i 's/HAVE_ISNAN 0/HAVE_ISNAN 1/g' config.h  
sed -i 's/HAVE_SINF 0/HAVE_SINF 1/g' config.h  
sed -i 's/HAVE_RINT 0/HAVE_RINT 1/g' config.h