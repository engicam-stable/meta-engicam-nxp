
meta-engicam-nxp
================

Based on NXP Yocto Honister

Retrieve the sources
--------------------

```
mkdir imx-yocto-bsp
cd imx-yocto-bsp
repo init -u https://source.codeaurora.org/external/imx/imx-manifest -b imx-linux-honister -m imx-5.15.5-1.0.0.xml
repo sync
```

SOMs supported
--------------

- imx6ull-microgea
- imx8mm-icore-2gb
- imx8mm-icore
- imx8mp-icore-1gb
- imx8mp-icore
- imx8mp-smarcore
- imx8xd-icore
- imx8xq-icore

Supported distros
-----------------

- fsl-imx-wayland: Distro for Wayland without X11. This distro includes wayland feature but doesn’t have X11 support.
- fsl-imx-xwayland: Distro for Wayland with X11. This distro includes both wayland and X11 emulation features.

Images available
----------------

- engicam-evaluation-image
- engicam-evaluation-image-mx6ull

First build
-----------

```
DISTRO=fsl-imx-xwayland MACHINE=imx8mp-icore source imx-setup-release.sh -b build
bitbake-layers add-layer ../sources/meta-engicam-nxp
bitbake engicam-evaluation-image
```

