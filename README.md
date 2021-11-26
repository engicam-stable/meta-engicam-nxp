
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
Clone the repository inside the sources folder:
git clone https://github.com/engicam-stable/meta-engicam-nxp.git -b dunfell-community-bsp

Then setup the environment from the fsl-community-bsp folder:
DISTRO=eng-imx-xwayland MACHINE=imx6ull-microgea source setup-environment build
bitbake-layers add-layer ../sources/meta-engicam-nxp
bitbake engicam-evaluation-image
```
