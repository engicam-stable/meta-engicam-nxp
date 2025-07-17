FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://0001-imx95-added-configuration-for-4GB-DDR.patch \
            file://0002-imx95-icore-added-configuration-for-4GB-DDR-3200MTS.patch \
            "

SRCREV = "dd800f27a921f49d847ae089028deca0b6b3763a"
EXTRA_OEMAKE += "DDR_CONFIG=${DDR_CONFIG}"
