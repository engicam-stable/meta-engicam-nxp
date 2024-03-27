
meta-engicam-nxp
================

Based on NXP Yocto Langdale



```
mkdir imx-yocto-bsp
cd imx-yocto-bsp
repo init -u https://github.com/nxp-imx/imx-manifest -b imx-linux-mickledore -m imx-6.1.36-2.1.0.xml
repo sync
```


SOMs supported
--------------

- imx6dl-icore
- imx6qd-icore
- imx6s-icore
- imx6ull-microgea
- imx8mm-icore
- imx8mp-icore
- imx8mp-icore-fasteth
- imx8mp-icore-2e
- imx8mp-smarcore
- imx8qxp-icore
- imx8ulp-microgea
- imx93-icore

Supported distros
-----------------

- fsl-imx-wayland: Distro for Wayland without X11. This distro includes wayland feature but doesn’t have X11 support.
- fsl-imx-xwayland: Distro for Wayland with X11. This distro includes both wayland and X11 emulation features.


Images available
----------------

- engicam-evaluation-image-mx6
- engicam-evaluation-image-mx6ull
- engicam-evaluation-image-mx8
- engicam-evaluation-image-mx93

First build
-----------


```
DISTRO=fsl-imx-xwayland MACHINE=imx8mp-icore source imx-setup-release.sh -b build
bitbake-layers add-layer ../sources/meta-engicam-nxp
bitbake engicam-evaluation-image
```


