
meta-engicam-nxp
================

Based on NXP FSL Community BSP DUNFELL

```
PATH=${PATH}:~/bin
mkdir fsl-community-bsp
cd fsl-community-bsp
repo init -u https://github.com/Freescale/fsl-community-bsp-platform -b dunfell
repo sync
```


SOMs supported
--------------

- imx6ull-microgea


Images available
----------------

- engicam-evaluation-image

First build
-----------

```
DISTRO=eng-imx-xwayland MACHINE=imx8mp-icore source imx-setup-release.sh -b build
bitbake-layers add-layer ../sources/meta-engicam-nxp
bitbake engicam-evaluation-image
```
