
meta-engicam-nxp
================

Based on NXP Yocto Zeus 2.3 GA


```
mkdir imx-yocto-bsp
cd imx-yocto-bsp
repo init -u https://source.codeaurora.org/external/imx/imx-manifest -b imx-linux-zeus -m imx-5.4.70-2.3.0.xml
repo sync
```


SOMs supported
--------------

- imx8mp-icore
- imx8mp-smarcore
- imx8mm-icore
- imx8mm-icore-2g
- imx8xq-icore
- imx8xd-icore
- imx8xq-smarcore
- imx8xd-smarcore
- imx6ull-microgea

Supported distros
-----------------

- eng-imx-wayland: Distro for Wayland without X11. This distro includes wayland feature but doesn’t have X11 support.
- eng-imx-xwayland: Distro for Wayland with X11. This distro includes both wayland and X11 emulation features.


Images available
----------------

- engicam-evaluation-image
- engicam-evaluation-image-mx6ull

First build
-----------

```
DISTRO=eng-imx-xwayland MACHINE=imx8mp-icore source imx-setup-release.sh -b build
bitbake-layers add-layer ../sources/meta-engicam-nxp
bitbake engicam-evaluation-image
```
