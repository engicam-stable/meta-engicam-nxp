meta-engicam-nxp
================

Based on NXP Yocto Walnascar
----------------------------

To use the i.MX Manifest repository repo tool must be installed first.

```bash
mkdir ~/bin
curl http://commondatastorage.googleapis.com/git-repo-downloads/repo > ~/bin/repo
chmod a+x ~/bin/repo
PATH=${PATH}:~/bin
```

Then download the yocto project BSP based on walnascar

```bash
mkdir imx-yocto-bsp
cd imx-yocto-bsp
repo init -u https://github.com/nxp-imx/imx-manifest -b imx-linux-walnascar -m imx-6.12.49-2.2.0.xml
repo sync
```

For iMX95 Smarcore and iMX95 I-core clone the meta-summit-radio:

```bash
cd sources/
git clone https://github.com/Ezurio/meta-summit-radio.git -b lrd-13.98.12.x
```


SOMs supported
--------------

- imx6ull-microgea
- imx8mm-icore
- imx8mp-icore
- imx8mp-icore-2e
- imx8mp-icore-fasteth
- imx8mp-smarcore
- imx8ulp-microgea
- imx91-microgea
- imx93-icore
- imx95-icore
- imx95-smarcore


Boards supported
----------------

|         SOM          |                BOARD                |
|----------------------|-------------------------------------|
|   imx6ull-microgea   |[microdev-rev2](docs/test_sheet_mx6_ugea_microdev.md)     |
|                      |[microdev-rev3](docs/test_sheet_mx6_ugea_microdev.md)     |
|                      |[loco5](docs/test_sheet_mx6_ugea_loco5.md)                |
|     imx8mm-icore     |[ctouch2](docs/test_sheet_mx8mm_ctouch2.md)               |
|                      |[ctouch3](docs/test_sheet_mx8mm_ctouch3.md)               |
|                      |[starterkit-v2](docs/test_sheet_mx8mm_starterkit.md)      |
|     imx8mp-icore     |[ctouch2](docs/test_sheet_mx8mp_ctouch2.md)               |
|                      |[ctouch3](docs/test_sheet_mx8mp_ctouch3.md)               |
|                      |[starterkit-v2](docs/test_sheet_mx8mp_starterkit.md)      |
|    imx8mp-icore-2e   |[ctouch2](docs/test_sheet_mx8mp_ctouch2.md)               |
|                      |[ctouch3](docs/test_sheet_mx8mp_ctouch3.md)               |
|                      |[starterkit-v2](docs/test_sheet_mx8mp_starterkit.md)      |
| imx8mp-icore-fasteth |[ctouch2](docs/test_sheet_mx8mp_ctouch2.md)               |
|                      |[ctouch3](docs/test_sheet_mx8mp_ctouch3.md)               |
|                      |[starterkit-v2](docs/test_sheet_mx8mp_starterkit.md)      |
|    imx8mp-smarcore   |[xtouch2](docs/test_sheet_mx8mp_smarcore_xtouch2.md)      |
|   imx8ulp-microgea   |[microdev-rev2](docs/test_sheet_mx8ulp_ugea_microdev.md)  |
|                      |[microdev-rev3](docs/test_sheet_mx8ulp_ugea_microdev.md)  |
|                      |[loco5](docs/test_sheet_mx8ulp_ugea_loco5.md)             |
|    imx91-microgea    |[evb](docs/test_sheet_mx91_ugea_evb.md)                   |
|                      |[loco5](docs/test_sheet_mx91_ugea_loco5.md)               |
|                      |[microdev-rev2](docs/test_sheet_mx91_ugea_microdev.md)    |
|                      |[microdev-rev3](docs/test_sheet_mx91_ugea_microdev.md)    |
|                      |[starterkit-v2](docs/test_sheet_mx91_starterkit.md)       |
|     imx93-icore      |[ctouch2](docs/test_sheet_mx93_ctouch2.md)                |
|                      |[ctouch3](docs/test_sheet_mx93_ctouch3.md)                |
|                      |[starterkit-v2](docs/test_sheet_mx93_starterkit.md)       |
|    imx95-smarcore    |[evb](docs/test_sheet_mx95_smarcore_evb.md)               |
|     imx95-icore      |[starterkit-v2](docs/test_sheet_mx95_icore_starterkit.md) |


Supported distros
-----------------

- fsl-imx-wayland: Distro for Wayland without X11. This distro includes wayland feature but doesn’t have X11 support.
- fsl-imx-xwayland: Distro for Wayland with X11. This distro includes both wayland and X11 emulation features.

Images available
----------------

- engicam-evaluation-image-mx6ull  
- engicam-evaluation-image-mx8  
- engicam-evaluation-image-mx91  
- engicam-evaluation-image-mx93  
- engicam-evaluation-image-mx95  

First build
-----------
- [For imx6ull-microgea](#for-imx6ull-microgea)
- [For imx8mm-icore](#for-imx8mm-icore)
- [For imx8mp-icore-2e](#for-imx8mp-icore-2e)
- [For imx8mp-icore-fasteth](#for-imx8mp-icore-fasteth)
- [For imx8mp-icore](#for-imx8mp-icore)
- [For imx8mp-smarcore](#for-imx8mp-smarcore)
- [For imx8ulp-microgea](#for-imx8ulp-microgea)
- [For imx91-microgea](#for-imx91-microgea)
- [For imx93-icore](#for-imx93-icore)
- [For imx95-icore](#for-imx95-icore)
- [For imx95-smarcore](#for-imx95-smarcore)

For imx6ull-microgea
--------------
```bash
DISTRO=fsl-imx-xwayland MACHINE=imx6ull-microgea source imx-setup-release.sh -b build
bitbake-layers add-layer ../sources/meta-engicam-nxp
bitbake engicam-evaluation-image-mx6
```

For imx8mm-icore
--------------
```bash
DISTRO=fsl-imx-xwayland MACHINE=imx8mm-icore source imx-setup-release.sh -b build
bitbake-layers add-layer ../sources/meta-engicam-nxp
bitbake engicam-evaluation-image-mx8
```

For imx8mp-icore-2e
--------------
```bash
DISTRO=fsl-imx-xwayland MACHINE=imx8mp-icore-2e source imx-setup-release.sh -b build
bitbake-layers add-layer ../sources/meta-engicam-nxp
bitbake engicam-evaluation-image-mx8
```

For imx8mp-icore-fasteth
--------------
```bash
DISTRO=fsl-imx-xwayland MACHINE=imx8mp-icore-fasteth source imx-setup-release.sh -b build
bitbake-layers add-layer ../sources/meta-engicam-nxp
bitbake engicam-evaluation-image-mx8
```

For imx8mp-icore
--------------
```bash
DISTRO=fsl-imx-xwayland MACHINE=imx8mp-icore source imx-setup-release.sh -b build
bitbake-layers add-layer ../sources/meta-engicam-nxp
bitbake engicam-evaluation-image-mx8
```

For imx8mp-smarcore
--------------
```bash
DISTRO=fsl-imx-xwayland MACHINE=imx8mp-smarcore source imx-setup-release.sh -b build
bitbake-layers add-layer ../sources/meta-engicam-nxp
bitbake engicam-evaluation-image-mx8
```

For imx8ulp-microgea
--------------
```bash
DISTRO=fsl-imx-xwayland MACHINE=imx8ulp-microgea source imx-setup-release.sh -b build
bitbake-layers add-layer ../sources/meta-engicam-nxp
bitbake engicam-evaluation-image-mx8
```

For imx91-microgea
--------------
```bash
DISTRO=fsl-imx-xwayland MACHINE=imx91-microgea source imx-setup-release.sh -b build
bitbake-layers add-layer ../sources/meta-engicam-nxp
bitbake engicam-evaluation-image-mx91
```

For imx93-icore
--------------
```bash
DISTRO=fsl-imx-xwayland MACHINE=imx93-icore source imx-setup-release.sh -b build
bitbake-layers add-layer ../sources/meta-engicam-nxp
bitbake engicam-evaluation-image-mx93
```

For imx95-icore:
--------------
Set-up enviroment
```bash
DISTRO=fsl-imx-xwayland MACHINE=imx95-icore source imx-setup-release.sh -b build
```

Add meta-summit-radio meta-layer
```bash
bitbake-layers add-layer ../sources/meta-summit-radio/meta-summit-radio
```

In local.conf add the following lines:
```conf
IMAGE_FEATURES += " allow-empty-password allow-root-login empty-root-password "
PACKAGE_EXCLUDE += "linux-firmware-bcm43430"

EXTRA_IMAGE_FEATURES += "package-management"
SKIP_RECIPE[rutabaga-gfx] = "Not needed"
SKIP_RECIPE[rutabaga-gfx-ffi] = "Not needed"
SKIP_RECIPE[qemu] = "Not needed"
SKIP_RECIPE[xen] = "Not needed"
SKIP_RECIPE[xen-tools] = "Not needed"

IMAGE_INSTALL:remove = "xen xen-tools"

PREFERRED_PROVIDER_wpa-supplicant = "summit-supplicant"
PREFERRED_RPROVIDER_wpa-supplicant = "summit-supplicant"
PREFERRED_RPROVIDER_wpa-supplicant-cli = "summit-supplicant"
PREFERRED_RPROVIDER_wpa-supplicant-passphrase = "summit-supplicant"
PREFERRED_RPROVIDER_wpa-supplicant-lib = "summit-supplicant"
```

Then add the needed layers and build the image:
```bash
bitbake-layers add-layer ../sources/meta-engicam-nxp
bitbake engicam-evaluation-image-mx95
```

For imx95-smarcore:
--------------
Set-up enviroment
```bash
DISTRO=fsl-imx-xwayland MACHINE=imx95-smarcore source imx-setup-release.sh -b build
```

Add meta-summit-radio meta-layer
```bash
bitbake-layers add-layer ../sources/meta-summit-radio/meta-summit-radio
```

In local.conf add the following lines:
```conf
IMAGE_FEATURES += " allow-empty-password allow-root-login empty-root-password "

EXTRA_IMAGE_FEATURES += "package-management"
SKIP_RECIPE[rutabaga-gfx] = "Not needed"
SKIP_RECIPE[rutabaga-gfx-ffi] = "Not needed"
SKIP_RECIPE[qemu] = "Not needed"
SKIP_RECIPE[xen] = "Not needed"
SKIP_RECIPE[xen-tools] = "Not needed"

IMAGE_INSTALL:remove = "xen xen-tools"

PREFERRED_PROVIDER_wpa-supplicant = "summit-supplicant"
PREFERRED_RPROVIDER_wpa-supplicant = "summit-supplicant"
PREFERRED_RPROVIDER_wpa-supplicant-cli = "summit-supplicant"
PREFERRED_RPROVIDER_wpa-supplicant-passphrase = "summit-supplicant"
PREFERRED_RPROVIDER_wpa-supplicant-lib = "summit-supplicant"
```

Then add the needed layers and build the image:
```bash
bitbake-layers add-layer ../sources/meta-engicam-nxp
bitbake engicam-evaluation-image-mx95
```
