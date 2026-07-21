SUMMARY = "Fix brcmfamc issue"
DESCRIPTION = " \
        Add modprobe option for brcmfmac driver to fix issue with wpa_supplicant 2.11. \
        It disables SAE, WPA3 handshake offloading. \
        Use it if you want to use wpa_supplicant as wireless daemon. \
        "

LICENSE = "MIT"

do_install() {
        install -d  "${D}${sysconfdir}/modprobe.d"
        echo "options brcmfmac feature_disable=0x82000" > "${D}${sysconfdir}/modprobe.d/brcmfmac_fix.conf"
}
