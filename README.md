
meta-engicam-nxp
================

Based on NXP Yocto Langdale



```bash
mkdir imx-yocto-bsp
cd imx-yocto-bsp
repo init -u https://github.com/engicam-stable/engicam-bsp-release.git -b langdale-nxp -m engicam-bsp-release.xml
repo sync
```


SOMs supported
--------------

- imx8mp-icore
- imx8mp-icore-2e
- imx8mp-icore-fasteth


Supported distros
-----------------

- fsl-imx-xwayland: Distro for Wayland with X11. This distro includes both wayland and X11 emulation features.

Images available
----------------

-   engicam-evaluation-image-mx6
-   engicam-evaluation-image-mx6ull
-   engicam-evaluation-image-mx8
-   engicam-evaluation-image-mx93


First build
-----------


```bash
DISTRO=fsl-imx-xwayland MACHINE=imx8mp-icore source imx-setup-release.sh -b build
bitbake-layers add-layer ../sources/meta-engicam-nxp
bitbake engicam-evaluation-image
```


