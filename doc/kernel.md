Kernel Build Guide
======


Create your temporary build directory:
```
mkdir /media/matteo/ext4ml/mx8m_mm_yocto/temp_build
```

1. launch the bitbake environment with setup-environment command.

Es.
```
. setup-environment imx8mm-wayland/
```
and launch the devshell of imx-mkimage packet with command:

```
bitbake linux-imx -c devshell
```
the copy the git directory to your temporary build directory.

es.
```
cd ..
cp -rf kernel-source/  /media/matteo/ext4ml/mx8m_mm_yocto/temp_build/
exit
```



3.
launch the toolchain setup environment
es.
```
. /opt/fsl-imx-wayland/4.9.51-mx8-beta/environment-setup-aarch64-poky-linux
unset LDFLAGS
```

and start configuring and building:

```
cd kernel-source/
make icoremx8mm_defconfig
make -j 12
make dtbs
```
