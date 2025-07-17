FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://0001-iMX95-Smarcore-Added-support.patch \
            file://0002-iMX95-icore-added-pins-to-A55.patch \
            "

SRCREV = "a07928b4a69e092667c3c2dc61adc36b5eaaf836"
